package com.voxtype.keyboard.database.dao

import androidx.room.*
import com.voxtype.keyboard.database.entities.SettingsPreference
import kotlinx.coroutines.flow.Flow

@Dao
interface SettingsPreferenceDao {
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPreference(preference: SettingsPreference)
    
    @Update
    suspend fun updatePreference(preference: SettingsPreference)
    
    @Delete
    suspend fun deletePreference(preference: SettingsPreference)
    
    @Query("SELECT * FROM settings_preferences WHERE key = :key LIMIT 1")
    suspend fun getPreference(key: String): SettingsPreference?
    
    @Query("SELECT value FROM settings_preferences WHERE key = :key LIMIT 1")
    suspend fun getPreferenceValue(key: String): String?
    
    @Query("SELECT * FROM settings_preferences ORDER BY key")
    fun getAllPreferences(): Flow<List<SettingsPreference>>
    
    @Query("SELECT * FROM settings_preferences WHERE key LIKE :keyPattern ORDER BY key")
    suspend fun getPreferencesByPattern(keyPattern: String): List<SettingsPreference>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPreferences(preferences: List<SettingsPreference>)
    
    @Query("DELETE FROM settings_preferences WHERE key = :key")
    suspend fun deletePreferenceByKey(key: String)
    
    @Query("DELETE FROM settings_preferences WHERE key LIKE :keyPattern")
    suspend fun deletePreferencesByPattern(keyPattern: String)
    
    @Query("DELETE FROM settings_preferences")
    suspend fun deleteAllPreferences()
    
    // Convenience methods for common data types
    suspend fun setBooleanPreference(key: String, value: Boolean) {
        insertPreference(SettingsPreference(key, value.toString()))
    }
    
    suspend fun getBooleanPreference(key: String, defaultValue: Boolean = false): Boolean {
        return getPreferenceValue(key)?.toBooleanStrictOrNull() ?: defaultValue
    }
    
    suspend fun setIntPreference(key: String, value: Int) {
        insertPreference(SettingsPreference(key, value.toString()))
    }
    
    suspend fun getIntPreference(key: String, defaultValue: Int = 0): Int {
        return getPreferenceValue(key)?.toIntOrNull() ?: defaultValue
    }
    
    suspend fun setFloatPreference(key: String, value: Float) {
        insertPreference(SettingsPreference(key, value.toString()))
    }
    
    suspend fun getFloatPreference(key: String, defaultValue: Float = 0f): Float {
        return getPreferenceValue(key)?.toFloatOrNull() ?: defaultValue
    }
    
    suspend fun setStringPreference(key: String, value: String) {
        insertPreference(SettingsPreference(key, value))
    }
    
    suspend fun getStringPreference(key: String, defaultValue: String = ""): String {
        return getPreferenceValue(key) ?: defaultValue
    }
}