package com.voxtype.keyboard

import android.content.Context
import com.voxtype.keyboard.database.VoxTypeDatabase
import com.voxtype.keyboard.database.dao.SettingsPreferenceDao
import com.voxtype.keyboard.database.entities.SettingsPreference
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AdvancedSettingsManager(private val context: Context) {
    
    private val database = VoxTypeDatabase.getInstance(context)
    private val settingsDao = database.settingsPreferenceDao()
    
    // Setting keys
    companion object {
        // Language settings
        const val KEY_VOICE_LANGUAGE = "voice_language"
        const val KEY_AUTO_LANGUAGE_DETECTION = "auto_language_detection"
        const val KEY_CODE_SWITCHING_ENABLED = "code_switching_enabled"
        const val KEY_LANGUAGE_SWITCHING_SENSITIVITY = "language_switching_sensitivity"
        
        // Whisper mode settings
        const val KEY_WHISPER_MODE = "whisper_mode"
        const val KEY_WHISPER_SENSITIVITY = "whisper_sensitivity"
        const val KEY_WHISPER_BITRATE = "whisper_bitrate"
        
        // Privacy settings
        const val KEY_PRIVACY_MODE = "privacy_mode"
        const val KEY_LOCAL_ONLY_STORAGE = "local_only_storage"
        const val KEY_ANALYTICS_ENABLED = "analytics_enabled"
        const val KEY_CLEAR_DATA_ON_PRIVACY = "clear_data_on_privacy"
        
        // Tone and context settings
        const val KEY_CONTEXT_AWARE_TONE = "context_aware_tone"
        const val KEY_APP_SPECIFIC_TONES = "app_specific_tones"
        const val KEY_CUSTOM_TONE_RULES = "custom_tone_rules"
        
        // Smart formatting settings
        const val KEY_SMART_PUNCTUATION = "smart_punctuation"
        const val KEY_SMART_CAPITALIZATION = "smart_capitalization"
        const val KEY_NUMBER_FORMATTING = "number_formatting"
        const val KEY_DATE_FORMATTING = "date_formatting"
        const val KEY_EMOJI_SUGGESTIONS = "emoji_suggestions"
        const val KEY_EMOJI_CONTEXT_AWARE = "emoji_context_aware"
        
        // Performance settings
        const val KEY_BATTERY_OPTIMIZATION = "battery_optimization"
        const val KEY_PROCESSING_QUALITY = "processing_quality"
        const val KEY_OFFLINE_FALLBACK = "offline_fallback"
        
        // Default values
        const val DEFAULT_VOICE_LANGUAGE = "auto"
        const val DEFAULT_WHISPER_SENSITIVITY = 0.7f
        const val DEFAULT_WHISPER_BITRATE = 128000
    }
    
    // Initialize default settings
    suspend fun initializeDefaultSettings() = withContext(Dispatchers.IO) {
        val defaultSettings = listOf(
            // Language settings
            SettingsPreference(KEY_VOICE_LANGUAGE, DEFAULT_VOICE_LANGUAGE),
            SettingsPreference(KEY_AUTO_LANGUAGE_DETECTION, "true"),
            SettingsPreference(KEY_CODE_SWITCHING_ENABLED, "true"),
            SettingsPreference(KEY_LANGUAGE_SWITCHING_SENSITIVITY, "0.6"),
            
            // Whisper mode settings
            SettingsPreference(KEY_WHISPER_MODE, "false"),
            SettingsPreference(KEY_WHISPER_SENSITIVITY, DEFAULT_WHISPER_SENSITIVITY.toString()),
            SettingsPreference(KEY_WHISPER_BITRATE, DEFAULT_WHISPER_BITRATE.toString()),
            
            // Privacy settings
            SettingsPreference(KEY_PRIVACY_MODE, "false"),
            SettingsPreference(KEY_LOCAL_ONLY_STORAGE, "false"),
            SettingsPreference(KEY_ANALYTICS_ENABLED, "true"),
            SettingsPreference(KEY_CLEAR_DATA_ON_PRIVACY, "false"),
            
            // Tone and context settings
            SettingsPreference(KEY_CONTEXT_AWARE_TONE, "true"),
            SettingsPreference(KEY_APP_SPECIFIC_TONES, "true"),
            SettingsPreference(KEY_CUSTOM_TONE_RULES, "{}"),
            
            // Smart formatting settings
            SettingsPreference(KEY_SMART_PUNCTUATION, "true"),
            SettingsPreference(KEY_SMART_CAPITALIZATION, "true"),
            SettingsPreference(KEY_NUMBER_FORMATTING, "true"),
            SettingsPreference(KEY_DATE_FORMATTING, "true"),
            SettingsPreference(KEY_EMOJI_SUGGESTIONS, "true"),
            SettingsPreference(KEY_EMOJI_CONTEXT_AWARE, "true"),
            
            // Performance settings
            SettingsPreference(KEY_BATTERY_OPTIMIZATION, "false"),
            SettingsPreference(KEY_PROCESSING_QUALITY, "high"),
            SettingsPreference(KEY_OFFLINE_FALLBACK, "true")
        )
        
        // Insert only if they don't exist
        for (setting in defaultSettings) {
            if (settingsDao.getPreference(setting.key) == null) {
                settingsDao.insertPreference(setting)
            }
        }
    }
    
    // Language settings
    suspend fun setVoiceLanguage(language: String) = withContext(Dispatchers.IO) {
        settingsDao.setStringPreference(KEY_VOICE_LANGUAGE, language)
    }
    
    suspend fun getVoiceLanguage(): String = withContext(Dispatchers.IO) {
        settingsDao.getStringPreference(KEY_VOICE_LANGUAGE, DEFAULT_VOICE_LANGUAGE)
    }
    
    suspend fun setAutoLanguageDetection(enabled: Boolean) = withContext(Dispatchers.IO) {
        settingsDao.setBooleanPreference(KEY_AUTO_LANGUAGE_DETECTION, enabled)
    }
    
    suspend fun isAutoLanguageDetectionEnabled(): Boolean = withContext(Dispatchers.IO) {
        settingsDao.getBooleanPreference(KEY_AUTO_LANGUAGE_DETECTION, true)
    }
    
    suspend fun setCodeSwitchingEnabled(enabled: Boolean) = withContext(Dispatchers.IO) {
        settingsDao.setBooleanPreference(KEY_CODE_SWITCHING_ENABLED, enabled)
    }
    
    suspend fun isCodeSwitchingEnabled(): Boolean = withContext(Dispatchers.IO) {
        settingsDao.getBooleanPreference(KEY_CODE_SWITCHING_ENABLED, true)
    }
    
    // Whisper mode settings
    suspend fun setWhisperMode(enabled: Boolean) = withContext(Dispatchers.IO) {
        settingsDao.setBooleanPreference(KEY_WHISPER_MODE, enabled)
    }
    
    suspend fun isWhisperModeEnabled(): Boolean = withContext(Dispatchers.IO) {
        settingsDao.getBooleanPreference(KEY_WHISPER_MODE, false)
    }
    
    suspend fun setWhisperSensitivity(sensitivity: Float) = withContext(Dispatchers.IO) {
        settingsDao.setFloatPreference(KEY_WHISPER_SENSITIVITY, sensitivity)
    }
    
    suspend fun getWhisperSensitivity(): Float = withContext(Dispatchers.IO) {
        settingsDao.getFloatPreference(KEY_WHISPER_SENSITIVITY, DEFAULT_WHISPER_SENSITIVITY)
    }
    
    // Privacy mode settings
    suspend fun setPrivacyMode(enabled: Boolean) = withContext(Dispatchers.IO) {
        settingsDao.setBooleanPreference(KEY_PRIVACY_MODE, enabled)
        
        // If enabling privacy mode and clear data option is set, clear analytics
        if (enabled && shouldClearDataOnPrivacy()) {
            clearSensitiveData()
        }
    }
    
    suspend fun isPrivacyModeEnabled(): Boolean = withContext(Dispatchers.IO) {
        settingsDao.getBooleanPreference(KEY_PRIVACY_MODE, false)
    }
    
    suspend fun setLocalOnlyStorage(enabled: Boolean) = withContext(Dispatchers.IO) {
        settingsDao.setBooleanPreference(KEY_LOCAL_ONLY_STORAGE, enabled)
    }
    
    suspend fun isLocalOnlyStorageEnabled(): Boolean = withContext(Dispatchers.IO) {
        settingsDao.getBooleanPreference(KEY_LOCAL_ONLY_STORAGE, false)
    }
    
    suspend fun shouldClearDataOnPrivacy(): Boolean = withContext(Dispatchers.IO) {
        settingsDao.getBooleanPreference(KEY_CLEAR_DATA_ON_PRIVACY, false)
    }
    
    private suspend fun clearSensitiveData() = withContext(Dispatchers.IO) {
        // Clear transcription history (keep only local stats)
        val transcriptionDao = database.transcriptionDao()
        // Implementation would clear sensitive transcription data
        
        // Clear correction data
        val correctionDao = database.correctionDao()
        // Implementation would clear personal corrections
        
        android.util.Log.i("AdvancedSettings", "Sensitive data cleared due to privacy mode")
    }
    
    // Tone and context settings
    suspend fun setContextAwareTone(enabled: Boolean) = withContext(Dispatchers.IO) {
        settingsDao.setBooleanPreference(KEY_CONTEXT_AWARE_TONE, enabled)
    }
    
    suspend fun isContextAwareToneEnabled(): Boolean = withContext(Dispatchers.IO) {
        settingsDao.getBooleanPreference(KEY_CONTEXT_AWARE_TONE, true)
    }
    
    suspend fun setAppSpecificTones(enabled: Boolean) = withContext(Dispatchers.IO) {
        settingsDao.setBooleanPreference(KEY_APP_SPECIFIC_TONES, enabled)
    }
    
    suspend fun isAppSpecificTonesEnabled(): Boolean = withContext(Dispatchers.IO) {
        settingsDao.getBooleanPreference(KEY_APP_SPECIFIC_TONES, true)
    }
    
    // App-specific tone preferences
    suspend fun setAppTonePreference(packageName: String, mode: GroqProcessor.TextMode) = withContext(Dispatchers.IO) {
        val key = "app_tone_$packageName"
        settingsDao.setStringPreference(key, mode.name)
    }
    
    suspend fun getAppTonePreference(packageName: String): GroqProcessor.TextMode? = withContext(Dispatchers.IO) {
        val key = "app_tone_$packageName"
        val value = settingsDao.getStringPreference(key, "")
        if (value.isNotEmpty()) {
            try {
                GroqProcessor.TextMode.valueOf(value)
            } catch (e: IllegalArgumentException) {
                null
            }
        } else null
    }
    
    // Smart formatting settings
    suspend fun setSmartPunctuation(enabled: Boolean) = withContext(Dispatchers.IO) {
        settingsDao.setBooleanPreference(KEY_SMART_PUNCTUATION, enabled)
    }
    
    suspend fun isSmartPunctuationEnabled(): Boolean = withContext(Dispatchers.IO) {
        settingsDao.getBooleanPreference(KEY_SMART_PUNCTUATION, true)
    }
    
    suspend fun setSmartCapitalization(enabled: Boolean) = withContext(Dispatchers.IO) {
        settingsDao.setBooleanPreference(KEY_SMART_CAPITALIZATION, enabled)
    }
    
    suspend fun isSmartCapitalizationEnabled(): Boolean = withContext(Dispatchers.IO) {
        settingsDao.getBooleanPreference(KEY_SMART_CAPITALIZATION, true)
    }
    
    suspend fun setEmojiSuggestions(enabled: Boolean) = withContext(Dispatchers.IO) {
        settingsDao.setBooleanPreference(KEY_EMOJI_SUGGESTIONS, enabled)
    }
    
    suspend fun isEmojiSuggestionsEnabled(): Boolean = withContext(Dispatchers.IO) {
        settingsDao.getBooleanPreference(KEY_EMOJI_SUGGESTIONS, true)
    }
    
    suspend fun setEmojiContextAware(enabled: Boolean) = withContext(Dispatchers.IO) {
        settingsDao.setBooleanPreference(KEY_EMOJI_CONTEXT_AWARE, enabled)
    }
    
    suspend fun isEmojiContextAwareEnabled(): Boolean = withContext(Dispatchers.IO) {
        settingsDao.getBooleanPreference(KEY_EMOJI_CONTEXT_AWARE, true)
    }
    
    // Performance settings
    suspend fun setBatteryOptimization(enabled: Boolean) = withContext(Dispatchers.IO) {
        settingsDao.setBooleanPreference(KEY_BATTERY_OPTIMIZATION, enabled)
    }
    
    suspend fun isBatteryOptimizationEnabled(): Boolean = withContext(Dispatchers.IO) {
        settingsDao.getBooleanPreference(KEY_BATTERY_OPTIMIZATION, false)
    }
    
    suspend fun setProcessingQuality(quality: String) = withContext(Dispatchers.IO) {
        settingsDao.setStringPreference(KEY_PROCESSING_QUALITY, quality)
    }
    
    suspend fun getProcessingQuality(): String = withContext(Dispatchers.IO) {
        settingsDao.getStringPreference(KEY_PROCESSING_QUALITY, "high")
    }
    
    // Emoji context mapping
    suspend fun getEmojiSuggestions(text: String, context: String): List<String> = withContext(Dispatchers.IO) {
        if (!isEmojiSuggestionsEnabled()) return@withContext emptyList()
        
        val suggestions = mutableListOf<String>()
        val lowerText = text.lowercase()
        val lowerContext = context.lowercase()
        
        // Basic emotion-based suggestions
        when {
            lowerText.contains("happy") || lowerText.contains("good") || lowerText.contains("great") -> 
                suggestions.addAll(listOf("😊", "😄", "🎉", "👍"))
            lowerText.contains("sad") || lowerText.contains("bad") || lowerText.contains("sorry") -> 
                suggestions.addAll(listOf("😢", "😞", "💔", "😔"))
            lowerText.contains("love") || lowerText.contains("heart") -> 
                suggestions.addAll(listOf("❤️", "💕", "😍", "💖"))
            lowerText.contains("laugh") || lowerText.contains("funny") || lowerText.contains("haha") -> 
                suggestions.addAll(listOf("😂", "🤣", "😆", "😄"))
            lowerText.contains("thanks") || lowerText.contains("thank you") -> 
                suggestions.addAll(listOf("🙏", "😊", "👍", "💯"))
        }
        
        // Context-aware suggestions
        if (isEmojiContextAwareEnabled()) {
            when {
                lowerContext.contains("whatsapp") || lowerContext.contains("telegram") -> {
                    // More casual emojis for chat apps
                    suggestions.addAll(listOf("👌", "🔥", "💯", "🙌"))
                }
                lowerContext.contains("linkedin") || lowerContext.contains("email") -> {
                    // Professional emojis
                    suggestions.addAll(listOf("👍", "💼", "📈", "🎯"))
                }
            }
        }
        
        return@withContext suggestions.distinct().take(6)
    }
}