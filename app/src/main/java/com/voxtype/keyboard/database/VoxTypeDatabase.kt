package com.voxtype.keyboard.database

import android.content.Context
import androidx.room.*
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.voxtype.keyboard.database.entities.*
import com.voxtype.keyboard.database.dao.*
import java.util.Date
import java.util.concurrent.TimeUnit

@Database(
    entities = [
        // Original entities
        TranscriptionEntry::class,
        CorrectionEntry::class,
        DailyStats::class,
        WordFrequency::class,
        UserDictionary::class,
        // New entities as per requirements
        DictionaryWord::class,
        Snippet::class,
        UserStatistics::class,
        SettingsPreference::class,
        UserDictionaryEntry::class
    ],
    version = 2,
    exportSchema = true
)
@TypeConverters(DateConverters::class)
abstract class VoxTypeDatabase : RoomDatabase() {
    
    // Original DAOs
    abstract fun transcriptionDao(): TranscriptionDao
    abstract fun correctionDao(): CorrectionDao
    abstract fun statsDao(): StatsDao
    abstract fun wordFrequencyDao(): WordFrequencyDao
    abstract fun userDictionaryDao(): UserDictionaryDao
    
    // New DAOs as per requirements
    abstract fun dictionaryWordDao(): DictionaryWordDao
    abstract fun snippetDao(): SnippetDao
    abstract fun userStatisticsDao(): UserStatisticsDao
    abstract fun settingsPreferenceDao(): SettingsPreferenceDao
    abstract fun userDictionaryEntryDao(): UserDictionaryEntryDao
    
    companion object {
        @Volatile
        private var INSTANCE: VoxTypeDatabase? = null
        
        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Create new tables for the required entities
                database.execSQL("""
                    CREATE TABLE IF NOT EXISTS dictionary_words (
                        id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                        word TEXT NOT NULL,
                        language TEXT NOT NULL DEFAULT 'en',
                        frequency INTEGER NOT NULL DEFAULT 1,
                        createdDate INTEGER NOT NULL,
                        lastUsed INTEGER NOT NULL
                    )
                """)
                
                database.execSQL("""
                    CREATE UNIQUE INDEX IF NOT EXISTS index_dictionary_words_word ON dictionary_words(word)
                """)
                
                database.execSQL("""
                    CREATE INDEX IF NOT EXISTS index_dictionary_words_language ON dictionary_words(language)
                """)
                
                database.execSQL("""
                    CREATE INDEX IF NOT EXISTS index_dictionary_words_frequency ON dictionary_words(frequency)
                """)
                
                database.execSQL("""
                    CREATE TABLE IF NOT EXISTS snippets (
                        id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                        trigger TEXT NOT NULL,
                        content TEXT NOT NULL,
                        category TEXT NOT NULL DEFAULT 'General',
                        usageCount INTEGER NOT NULL DEFAULT 0,
                        createdDate INTEGER NOT NULL
                    )
                """)
                
                database.execSQL("""
                    CREATE UNIQUE INDEX IF NOT EXISTS index_snippets_trigger ON snippets(trigger)
                """)
                
                database.execSQL("""
                    CREATE INDEX IF NOT EXISTS index_snippets_category ON snippets(category)
                """)
                
                database.execSQL("""
                    CREATE INDEX IF NOT EXISTS index_snippets_usageCount ON snippets(usageCount)
                """)
                
                database.execSQL("""
                    CREATE TABLE IF NOT EXISTS user_statistics (
                        id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                        date TEXT NOT NULL,
                        wordsTyped INTEGER NOT NULL DEFAULT 0,
                        wpm REAL NOT NULL DEFAULT 0.0,
                        appsUsed TEXT NOT NULL DEFAULT '',
                        languagesUsed TEXT NOT NULL DEFAULT ''
                    )
                """)
                
                database.execSQL("""
                    CREATE UNIQUE INDEX IF NOT EXISTS index_user_statistics_date ON user_statistics(date)
                """)
                
                database.execSQL("""
                    CREATE TABLE IF NOT EXISTS settings_preferences (
                        key TEXT PRIMARY KEY NOT NULL,
                        value TEXT NOT NULL
                    )
                """)
                
                database.execSQL("""
                    CREATE UNIQUE INDEX IF NOT EXISTS index_settings_preferences_key ON settings_preferences(key)
                """)
                
                database.execSQL("""
                    CREATE TABLE IF NOT EXISTS user_dictionary_entries (
                        id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                        word TEXT NOT NULL,
                        replacement TEXT NOT NULL,
                        createdDate INTEGER NOT NULL
                    )
                """)
                
                database.execSQL("""
                    CREATE UNIQUE INDEX IF NOT EXISTS index_user_dictionary_entries_word ON user_dictionary_entries(word)
                """)
            }
        }
        
        fun getInstance(context: Context): VoxTypeDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    VoxTypeDatabase::class.java,
                    "voxtype_database"
                )
                .addMigrations(MIGRATION_1_2)
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
}

