package com.voxtype.keyboard

import android.content.Context
import com.voxtype.keyboard.database.*
import kotlinx.coroutines.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.Calendar

class AnalyticsManager(private val context: Context) {
    
    private val database = VoxTypeDatabase.getInstance(context)
    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    private val hourFormat = SimpleDateFormat("HH", Locale.getDefault())
    
    // Track a new transcription
    fun trackTranscription(
        rawText: String,
        finalText: String,
        duration: Float? = null,
        appPackage: String? = null,
        mode: GroqProcessor.TextMode = GroqProcessor.TextMode.GENERAL
    ) {
        scope.launch {
            try {
                // Count words and characters
                val wordCount = finalText.split("\\s+".toRegex()).filter { it.isNotEmpty() }.size
                val charCount = finalText.length
                
                // Save transcription entry
                val entry = TranscriptionEntry(
                    rawTranscription = rawText,
                    finalText = finalText,
                    wordCount = wordCount,
                    characterCount = charCount,
                    duration = duration,
                    appPackage = appPackage,
                    textMode = mode.name
                )
                database.transcriptionDao().insert(entry)
                
                // Update daily stats
                updateDailyStats(wordCount, charCount, duration, appPackage)
                
                // Track word frequency
                trackWordFrequency(finalText)
                
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
    
    // Track when user edits the transcribed text
    fun trackCorrection(original: String, corrected: String, context: String? = null) {
        if (original == corrected) return
        
        scope.launch {
            try {
                // Check if this correction exists
                val existing = database.correctionDao().findCorrection(original)
                
                if (existing != null) {
                    // Increment frequency
                    database.correctionDao().incrementFrequency(original)
                } else {
                    // Add new correction
                    val correction = CorrectionEntry(
                        originalText = original,
                        correctedText = corrected,
                        context = context
                    )
                    database.correctionDao().insert(correction)
                }
                
                // Learn from the correction for future
                learnFromCorrection(original, corrected)
                
                // Update daily correction count
                val today = dateFormat.format(Date())
                val stats = database.statsDao().getStats(today) ?: DailyStats(date = today)
                stats.correctionsCount++
                database.statsDao().insertOrUpdate(stats)
                
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
    
    // Update daily statistics
    private suspend fun updateDailyStats(
        words: Int,
        characters: Int,
        duration: Float?,
        appPackage: String?
    ) {
        val today = dateFormat.format(Date())
        val currentHour = hourFormat.format(Date()).toInt()
        
        val stats = database.statsDao().getStats(today) ?: DailyStats(date = today)
        
        stats.totalWords += words
        stats.totalCharacters += characters
        stats.totalTranscriptions++
        duration?.let { stats.totalDuration += it }
        
        // Track apps used
        appPackage?.let {
            if (!stats.appsUsed.contains(it)) {
                stats.appsUsed = if (stats.appsUsed.isEmpty()) it 
                               else "${stats.appsUsed},$it"
            }
        }
        
        // Update peak hour
        stats.peakHour = currentHour
        
        // Calculate average
        stats.averageWordsPerTranscription = 
            stats.totalWords.toFloat() / stats.totalTranscriptions.toFloat()
        
        database.statsDao().insertOrUpdate(stats)
    }
    
    // Track word frequency for better predictions
    private suspend fun trackWordFrequency(text: String) {
        val words = text.toLowerCase(Locale.getDefault())
            .split("\\s+".toRegex())
            .filter { it.length > 2 } // Skip very short words
            .map { it.replace(Regex("[^a-z0-9]"), "") } // Remove punctuation
            .filter { it.isNotEmpty() }
        
        for (word in words) {
            val existing = database.wordFrequencyDao().getMostUsedWords(Int.MAX_VALUE)
                .find { it.word == word }
            
            if (existing != null) {
                database.wordFrequencyDao().incrementFrequency(word)
            } else {
                database.wordFrequencyDao().insert(
                    WordFrequency(word = word, frequency = 1)
                )
            }
        }
    }
    
    // Learn from user corrections
    private suspend fun learnFromCorrection(original: String, corrected: String) {
        // Find words that were changed
        val originalWords = original.split(" ")
        val correctedWords = corrected.split(" ")
        
        // Simple word-by-word comparison (can be improved with diff algorithm)
        originalWords.zip(correctedWords).forEach { (orig, corr) ->
            if (orig != corr && orig.isNotEmpty() && corr.isNotEmpty()) {
                // Add to user dictionary as a correction pattern
                database.userDictionaryDao().insert(
                    UserDictionary(
                        word = orig.toLowerCase(),
                        replacement = corr,
                        isPhraseShortcut = false,
                        language = "en"
                    )
                )
            }
        }
    }
    
    // Get today's statistics
    suspend fun getTodayStats(): TodayStatsModel {
        val transcriptions = database.transcriptionDao().getToday()
        val wordCount = transcriptions.sumOf { it.wordCount }
        val charCount = transcriptions.sumOf { it.characterCount }
        val duration = transcriptions.mapNotNull { it.duration }.sum()
        
        return TodayStatsModel(
            totalWords = wordCount,
            totalCharacters = charCount,
            totalTranscriptions = transcriptions.size,
            totalDuration = duration,
            averageWordsPerTranscription = if (transcriptions.isNotEmpty()) 
                wordCount.toFloat() / transcriptions.size else 0f,
            transcriptions = transcriptions
        )
    }
    
    // Get weekly statistics
    suspend fun getWeeklyStats(): WeeklyStatsModel {
        val stats = database.statsDao().getLastWeekStats()
        val totalWords = stats.sumOf { it.totalWords }
        val totalTranscriptions = stats.sumOf { it.totalTranscriptions }
        val totalDuration = stats.sumOf { it.totalDuration.toDouble() }.toFloat()
        
        return WeeklyStatsModel(
            dailyStats = stats,
            totalWords = totalWords,
            totalTranscriptions = totalTranscriptions,
            totalDuration = totalDuration,
            averageWordsPerDay = totalWords / 7f,
            mostProductiveDay = stats.maxByOrNull { it.totalWords },
            vocabularySize = database.wordFrequencyDao().getVocabularySize()
        )
    }
    
    // Get most used words
    suspend fun getMostUsedWords(limit: Int = 50): List<WordFrequency> {
        return database.wordFrequencyDao().getMostUsedWords(limit)
    }
    
    // Get correction suggestions
    suspend fun getCorrectionSuggestion(text: String): String? {
        val correction = database.correctionDao().findCorrection(text)
        return correction?.correctedText
    }
    
    // Get word suggestions for autocomplete
    suspend fun getWordSuggestions(prefix: String, limit: Int = 5): List<String> {
        return database.wordFrequencyDao()
            .getWordSuggestions(prefix, limit)
            .map { it.word }
    }
    
    // Check user dictionary for replacements
    suspend fun checkUserDictionary(word: String): String? {
        val entry = database.userDictionaryDao().find(word.toLowerCase())
        return entry?.replacement
    }
    
    // Add custom word to dictionary
    fun addToUserDictionary(word: String, replacement: String? = null, isName: Boolean = false) {
        scope.launch {
            database.userDictionaryDao().insert(
                UserDictionary(
                    word = word.toLowerCase(),
                    replacement = replacement,
                    isName = isName
                )
            )
        }
    }
    
    // Get streak days
    suspend fun getStreakDays(): Int {
        // Calculate consecutive days with activity
        val recentStats = database.statsDao().getRecentStats(30)
        if (recentStats.isEmpty()) return 0
        
        val today = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        var streak = 0
        val calendar = Calendar.getInstance()
        
        // Start from today and go backwards
        for (i in 0..29) {
            calendar.time = Date()
            calendar.add(Calendar.DAY_OF_YEAR, -i)
            val dateStr = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(calendar.time)
            
            val hasActivity = recentStats.any { it.date == dateStr && it.totalWords > 0 }
            
            if (hasActivity) {
                if (i == streak) {
                    streak++
                } else {
                    break
                }
            } else if (i == 0) {
                // No activity today yet, check from yesterday
                continue
            } else if (streak > 0) {
                // Gap found, stop counting
                break
            }
        }
        
        return streak
    }
    
    // Clean up old data (older than 30 days)
    fun cleanupOldData() {
        scope.launch {
            // Keep last 30 days of detailed transcriptions
            // This would need a custom query implementation
        }
    }
    
    fun destroy() {
        scope.cancel()
    }
}

// Data Models for UI
data class TodayStatsModel(
    val totalWords: Int,
    val totalCharacters: Int,
    val totalTranscriptions: Int,
    val totalDuration: Float,
    val averageWordsPerTranscription: Float,
    val transcriptions: List<TranscriptionEntry>
)

data class WeeklyStatsModel(
    val dailyStats: List<DailyStats>,
    val totalWords: Int,
    val totalTranscriptions: Int,
    val totalDuration: Float,
    val averageWordsPerDay: Float,
    val mostProductiveDay: DailyStats?,
    val vocabularySize: Int
)

// Extension functions for formatting
fun Float.formatDuration(): String {
    val hours = (this / 3600).toInt()
    val minutes = ((this % 3600) / 60).toInt()
    val seconds = (this % 60).toInt()
    
    return when {
        hours > 0 -> String.format("%d:%02d:%02d", hours, minutes, seconds)
        minutes > 0 -> String.format("%d:%02d", minutes, seconds)
        else -> String.format("%ds", seconds)
    }
}