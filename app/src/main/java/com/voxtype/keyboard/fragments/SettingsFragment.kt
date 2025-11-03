package com.voxtype.keyboard.fragments

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText
import com.voxtype.keyboard.GroqProcessor
import com.voxtype.keyboard.R
import com.voxtype.keyboard.StatsActivity

class SettingsFragment : Fragment() {
    
    private lateinit var groqProcessor: GroqProcessor
    private lateinit var apiKeyInput: TextInputEditText
    private lateinit var apiUrlInput: TextInputEditText
    private lateinit var saveButton: Button
    private lateinit var enableKeyboardButton: Button
    private lateinit var selectKeyboardButton: Button
    private lateinit var statusText: TextView
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        groqProcessor = GroqProcessor(requireContext())
        
        // Initialize views
        initializeViews(view)
        
        // Load saved settings
        loadSettings()
        
        // Check permissions
        checkPermissions()
        
        // Check keyboard status
        updateKeyboardStatus()
    }
    
    private fun initializeViews(view: View) {
        apiKeyInput = view.findViewById(R.id.api_key_input)
        apiUrlInput = view.findViewById(R.id.api_url_input)
        saveButton = view.findViewById(R.id.save_button)
        enableKeyboardButton = view.findViewById(R.id.enable_keyboard_button)
        selectKeyboardButton = view.findViewById(R.id.select_keyboard_button)
        statusText = view.findViewById(R.id.status_text)
        
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
        
        // Analytics button
        view.findViewById<Button>(R.id.view_analytics_button).setOnClickListener {
            val intent = Intent(requireContext(), StatsActivity::class.java)
            startActivity(intent)
        }
    }
    
    private fun loadSettings() {
        val prefs = requireContext().getSharedPreferences("voxtype_prefs", android.content.Context.MODE_PRIVATE)
        apiKeyInput.setText(prefs.getString("groq_api_key", ""))
        apiUrlInput.setText(prefs.getString("groq_base_url", "https://api.groq.com/"))
    }
    
    private fun saveSettings() {
        val apiKey = apiKeyInput.text.toString()
        val apiUrl = apiUrlInput.text.toString()
        
        if (apiKey.isEmpty()) {
            Toast.makeText(requireContext(), "Please enter Groq API Key", Toast.LENGTH_SHORT).show()
            return
        }
        
        if (apiUrl.isEmpty()) {
            Toast.makeText(requireContext(), "Please enter Groq API URL", Toast.LENGTH_SHORT).show()
            return
        }
        
        groqProcessor.saveSettings(apiKey, apiUrl)
        Toast.makeText(requireContext(), getString(R.string.settings_saved), Toast.LENGTH_SHORT).show()
    }
    
    private fun checkPermissions() {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.RECORD_AUDIO) 
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.RECORD_AUDIO),
                PERMISSION_REQUEST_CODE
            )
        }
    }
    
    private fun updateKeyboardStatus() {
        val imm = requireContext().getSystemService(android.content.Context.INPUT_METHOD_SERVICE) as InputMethodManager
        
        // For Android 14+, use InputMethodManager to check enabled status
        val isEnabled = try {
            imm.enabledInputMethodList.any { 
                it.packageName == requireContext().packageName 
            }
        } catch (e: Exception) {
            false
        }
        
        val currentMethod = try {
            Settings.Secure.getString(
                requireContext().contentResolver,
                Settings.Secure.DEFAULT_INPUT_METHOD
            )
        } catch (e: Exception) {
            ""
        }
        val isSelected = currentMethod?.contains(requireContext().packageName) == true
        
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
        val imm = requireContext().getSystemService(android.content.Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showInputMethodPicker()
    }
    
    override fun onResume() {
        super.onResume()
        updateKeyboardStatus()
    }
    
    companion object {
        private const val PERMISSION_REQUEST_CODE = 100
    }
}