// Entity Classes
@Entity(tableName = "transcriptions")
data class TranscriptionEntry(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val timestamp: Date = Date(),
    val originalAudio: String? = null, // Path to audio file if saved
    val rawTranscription: String,      // What Whisper returned
    val finalText: String,              // After AI enhancement
    val userEditedText: String? = null, // If user made corrections
    val wordCount: Int,
    val characterCount: Int,
    val duration: Float? = null,        // Recording duration in seconds
    val appPackage: String? = null,     // Which app was being used
    val textMode: String                // GENERAL, EMAIL, CHAT, FORMAL
)

@Entity(
    tableName = "corrections",
    indices = [Index(value = ["originalText"])]
)
data class CorrectionEntry(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val timestamp: Date = Date(),
    val originalText: String,           // What was transcribed
    val correctedText: String,          // What user changed it to
    val context: String? = null,        // Surrounding text for context
    val frequency: Int = 1              // How many times this correction was made
)

@Entity(
    tableName = "daily_stats",
    indices = [Index(value = ["date"], unique = true)]
)
data class DailyStats(
    @PrimaryKey
    val date: String,                   // Format: "2024-11-02"
    var totalWords: Int = 0,
    var totalCharacters: Int = 0,
    var totalTranscriptions: Int = 0,
    var totalDuration: Float = 0f,      // Total recording time in seconds
    var correctionsCount: Int = 0,
    var appsUsed: String = "",          // Comma-separated list of app packages
    var peakHour: Int? = null,          // Hour with most usage (0-23)
    var averageWordsPerTranscription: Float = 0f
)

@Entity(
    tableName = "word_frequency",
    indices = [Index(value = ["word"], unique = true)]
)
data class WordFrequency(
    @PrimaryKey
    val word: String,
    var frequency: Int = 1,
    val firstUsed: Date = Date(),
    var lastUsed: Date = Date(),
    var contexts: String = ""           // JSON array of contexts where used
)

@Entity(
    tableName = "user_dictionary",
    indices = [Index(value = ["word"], unique = true)]
)
data class UserDictionary(
    @PrimaryKey
    val word: String,                   // Custom word/phrase
    val replacement: String? = null,    // Optional replacement
    val isName: Boolean = false,        // Is it a proper name?
    val isPhraseShortcut: Boolean = false, // e.g., "brb" -> "be right back"
    val language: String = "en",
    val addedDate: Date = Date()
)

// Type Converters
class DateConverters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }
    
    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}

// DAO Interfaces
@Dao
interface TranscriptionDao {
    @Insert
    suspend fun insert(transcription: TranscriptionEntry): Long
    
    @Update
    suspend fun update(transcription: TranscriptionEntry)
    
    @Query("SELECT * FROM transcriptions ORDER BY timestamp DESC LIMIT :limit")
    suspend fun getRecent(limit: Int = 100): List<TranscriptionEntry>
    
    @Query("SELECT * FROM transcriptions WHERE date(timestamp/1000, 'unixepoch') = date('now') ORDER BY timestamp DESC")
    suspend fun getToday(): List<TranscriptionEntry>
    
