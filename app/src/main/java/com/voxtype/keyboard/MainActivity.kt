package com.voxtype.keyboard

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.Settings
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    
    private lateinit var groqProcessor: GroqProcessor
    private lateinit var apiKeyInput: TextInputEditText
    private lateinit var apiUrlInput: TextInputEditText
    private lateinit var saveButton: Button
    private lateinit var enableKeyboardButton: Button
    private lateinit var selectKeyboardButton: Button
    private lateinit var statusText: TextView
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        groqProcessor = GroqProcessor(this)
        
        // Initialize views
        initializeViews()
        
        // Load saved settings
        loadSettings()
        
        // Check permissions
        checkPermissions()
        
        // Check keyboard status
        updateKeyboardStatus()
    }
    
    private fun initializeViews() {
        apiKeyInput = findViewById(R.id.api_key_input)
        apiUrlInput = findViewById(R.id.api_url_input)
        saveButton = findViewById(R.id.save_button)
        enableKeyboardButton = findViewById(R.id.enable_keyboard_button)
        selectKeyboardButton = findViewById(R.id.select_keyboard_button)
        statusText = findViewById(R.id.status_text)
        
        // Set up click listeners
        saveButton.setOnClickListener {
            saveSettings()
        }
        
        enableKeyboardButton.setOnClickListener {
            openKeyboardSettings()
        }
        
        selectKeyboardButton.setOnClickListener {
            showInputMethodPicker()
        }
    }
    
    private fun loadSettings() {
        val prefs = getSharedPreferences("voxtype_prefs", MODE_PRIVATE)
        apiKeyInput.setText(prefs.getString("groq_api_key", ""))
        apiUrlInput.setText(prefs.getString("groq_base_url", "https://api.groq.com/"))
    }
    
    private fun saveSettings() {
        val apiKey = apiKeyInput.text.toString()
        val apiUrl = apiUrlInput.text.toString()
        
        if (apiKey.isEmpty()) {
            Toast.makeText(this, "Please enter Groq API Key", Toast.LENGTH_SHORT).show()
            return
        }
        
        if (apiUrl.isEmpty()) {
            Toast.makeText(this, "Please enter Groq API URL", Toast.LENGTH_SHORT).show()
            return
        }
        
        groqProcessor.saveSettings(apiKey, apiUrl)
        Toast.makeText(this, getString(R.string.settings_saved), Toast.LENGTH_SHORT).show()
    }
    
    private fun checkPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) 
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.RECORD_AUDIO),
                PERMISSION_REQUEST_CODE
            )
        }
    }
    
    private fun updateKeyboardStatus() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        
        // For Android 14+, use InputMethodManager to check enabled status
        val isEnabled = try {
            imm.enabledInputMethodList.any { 
                it.packageName == packageName 
            }
        } catch (e: Exception) {
            false
        }
        
        val currentMethod = try {
            Settings.Secure.getString(
                contentResolver,
                Settings.Secure.DEFAULT_INPUT_METHOD
            )
        } catch (e: Exception) {
            ""
        }
        val isSelected = currentMethod?.contains(packageName) == true
        
        val status = when {
            isSelected -> "✅ VoxType is active and ready!"
            isEnabled -> "⚠️ VoxType is enabled but not selected"
            else -> "❌ VoxType needs to be enabled"
        }
        
        statusText.text = status
        
        enableKeyboardButton.isEnabled = !isEnabled
        selectKeyboardButton.isEnabled = isEnabled && !isSelected
    }
    
    private fun openKeyboardSettings() {
        val intent = Intent(Settings.ACTION_INPUT_METHOD_SETTINGS)
        startActivity(intent)
    }
    
    private fun showInputMethodPicker() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showInputMethodPicker()
    }
    
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Microphone permission granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Microphone permission is required for voice input", Toast.LENGTH_LONG).show()
            }
        }
    }
    
    override fun onResume() {
        super.onResume()
        updateKeyboardStatus()
    }
    
    companion object {
        private const val PERMISSION_REQUEST_CODE = 100
    }
}