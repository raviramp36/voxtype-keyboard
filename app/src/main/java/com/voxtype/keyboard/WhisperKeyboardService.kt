package com.voxtype.keyboard

import android.Manifest
import android.content.pm.PackageManager
import android.inputmethodservice.InputMethodService
import android.media.MediaRecorder
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import kotlinx.coroutines.*
import java.io.File
import java.io.IOException
import java.util.Locale

class WhisperKeyboardService : InputMethodService() {
    
    private lateinit var keyboardView: View
    private lateinit var voiceButton: View
    private lateinit var statusText: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var groqProcessor: GroqProcessor
    private lateinit var analyticsManager: AnalyticsManager
    private lateinit var advancedSettings: AdvancedSettingsManager
    
    private var mediaRecorder: MediaRecorder? = null
    private var audioFile: File? = null
    private var isRecording = false
    private var recordingStartTime = 0L
    private var lastTranscribedText = ""
    private val mainScope = CoroutineScope(Dispatchers.Main + Job())
    private val handler = Handler(Looper.getMainLooper())
    
    // New feature states
    private var isWhisperMode = false
    private var selectedLanguage = "auto"
    private var isPrivacyMode = false
    
    override fun onCreate() {
        super.onCreate()
        groqProcessor = GroqProcessor(this)
        analyticsManager = AnalyticsManager(this)
        advancedSettings = AdvancedSettingsManager(this)
        
        // Load feature settings
        loadFeatureSettings()
        
        // Check if API key is configured
        val prefs = getSharedPreferences("voxtype_prefs", MODE_PRIVATE)
        val apiKey = prefs.getString("groq_api_key", "") ?: ""
        if (apiKey.isEmpty()) {
            Handler(Looper.getMainLooper()).postDelayed({
                showStatus("⚠️ Add Groq API key in settings")
            }, 1000)
        }
    }
    
    override fun onCreateInputView(): View {
        keyboardView = LayoutInflater.from(this).inflate(R.layout.keyboard_layout_simple, null)
        
        // Initialize UI components
        val voiceTouchArea = keyboardView.findViewById<View>(R.id.voice_touch_area)
        voiceButton = keyboardView.findViewById(R.id.voice_button) // Hidden view for compatibility
        statusText = keyboardView.findViewById(R.id.status_text)
        progressBar = keyboardView.findViewById(R.id.progress_bar)
        
        // Set up the entire touch area for voice recording
        voiceTouchArea.setOnClickListener {
            toggleRecording()
        }
        
        // Long press for continuous recording on touch area
        voiceTouchArea.setOnLongClickListener {
            startContinuousRecording()
            true
        }
        
        // Set up quick action buttons
        setupQuickActions()
        
        return keyboardView
    }
    
    override fun onStartInputView(info: EditorInfo?, restarting: Boolean) {
        super.onStartInputView(info, restarting)
        keyboardView?.visibility = View.VISIBLE
        updateUI(RecordingState.IDLE)
        
        // Update UI elements with current settings
        updateLanguageButton()
        updateWhisperModeUI()
    }
    
    override fun onFinishInputView(finishingInput: Boolean) {
        super.onFinishInputView(finishingInput)
        if (isRecording) {
            stopRecording()
        }
    }
    
    private fun setupQuickActions() {
        keyboardView.findViewById<View>(R.id.settings_button)?.setOnClickListener {
            val intent = android.content.Intent(this, MainActivity::class.java).apply {
                flags = android.content.Intent.FLAG_ACTIVITY_NEW_TASK
            }
            startActivity(intent)
        }
        
        keyboardView.findViewById<View>(R.id.language_button)?.setOnClickListener {
            switchToNextSubtype()
        }
        
        keyboardView.findViewById<View>(R.id.space_button)?.setOnClickListener {
            // Insert space character
            currentInputConnection?.commitText(" ", 1)
            vibrate(25)
        }
        
        keyboardView.findViewById<View>(R.id.clear_button)?.setOnClickListener {
            currentInputConnection?.deleteSurroundingText(1000, 1000)
        }
        
        // Add backspace button functionality
        keyboardView.findViewById<View>(R.id.backspace_button)?.setOnClickListener {
            handleBackspace()
        }
        
        // Add whisper mode toggle on long press of voice button
        voiceButton.setOnLongClickListener {
            if (!isRecording) {
                toggleWhisperMode()
                true
            } else {
                false
            }
        }
    }
    