    @Query("SELECT * FROM transcriptions WHERE date(timestamp/1000, 'unixepoch') >= date('now', '-7 days') ORDER BY timestamp DESC")
    suspend fun getLastWeek(): List<TranscriptionEntry>
    
    @Query("SELECT COUNT(*) FROM transcriptions WHERE date(timestamp/1000, 'unixepoch') = date('now')")
    suspend fun getTodayCount(): Int
    
    @Query("SELECT SUM(wordCount) FROM transcriptions WHERE date(timestamp/1000, 'unixepoch') = date('now')")
    suspend fun getTodayWordCount(): Int?
}

@Dao
interface CorrectionDao {
    @Insert
    suspend fun insert(correction: CorrectionEntry)
    
    @Query("SELECT * FROM corrections WHERE originalText = :text LIMIT 1")
    suspend fun findCorrection(text: String): CorrectionEntry?
    
    @Query("UPDATE corrections SET frequency = frequency + 1, timestamp = :timestamp WHERE originalText = :original")
    suspend fun incrementFrequency(original: String, timestamp: Date = Date())
    
    @Query("SELECT * FROM corrections ORDER BY frequency DESC LIMIT :limit")
    suspend fun getMostFrequent(limit: Int = 50): List<CorrectionEntry>
    
    @Query("SELECT * FROM corrections WHERE originalText LIKE '%' || :word || '%'")
    suspend fun findByWord(word: String): List<CorrectionEntry>
}

@Dao
interface StatsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(stats: DailyStats)
    
    @Query("SELECT * FROM daily_stats WHERE date = :date")
    suspend fun getStats(date: String): DailyStats?
    
    @Query("SELECT * FROM daily_stats WHERE date >= :startDate ORDER BY date DESC")
    suspend fun getStatsSince(startDate: String): List<DailyStats>
    
    @Query("SELECT SUM(totalWords) as total FROM daily_stats WHERE date >= :startDate")
    suspend fun getTotalWordsSince(startDate: String): Int?
    
    @Query("SELECT * FROM daily_stats ORDER BY date DESC LIMIT 7")
    suspend fun getLastWeekStats(): List<DailyStats>
    
    @Query("SELECT * FROM daily_stats ORDER BY date DESC LIMIT :days")
    suspend fun getRecentStats(days: Int): List<DailyStats>
    
    @Query("SELECT AVG(totalWords) FROM daily_stats WHERE date >= :startDate")
    suspend fun getAverageWordsSince(startDate: String): Float?
}

@Dao
interface WordFrequencyDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: WordFrequency)
    
    @Query("UPDATE word_frequency SET frequency = frequency + 1, lastUsed = :date WHERE word = :word")
    suspend fun incrementFrequency(word: String, date: Date = Date())
    
    @Query("SELECT * FROM word_frequency ORDER BY frequency DESC LIMIT :limit")
    suspend fun getMostUsedWords(limit: Int = 100): List<WordFrequency>
    
    @Query("SELECT * FROM word_frequency WHERE word LIKE :prefix || '%' ORDER BY frequency DESC LIMIT :limit")
    suspend fun getWordSuggestions(prefix: String, limit: Int = 10): List<WordFrequency>
    
    @Query("SELECT COUNT(DISTINCT word) FROM word_frequency")
    suspend fun getVocabularySize(): Int
}

@Dao
interface UserDictionaryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entry: UserDictionary)
    
    @Delete
    suspend fun delete(entry: UserDictionary)
    
    @Query("SELECT * FROM user_dictionary WHERE word = :word")
    suspend fun find(word: String): UserDictionary?
    
    @Query("SELECT * FROM user_dictionary WHERE isPhraseShortcut = 1")
    suspend fun getAllShortcuts(): List<UserDictionary>
    
    @Query("SELECT * FROM user_dictionary WHERE isName = 1 ORDER BY word")
    suspend fun getAllNames(): List<UserDictionary>
    
    @Query("SELECT * FROM user_dictionary ORDER BY word")
    suspend fun getAll(): List<UserDictionary>
}