package com.voxtype.keyboard.repository

import com.voxtype.keyboard.database.VoxTypeDatabase
import com.voxtype.keyboard.database.entities.SettingsPreference
import kotlinx.coroutines.flow.Flow
class SettingsRepository(
    private val database: VoxTypeDatabase
) {
    private val settingsDao = database.settingsPreferenceDao()
    
    fun getAllPreferences(): Flow<List<SettingsPreference>> = settingsDao.getAllPreferences()
    
    suspend fun setPreference(key: String, value: String) = 
        settingsDao.insertPreference(SettingsPreference(key, value))
    
    suspend fun getPreference(key: String): SettingsPreference? = 
        settingsDao.getPreference(key)
    
    suspend fun getPreferenceValue(key: String, defaultValue: String = ""): String = 
        settingsDao.getPreferenceValue(key) ?: defaultValue
    
    suspend fun deletePreference(key: String) = settingsDao.deletePreferenceByKey(key)
    
    suspend fun deleteAllPreferences() = settingsDao.deleteAllPreferences()
    
    // Convenience methods for common settings
    suspend fun setBooleanSetting(key: String, value: Boolean) = 
        settingsDao.setBooleanPreference(key, value)
    
    suspend fun getBooleanSetting(key: String, defaultValue: Boolean = false): Boolean = 
        settingsDao.getBooleanPreference(key, defaultValue)
    
    suspend fun setIntSetting(key: String, value: Int) = 
        settingsDao.setIntPreference(key, value)
    
    suspend fun getIntSetting(key: String, defaultValue: Int = 0): Int = 
        settingsDao.getIntPreference(key, defaultValue)
    
    suspend fun setFloatSetting(key: String, value: Float) = 
        settingsDao.setFloatPreference(key, value)
    
    suspend fun getFloatSetting(key: String, defaultValue: Float = 0f): Float = 
        settingsDao.getFloatPreference(key, defaultValue)
    
    suspend fun setStringSetting(key: String, value: String) = 
        settingsDao.setStringPreference(key, value)
    
    suspend fun getStringSetting(key: String, defaultValue: String = ""): String = 
        settingsDao.getStringPreference(key, defaultValue)
    
    // Common settings keys
    object SettingsKeys {
        const val VOICE_ENABLED = "voice_enabled"
        const val AUTO_PUNCTUATION = "auto_punctuation"
        const val LANGUAGE_DETECTION = "language_detection"
        const val VIBRATION_ENABLED = "vibration_enabled"
        const val SOUND_ENABLED = "sound_enabled"
        const val THEME_MODE = "theme_mode"
        const val RECORDING_QUALITY = "recording_quality"
        const val AUTO_SAVE_TRANSCRIPTIONS = "auto_save_transcriptions"
        const val PRIVACY_MODE = "privacy_mode"
        const val ANALYTICS_ENABLED = "analytics_enabled"
    }
}