    private fun switchToNextSubtype() {
        val imeManager = getSystemService(INPUT_METHOD_SERVICE) as android.view.inputmethod.InputMethodManager
        imeManager.switchToNextInputMethod(window.window?.attributes?.token, false)
    }
    
    private fun handleBackspace() {
        val inputConnection = currentInputConnection ?: return
        
        // Check if there's selected text
        val selectedText = inputConnection.getSelectedText(0)
        if (!selectedText.isNullOrEmpty()) {
            // Delete selected text
            inputConnection.commitText("", 1)
        } else {
            // Delete character before cursor
            inputConnection.deleteSurroundingText(1, 0)
        }
        
        // Vibrate for feedback
        vibrate(25)
    }
    
    private fun toggleRecording() {
        if (isRecording) {
            stopRecording()
        } else {
            startRecording()
        }
    }
    
    private fun startRecording() {
        if (!hasRecordPermission()) {
            showStatus("Microphone permission required")
            return
        }
        
        try {
            // Create temporary audio file
            audioFile = File.createTempFile("voxtype_", ".m4a", cacheDir)
            
            // Initialize MediaRecorder based on Android version
            mediaRecorder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                MediaRecorder(this)
            } else {
                @Suppress("DEPRECATION")
                MediaRecorder()
            }.apply {
                setAudioSource(MediaRecorder.AudioSource.MIC)
                setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
                setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
                setAudioSamplingRate(16000)  // Optimal for Whisper
                setOutputFile(audioFile?.absolutePath)
                prepare()
                start()
            }
            
            isRecording = true
            recordingStartTime = System.currentTimeMillis()
            updateUI(RecordingState.RECORDING)
            showStatus("Recording...")
            vibrate()
            
        } catch (e: IOException) {
            e.printStackTrace()
            showStatus("Failed to start recording")
        }
    }
    
    private fun stopRecording() {
        try {
            mediaRecorder?.apply {
                stop()
                release()
            }
            mediaRecorder = null
            isRecording = false
            
            updateUI(RecordingState.PROCESSING)
            showStatus("Processing...")
            
            // Process the audio file
            audioFile?.let { file ->
                processAudioWithWhisper(file)
            }
            
        } catch (e: Exception) {
            e.printStackTrace()
            showStatus("Recording error")
            updateUI(RecordingState.IDLE)
        }
    }
    
    private fun startContinuousRecording() {
        startRecording()
        
        voiceButton.setOnTouchListener { _, event ->
            when (event.action) {
                android.view.MotionEvent.ACTION_UP -> {
                    stopRecording()
                    voiceButton.setOnTouchListener(null)
                    true
                }
                else -> false
            }
        }
    }
    
    private fun processAudioWithWhisper(audioFile: File) {
        mainScope.launch {
            try {
                updateUI(RecordingState.PROCESSING)
                showStatus("Transcribing with Whisper...")
                
                // Reload settings to ensure API is configured
                groqProcessor.reloadSettings()
                
                // Get context for better transcription
                val contextBefore = currentInputConnection?.getTextBeforeCursor(200, 0)?.toString() ?: ""
                
                // Get language preference or auto-detect
                val language = getSelectedLanguage()
                
                // Transcribe with Whisper with enhanced settings
                val transcribedText = groqProcessor.transcribeAudio(
                    audioFile = audioFile,
                    language = language,
                    prompt = contextBefore,  // Provide context for better accuracy
                    isWhisperMode = isWhisperMode
                )
                
                // Detect mode for potential enhancement
                val mode = detectTextMode()
                
                // Apply smart formatting first
                var enhancedText = groqProcessor.applySmartFormatting(transcribedText, contextBefore)
                
                // Optional: Further enhance with LLM if needed
                val finalText = if (shouldEnhanceText(mode)) {
                    showStatus("Enhancing...")
                    groqProcessor.processText(
                        rawText = enhancedText, 
                        context = contextBefore, 
                        mode = mode,
                        language = language,
                        isWhisperMode = isWhisperMode
                    )
                } else {
                    // Smart formatting is already applied
                    enhancedText
                }
                
                // Insert the text
                currentInputConnection?.commitText(finalText, 1)
                lastTranscribedText = finalText
                
                // Track analytics (respecting privacy mode)
                if (!isPrivacyMode) {
                    val duration = (System.currentTimeMillis() - recordingStartTime) / 1000f
                    val appPackage = currentInputEditorInfo?.packageName
                    analyticsManager.trackTranscription(
                        rawText = transcribedText,
                        finalText = finalText,
                        duration = duration,
                        appPackage = appPackage,
                        mode = mode
                    )
                }
                
                // Show word count in status
                val wordCount = finalText.split("\\s+".toRegex()).filter { it.isNotEmpty() }.size
                showStatus("Done! ($wordCount words)")
                updateUI(RecordingState.IDLE)
                
                // Success vibration
                vibrate(pattern = longArrayOf(0, 50, 50, 50))
                
                // Clean up audio file
                audioFile.delete()
                
            } catch (e: Exception) {
                e.printStackTrace()
                showStatus("Error: ${e.message}")
                updateUI(RecordingState.IDLE)
            } finally {
                isRecording = false
            }
        }
    }
    
    private fun shouldEnhanceText(mode: GroqProcessor.TextMode): Boolean {
        // Only enhance for formal/email modes or if user has specific preferences
        return mode == GroqProcessor.TextMode.EMAIL || 
               mode == GroqProcessor.TextMode.FORMAL
    }
    
    private fun updateUI(state: RecordingState) {
        handler.post {
            when (state) {
                RecordingState.IDLE -> {
                    progressBar.visibility = View.GONE
                    // Update touch area background for idle state
                    keyboardView.findViewById<View>(R.id.voice_touch_area)?.setBackgroundResource(R.drawable.glass_background)
                }
                RecordingState.RECORDING -> {
                    progressBar.visibility = View.GONE // Hide progress during recording for cleaner look
                    // Update touch area background for recording state
                    keyboardView.findViewById<View>(R.id.voice_touch_area)?.setBackgroundResource(R.drawable.glass_button_recording)
                }
                RecordingState.PROCESSING -> {
                    progressBar.visibility = View.VISIBLE // Show progress only when processing
                    // Update touch area background for processing state
                    keyboardView.findViewById<View>(R.id.voice_touch_area)?.setBackgroundResource(R.drawable.glass_button_processing)
                }
            }
        }
    }
    
    private fun showStatus(message: String) {
        // Status text is now hidden, so we don't update it
        // Keep the method for compatibility but do nothing
    }
    
    private fun hasRecordPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            this, 
            Manifest.permission.RECORD_AUDIO
        ) == PackageManager.PERMISSION_GRANTED
    }
    
    private fun vibrate(duration: Long = 50, pattern: LongArray? = null) {
        val vibrator = getSystemService(android.os.Vibrator::class.java)
        if (pattern != null) {
            vibrator?.vibrate(pattern, -1)
        } else {
            vibrator?.vibrate(duration)
        }
    }
    
    override fun onUpdateSelection(
        oldSelStart: Int, oldSelEnd: Int,
        newSelStart: Int, newSelEnd: Int,
        candidatesStart: Int, candidatesEnd: Int
    ) {
        super.onUpdateSelection(oldSelStart, oldSelEnd, newSelStart, newSelEnd, candidatesStart, candidatesEnd)
        
        // Check if text was edited after our transcription
        if (lastTranscribedText.isNotEmpty() && newSelStart > 0) {
            val currentText = currentInputConnection?.getTextBeforeCursor(1000, 0)?.toString() ?: ""
            
            // Simple check if text was modified
            if (currentText.isNotEmpty() && !currentText.endsWith(lastTranscribedText)) {
                // Track the correction
                val correctedText = currentText.substringAfter(
                    currentText.removeSuffix(lastTranscribedText)
                ).take(lastTranscribedText.length)
                
                if (correctedText != lastTranscribedText) {
                    analyticsManager.trackCorrection(
                        original = lastTranscribedText,
                        corrected = correctedText,
                        context = currentText
                    )
                }
                lastTranscribedText = ""  // Reset after tracking
            }
        }
    }
    
    override fun onDestroy() {
        super.onDestroy()
        mediaRecorder?.release()
        analyticsManager.destroy()
        mainScope.cancel()
    }
    
    enum class RecordingState {
        IDLE, RECORDING, PROCESSING
    }
    
    private fun getCurrentLocale(): Locale {
        val prefs = getSharedPreferences("voxtype_prefs", MODE_PRIVATE)
        val language = prefs.getString("selected_language", "en_IN")
        
        return when (language) {
            "en_IN" -> Locale("en", "IN")
            "en_US" -> Locale("en", "US")
            "en_GB" -> Locale("en", "GB")
            "hi_IN" -> Locale("hi", "IN")
            else -> Locale("en", "IN")
        }
    }
    
    private fun getSelectedLanguage(): String {
        val prefs = getSharedPreferences("voxtype_prefs", MODE_PRIVATE)
        selectedLanguage = prefs.getString("voice_language", "auto") ?: "auto"
        return when (selectedLanguage) {
            "auto" -> "auto"
            "english" -> "en"
            "hindi" -> "hi"
            "mixed" -> "auto"  // Use auto-detection for mixed language
            else -> "auto"
        }
    }
    
    private fun loadFeatureSettings() {
        mainScope.launch {
            try {
                isWhisperMode = advancedSettings.isWhisperModeEnabled()
                isPrivacyMode = advancedSettings.isPrivacyModeEnabled()
                selectedLanguage = advancedSettings.getVoiceLanguage()
                
                // Initialize default settings if needed
                advancedSettings.initializeDefaultSettings()
            } catch (e: Exception) {
                // Fallback to SharedPreferences if database isn't ready
                val prefs = getSharedPreferences("voxtype_prefs", MODE_PRIVATE)
                isWhisperMode = prefs.getBoolean("whisper_mode", false)
                isPrivacyMode = prefs.getBoolean("privacy_mode", false)
                selectedLanguage = prefs.getString("voice_language", "auto") ?: "auto"
            }
        }
    }
    
    private fun toggleWhisperMode() {
        isWhisperMode = !isWhisperMode
        val prefs = getSharedPreferences("voxtype_prefs", MODE_PRIVATE)
        prefs.edit().putBoolean("whisper_mode", isWhisperMode).apply()
        
        showStatus(if (isWhisperMode) "Whisper mode ON" else "Whisper mode OFF")
        updateWhisperModeUI()
    }
    
    private fun updateWhisperModeUI() {
        // Update UI elements to show whisper mode state
        handler.post {
            val whisperIndicator = keyboardView.findViewById<View>(R.id.whisper_indicator)
            whisperIndicator?.visibility = if (isWhisperMode) View.VISIBLE else View.GONE
            
            // Update voice button appearance for whisper mode
            if (isWhisperMode) {
                voiceButton.alpha = 0.7f  // Dimmed for whisper mode
            } else {
                voiceButton.alpha = 1.0f
            }
        }
    }
    
    private fun cycleLanguage() {
        val languages = listOf("auto", "english", "hindi", "mixed")
        val currentIndex = languages.indexOf(selectedLanguage)
        val nextIndex = (currentIndex + 1) % languages.size
        selectedLanguage = languages[nextIndex]
        
        val prefs = getSharedPreferences("voxtype_prefs", MODE_PRIVATE)
        prefs.edit().putString("voice_language", selectedLanguage).apply()
        
        val displayName = when (selectedLanguage) {
            "auto" -> "Auto-detect"
            "english" -> "English"
            "hindi" -> "हिन्दी"
            "mixed" -> "Mixed (En+Hi)"
            else -> "Auto-detect"
        }
        
        showStatus("Language: $displayName")
        updateLanguageButton()
    }
    
    private fun updateLanguageButton() {
        handler.post {
            val languageButton = keyboardView.findViewById<Button>(R.id.language_button)
            val emoji = when (selectedLanguage) {
                "english" -> "🇺🇸"
                "hindi" -> "🇮🇳"
                "mixed" -> "🌏"
                else -> "🌐"
            }
            languageButton?.text = emoji
        }
    }
    
    private fun detectTextMode(): GroqProcessor.TextMode {
        val editorInfo = currentInputEditorInfo
        val packageName = editorInfo?.packageName ?: ""
        val inputType = editorInfo?.inputType ?: 0
        val className = editorInfo?.fieldName ?: ""
        
        // Enhanced app detection patterns
        return when {
            // Email applications
            packageName.contains("gmail") || 
            packageName.contains("mail") || 
            packageName.contains("outlook") ||
            packageName.contains("protonmail") ||
            packageName.contains("yahoo") ||
            packageName.contains("spark") -> GroqProcessor.TextMode.EMAIL
            
            // Chat/Messaging applications
            packageName.contains("whatsapp") || 
            packageName.contains("telegram") || 
            packageName.contains("messages") ||
            packageName.contains("sms") ||
            packageName.contains("chat") ||
            packageName.contains("messenger") ||
            packageName.contains("signal") ||
            packageName.contains("discord") ||
            packageName.contains("instagram") ||
            packageName.contains("snapchat") ||
            packageName.contains("viber") ||
            packageName.contains("wechat") -> GroqProcessor.TextMode.CHAT
            
            // Professional/Business applications
            packageName.contains("slack") || 
            packageName.contains("teams") || 
            packageName.contains("linkedin") ||
            packageName.contains("zoom") ||
            packageName.contains("notion") ||
            packageName.contains("asana") ||
            packageName.contains("trello") ||
            packageName.contains("jira") -> GroqProcessor.TextMode.FORMAL
            
            // Document/Note taking apps
            packageName.contains("docs") ||
            packageName.contains("notes") ||
            packageName.contains("onenote") ||
            packageName.contains("evernote") ||
            packageName.contains("obsidian") ||
            packageName.contains("bear") -> GroqProcessor.TextMode.FORMAL
            
            // Social media (casual)
            packageName.contains("twitter") ||
            packageName.contains("facebook") ||
            packageName.contains("reddit") ||
            packageName.contains("tiktok") ||
            packageName.contains("youtube") -> GroqProcessor.TextMode.CHAT
            
            // Input type based detection
            inputType and android.text.InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS != 0 -> 
                GroqProcessor.TextMode.EMAIL
            
            inputType and android.text.InputType.TYPE_TEXT_VARIATION_SHORT_MESSAGE != 0 -> 
                GroqProcessor.TextMode.CHAT
                
            inputType and android.text.InputType.TYPE_TEXT_VARIATION_LONG_MESSAGE != 0 -> 
                GroqProcessor.TextMode.FORMAL
            
            // Field name based detection
            className.contains("email", true) -> GroqProcessor.TextMode.EMAIL
            className.contains("message", true) || className.contains("chat", true) -> GroqProcessor.TextMode.CHAT
            className.contains("subject", true) || className.contains("title", true) -> GroqProcessor.TextMode.FORMAL
            
            else -> GroqProcessor.TextMode.GENERAL
        }
    }
}