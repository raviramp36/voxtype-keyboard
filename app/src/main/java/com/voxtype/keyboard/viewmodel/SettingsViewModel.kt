package com.voxtype.keyboard.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.voxtype.keyboard.database.VoxTypeDatabase
import com.voxtype.keyboard.database.entities.SettingsPreference
import com.voxtype.keyboard.repository.SettingsRepository
import kotlinx.coroutines.launch

class SettingsViewModel(application: Application) : AndroidViewModel(application) {
    
    private val repository: SettingsRepository
    
    val allPreferences: LiveData<List<SettingsPreference>>
    
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    
    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage
    
    init {
        val database = VoxTypeDatabase.getInstance(application)
        repository = SettingsRepository(database)
        allPreferences = repository.getAllPreferences().asLiveData()
        initializeDefaultSettings()
    }
    
    private fun initializeDefaultSettings() {
        viewModelScope.launch {
            try {
                // Initialize default settings if they don't exist
                val defaults = mapOf(
                    SettingsRepository.SettingsKeys.VOICE_ENABLED to "true",
                    SettingsRepository.SettingsKeys.AUTO_PUNCTUATION to "true",
                    SettingsRepository.SettingsKeys.LANGUAGE_DETECTION to "true",
                    SettingsRepository.SettingsKeys.VIBRATION_ENABLED to "true",
                    SettingsRepository.SettingsKeys.SOUND_ENABLED to "false",
                    SettingsRepository.SettingsKeys.THEME_MODE to "system",
                    SettingsRepository.SettingsKeys.RECORDING_QUALITY to "high",
                    SettingsRepository.SettingsKeys.AUTO_SAVE_TRANSCRIPTIONS to "true",
                    SettingsRepository.SettingsKeys.PRIVACY_MODE to "false",
                    SettingsRepository.SettingsKeys.ANALYTICS_ENABLED to "true"
                )
                
                defaults.forEach { (key, value) ->
                    val existing = repository.getPreference(key)
                    if (existing == null) {
                        repository.setPreference(key, value)
                    }
                }
            } catch (e: Exception) {
                _errorMessage.value = "Failed to initialize default settings: ${e.message}"
            }
        }
    }
    
    fun setBooleanSetting(key: String, value: Boolean) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                repository.setBooleanSetting(key, value)
                _errorMessage.value = null
            } catch (e: Exception) {
                _errorMessage.value = "Failed to save setting: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    fun getBooleanSetting(key: String, defaultValue: Boolean = false): LiveData<Boolean> {
        val liveData = MutableLiveData<Boolean>()
        viewModelScope.launch {
            try {
                val value = repository.getBooleanSetting(key, defaultValue)
                liveData.value = value
            } catch (e: Exception) {
                _errorMessage.value = "Failed to load setting: ${e.message}"
                liveData.value = defaultValue
            }
        }
        return liveData
    }
    
    fun setStringSetting(key: String, value: String) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                repository.setStringSetting(key, value)
                _errorMessage.value = null
            } catch (e: Exception) {
                _errorMessage.value = "Failed to save setting: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    fun getStringSetting(key: String, defaultValue: String = ""): LiveData<String> {
        val liveData = MutableLiveData<String>()
        viewModelScope.launch {
            try {
                val value = repository.getStringSetting(key, defaultValue)
                liveData.value = value
            } catch (e: Exception) {
                _errorMessage.value = "Failed to load setting: ${e.message}"
                liveData.value = defaultValue
            }
        }
        return liveData
    }
    
    fun setIntSetting(key: String, value: Int) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                repository.setIntSetting(key, value)
                _errorMessage.value = null
            } catch (e: Exception) {
                _errorMessage.value = "Failed to save setting: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    fun getIntSetting(key: String, defaultValue: Int = 0): LiveData<Int> {
        val liveData = MutableLiveData<Int>()
        viewModelScope.launch {
            try {
                val value = repository.getIntSetting(key, defaultValue)
                liveData.value = value
            } catch (e: Exception) {
                _errorMessage.value = "Failed to load setting: ${e.message}"
                liveData.value = defaultValue
            }
        }
        return liveData
    }
    
    fun setFloatSetting(key: String, value: Float) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                repository.setFloatSetting(key, value)
                _errorMessage.value = null
            } catch (e: Exception) {
                _errorMessage.value = "Failed to save setting: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    fun getFloatSetting(key: String, defaultValue: Float = 0f): LiveData<Float> {
        val liveData = MutableLiveData<Float>()
        viewModelScope.launch {
            try {
                val value = repository.getFloatSetting(key, defaultValue)
                liveData.value = value
            } catch (e: Exception) {
                _errorMessage.value = "Failed to load setting: ${e.message}"
                liveData.value = defaultValue
            }
        }
        return liveData
    }
    
    fun resetToDefaults() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                repository.deleteAllPreferences()
                initializeDefaultSettings()
                _errorMessage.value = null
            } catch (e: Exception) {
                _errorMessage.value = "Failed to reset settings: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    fun clearError() {
        _errorMessage.value = null
    }
}