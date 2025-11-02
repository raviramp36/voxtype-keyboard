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
    private lateinit var voiceButton: Button
    private lateinit var statusText: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var groqProcessor: GroqProcessor
    private lateinit var analyticsManager: AnalyticsManager
    
    private var mediaRecorder: MediaRecorder? = null
    private var audioFile: File? = null
    private var isRecording = false
    private var recordingStartTime = 0L
    private var lastTranscribedText = ""
    private val mainScope = CoroutineScope(Dispatchers.Main + Job())
    private val handler = Handler(Looper.getMainLooper())
    
    override fun onCreate() {
        super.onCreate()
        groqProcessor = GroqProcessor(this)
        analyticsManager = AnalyticsManager(this)
        
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
        voiceButton = keyboardView.findViewById(R.id.voice_button)
        statusText = keyboardView.findViewById(R.id.status_text)
        progressBar = keyboardView.findViewById(R.id.progress_bar)
        
        // Set up the big voice button
        voiceButton.setOnClickListener {
            toggleRecording()
        }
        
        // Long press for continuous recording
        voiceButton.setOnLongClickListener {
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
        
        keyboardView.findViewById<View>(R.id.stats_button)?.setOnClickListener {
            val intent = android.content.Intent(this, StatsActivity::class.java).apply {
                flags = android.content.Intent.FLAG_ACTIVITY_NEW_TASK
            }
            startActivity(intent)
        }
        
        keyboardView.findViewById<View>(R.id.language_button)?.setOnClickListener {
            switchToNextSubtype()
        }
        
        keyboardView.findViewById<View>(R.id.keyboard_button)?.setOnClickListener {
            val imm = getSystemService(INPUT_METHOD_SERVICE) as android.view.inputmethod.InputMethodManager
            imm.showInputMethodPicker()
        }
        
        keyboardView.findViewById<View>(R.id.clear_button)?.setOnClickListener {
            currentInputConnection?.deleteSurroundingText(1000, 1000)
        }
    }
    
    private fun switchToNextSubtype() {
        val imeManager = getSystemService(INPUT_METHOD_SERVICE) as android.view.inputmethod.InputMethodManager
        imeManager.switchToNextInputMethod(window.window?.attributes?.token, false)
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
                
                // Detect language
                val language = when (getCurrentLocale().language) {
                    "hi" -> "hi"  // Hindi
                    else -> "en"  // English (all variants)
                }
                
                // Transcribe with Whisper (already includes punctuation!)
                val transcribedText = groqProcessor.transcribeAudio(
                    audioFile = audioFile,
                    language = language,
                    prompt = contextBefore  // Provide context for better accuracy
                )
                
                // Detect mode for potential enhancement
                val mode = detectTextMode()
                
                // Optional: Further enhance with LLM if needed
                val finalText = if (shouldEnhanceText(mode)) {
                    showStatus("Enhancing...")
                    groqProcessor.processText(transcribedText, contextBefore, mode)
                } else {
                    // Whisper output is already good enough for most cases
                    transcribedText
                }
                
                // Insert the text
                currentInputConnection?.commitText(finalText, 1)
                lastTranscribedText = finalText
                
                // Track analytics
                val duration = (System.currentTimeMillis() - recordingStartTime) / 1000f
                val appPackage = currentInputEditorInfo?.packageName
                analyticsManager.trackTranscription(
                    rawText = transcribedText,
                    finalText = finalText,
                    duration = duration,
                    appPackage = appPackage,
                    mode = mode
                )
                
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
                    voiceButton.text = "🎤"
                    voiceButton.setBackgroundResource(R.drawable.button_idle)
                    progressBar.visibility = View.GONE
                }
                RecordingState.RECORDING -> {
                    voiceButton.text = "🔴"
                    voiceButton.setBackgroundResource(R.drawable.button_recording)
                    progressBar.visibility = View.VISIBLE
                }
                RecordingState.PROCESSING -> {
                    voiceButton.text = "⏳"
                    voiceButton.setBackgroundResource(R.drawable.button_processing)
                    progressBar.visibility = View.VISIBLE
                }
            }
        }
    }
    
    private fun showStatus(message: String) {
        handler.post {
            statusText.text = message
            statusText.visibility = View.VISIBLE
            
            // Auto-hide status after 3 seconds
            handler.postDelayed({
                statusText.visibility = View.GONE
            }, 3000)
        }
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
    
    private fun detectTextMode(): GroqProcessor.TextMode {
        val editorInfo = currentInputEditorInfo
        val packageName = editorInfo?.packageName ?: ""
        val inputType = editorInfo?.inputType ?: 0
        
        return when {
            packageName.contains("gmail") || 
            packageName.contains("mail") || 
            packageName.contains("outlook") -> GroqProcessor.TextMode.EMAIL
            
            packageName.contains("whatsapp") || 
            packageName.contains("telegram") || 
            packageName.contains("messages") ||
            packageName.contains("sms") ||
            packageName.contains("chat") -> GroqProcessor.TextMode.CHAT
            
            packageName.contains("slack") || 
            packageName.contains("teams") || 
            packageName.contains("linkedin") -> GroqProcessor.TextMode.FORMAL
            
            inputType and android.text.InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS != 0 -> 
                GroqProcessor.TextMode.EMAIL
            
            inputType and android.text.InputType.TYPE_TEXT_VARIATION_SHORT_MESSAGE != 0 -> 
                GroqProcessor.TextMode.CHAT
            
            else -> GroqProcessor.TextMode.GENERAL
        }
    }
}