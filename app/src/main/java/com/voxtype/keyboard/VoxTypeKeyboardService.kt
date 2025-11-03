package com.voxtype.keyboard

import android.Manifest
import android.content.pm.PackageManager
import android.inputmethodservice.InputMethodService
import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.voxtype.keyboard.database.TranscriptionEntry
import com.voxtype.keyboard.database.VoxTypeDatabase
import kotlinx.coroutines.*
import java.util.Date
import java.util.Locale

class VoxTypeKeyboardService : InputMethodService() {
    
    private lateinit var keyboardView: View
    private lateinit var voiceButton: Button
    private lateinit var statusText: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var speechRecognizer: SpeechRecognizer
    private lateinit var groqProcessor: GroqProcessor
    private lateinit var database: VoxTypeDatabase
    
    private var isRecording = false
    private val mainScope = CoroutineScope(Dispatchers.Main + Job())
    private val handler = Handler(Looper.getMainLooper())
    private var currentRecordingStartTime: Long = 0
    
    override fun onCreate() {
        super.onCreate()
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this)
        groqProcessor = GroqProcessor(this)
        database = VoxTypeDatabase.getInstance(this)
        setupSpeechRecognizer()
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
        // Make sure the keyboard view is visible
        keyboardView?.visibility = View.VISIBLE
        updateUI(RecordingState.IDLE)
    }
    
    override fun onFinishInputView(finishingInput: Boolean) {
        super.onFinishInputView(finishingInput)
        // Clean up when keyboard is hidden
        if (isRecording) {
            stopRecording()
        }
    }
    
    private fun setupQuickActions() {
        keyboardView.findViewById<View>(R.id.settings_button)?.setOnClickListener {
            // Open settings activity
            val intent = android.content.Intent(this, MainActivity::class.java).apply {
                flags = android.content.Intent.FLAG_ACTIVITY_NEW_TASK
            }
            startActivity(intent)
        }
        
        keyboardView.findViewById<View>(R.id.language_button)?.setOnClickListener {
            // Switch language/locale
            switchToNextSubtype()
        }
        
        keyboardView.findViewById<View>(R.id.keyboard_button)?.setOnClickListener {
            // Switch to next input method
            val imm = getSystemService(INPUT_METHOD_SERVICE) as android.view.inputmethod.InputMethodManager
            imm.showInputMethodPicker()
        }
        
        keyboardView.findViewById<View>(R.id.backspace_button)?.setOnClickListener {
            // Handle backspace functionality
            handleBackspace()
        }
        
        keyboardView.findViewById<View>(R.id.clear_button)?.setOnClickListener {
            // Clear current text
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
        
        isRecording = true
        currentRecordingStartTime = System.currentTimeMillis()
        updateUI(RecordingState.RECORDING)
        
        // Support for Indian English
        val currentLocale = getCurrentLocale()
        
        val intent = android.content.Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, currentLocale.toString())
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE, currentLocale.toString())
            putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true)
            putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1)
        }
        
        speechRecognizer.startListening(intent)
        showStatus("Listening...")
        
        // Vibrate for feedback
        vibrate()
    }
    
    private fun stopRecording() {
        isRecording = false
        updateUI(RecordingState.PROCESSING)
        speechRecognizer.stopListening()
        showStatus("Processing...")
    }
    
    private fun startContinuousRecording() {
        // For hold-to-talk mode
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
    
    private fun setupSpeechRecognizer() {
        speechRecognizer.setRecognitionListener(object : RecognitionListener {
            override fun onReadyForSpeech(params: Bundle?) {
                showStatus("Speak now...")
            }
            
            override fun onBeginningOfSpeech() {
                updateUI(RecordingState.LISTENING)
            }
            
            override fun onRmsChanged(rmsdB: Float) {
                // Update voice level indicator if needed
            }
            
            override fun onBufferReceived(buffer: ByteArray?) {}
            
            override fun onEndOfSpeech() {
                updateUI(RecordingState.PROCESSING)
                showStatus("Processing speech...")
            }
            
            override fun onError(error: Int) {
                val errorMessage = when (error) {
                    SpeechRecognizer.ERROR_NETWORK -> "Network error"
                    SpeechRecognizer.ERROR_NO_MATCH -> "No speech detected"
                    SpeechRecognizer.ERROR_AUDIO -> "Audio error"
                    else -> "Recognition error"
                }
                showStatus(errorMessage)
                updateUI(RecordingState.IDLE)
                isRecording = false
            }
            
            override fun onResults(results: Bundle?) {
                results?.let {
                    val matches = it.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                    if (!matches.isNullOrEmpty()) {
                        val transcribedText = matches[0]
                        processWithRAG(transcribedText)
                    }
                }
            }
            
            override fun onPartialResults(partialResults: Bundle?) {
                partialResults?.let {
                    val matches = it.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                    if (!matches.isNullOrEmpty()) {
                        showStatus("\"${matches[0]}\"")
                    }
                }
            }
            
            override fun onEvent(eventType: Int, params: Bundle?) {}
        })
    }
    
    private fun processWithRAG(text: String) {
        mainScope.launch {
            try {
                updateUI(RecordingState.PROCESSING)
                showStatus("Enhancing with Groq AI...")
                
                // Get more context for better formatting
                val contextBefore = currentInputConnection?.getTextBeforeCursor(500, 0)?.toString() ?: ""
                val contextAfter = currentInputConnection?.getTextAfterCursor(100, 0)?.toString() ?: ""
                
                // Combine context for better understanding
                val fullContext = buildString {
                    if (contextBefore.isNotEmpty()) {
                        append(contextBefore)
                    }
                    if (contextAfter.isNotEmpty()) {
                        append(" [CURSOR] ")
                        append(contextAfter)
                    }
                }
                
                // Detect mode based on current app/field
                val mode = detectTextMode()
                
                // Process with Groq for intelligent formatting
                val enhancedText = groqProcessor.processText(text, fullContext, mode)
                
                // Insert the enhanced text
                currentInputConnection?.commitText(enhancedText, 1)
                
                // Save transcription to history
                saveTranscriptionToHistory(text, enhancedText, mode)
                
                showStatus("Done!")
                updateUI(RecordingState.IDLE)
                
                // Success vibration
                vibrate(pattern = longArrayOf(0, 50, 50, 50))
                
            } catch (e: Exception) {
                showStatus("Error: ${e.message}")
                // Fallback to original text
                currentInputConnection?.commitText(text, 1)
                updateUI(RecordingState.IDLE)
            } finally {
                isRecording = false
            }
        }
    }
    
    private fun updateUI(state: RecordingState) {
        handler.post {
            when (state) {
                RecordingState.IDLE -> {
                    voiceButton.text = "🎤"
                    voiceButton.setBackgroundResource(R.drawable.glass_button_idle)
                    progressBar.visibility = View.GONE
                }
                RecordingState.RECORDING -> {
                    voiceButton.text = "🔴"
                    voiceButton.setBackgroundResource(R.drawable.glass_button_recording)
                    progressBar.visibility = View.VISIBLE
                }
                RecordingState.LISTENING -> {
                    voiceButton.text = "👂"
                    voiceButton.setBackgroundResource(R.drawable.glass_button_listening)
                    progressBar.visibility = View.VISIBLE
                }
                RecordingState.PROCESSING -> {
                    voiceButton.text = "⏳"
                    voiceButton.setBackgroundResource(R.drawable.glass_button_processing)
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
    
    override fun onDestroy() {
        super.onDestroy()
        speechRecognizer.destroy()
        mainScope.cancel()
    }
    
    enum class RecordingState {
        IDLE, RECORDING, LISTENING, PROCESSING
    }
    
    private fun getCurrentLocale(): Locale {
        // Check shared preferences for selected language
        val prefs = getSharedPreferences("voxtype_prefs", MODE_PRIVATE)
        val language = prefs.getString("selected_language", "en_IN")
        
        return when (language) {
            "en_IN" -> Locale("en", "IN") // Indian English
            "en_US" -> Locale("en", "US") // US English
            "en_GB" -> Locale("en", "GB") // British English
            "hi_IN" -> Locale("hi", "IN") // Hindi
            else -> Locale("en", "IN") // Default to Indian English
        }
    }
    
    private fun detectTextMode(): GroqProcessor.TextMode {
        // Get current app package name and input field info
        val editorInfo = currentInputEditorInfo
        val packageName = editorInfo?.packageName ?: ""
        val inputType = editorInfo?.inputType ?: 0
        
        // Detect based on app
        return when {
            // Email apps
            packageName.contains("gmail") || 
            packageName.contains("mail") || 
            packageName.contains("outlook") -> GroqProcessor.TextMode.EMAIL
            
            // Messaging apps
            packageName.contains("whatsapp") || 
            packageName.contains("telegram") || 
            packageName.contains("messages") ||
            packageName.contains("sms") ||
            packageName.contains("chat") -> GroqProcessor.TextMode.CHAT
            
            // Professional apps
            packageName.contains("slack") || 
            packageName.contains("teams") || 
            packageName.contains("linkedin") -> GroqProcessor.TextMode.FORMAL
            
            // Check input type hints
            inputType and android.text.InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS != 0 -> 
                GroqProcessor.TextMode.EMAIL
            
            inputType and android.text.InputType.TYPE_TEXT_VARIATION_SHORT_MESSAGE != 0 -> 
                GroqProcessor.TextMode.CHAT
            
            // Default
            else -> GroqProcessor.TextMode.GENERAL
        }
    }
    
    private fun handleBackspace() {
        val ic = currentInputConnection ?: return
        
        // Get text before cursor to determine what to delete
        val selectedText = ic.getSelectedText(0)
        
        if (!selectedText.isNullOrEmpty()) {
            // Delete selected text
            ic.commitText("", 1)
        } else {
            // Delete character before cursor
            ic.deleteSurroundingText(1, 0)
        }
        
        // Provide haptic feedback
        vibrate(30)
    }
    
    private fun saveTranscriptionToHistory(
        rawTranscription: String,
        enhancedText: String,
        mode: GroqProcessor.TextMode
    ) {
        mainScope.launch {
            try {
                val editorInfo = currentInputEditorInfo
                val appPackage = editorInfo?.packageName ?: ""
                val duration = if (currentRecordingStartTime > 0) {
                    (System.currentTimeMillis() - currentRecordingStartTime) / 1000f
                } else {
                    null
                }
                
                val transcriptionEntry = TranscriptionEntry(
                    timestamp = Date(),
                    rawTranscription = rawTranscription,
                    finalText = enhancedText,
                    wordCount = enhancedText.split("\\s+".toRegex()).size,
                    characterCount = enhancedText.length,
                    duration = duration,
                    appPackage = appPackage,
                    textMode = mode.name
                )
                
                database.transcriptionDao().insert(transcriptionEntry)
                
            } catch (e: Exception) {
                // Log error but don't interrupt user experience
                android.util.Log.e("VoxTypeKeyboard", "Failed to save transcription", e)
            }
        }
    }
}