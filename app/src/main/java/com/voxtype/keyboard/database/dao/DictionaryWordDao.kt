package com.voxtype.keyboard.database.dao

import androidx.room.*
import com.voxtype.keyboard.database.entities.DictionaryWord
import kotlinx.coroutines.flow.Flow
import java.util.Date

@Dao
interface DictionaryWordDao {
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWord(word: DictionaryWord): Long
    
    @Update
    suspend fun updateWord(word: DictionaryWord)
    
    @Delete
    suspend fun deleteWord(word: DictionaryWord)
    
    @Query("SELECT * FROM dictionary_words WHERE id = :id")
    suspend fun getWordById(id: Long): DictionaryWord?
    
    @Query("SELECT * FROM dictionary_words WHERE word = :word LIMIT 1")
    suspend fun getWordByText(word: String): DictionaryWord?
    
    @Query("SELECT * FROM dictionary_words WHERE language = :language ORDER BY frequency DESC")
    fun getWordsByLanguage(language: String): Flow<List<DictionaryWord>>
    
    @Query("SELECT * FROM dictionary_words ORDER BY frequency DESC")
    fun getAllWords(): Flow<List<DictionaryWord>>
    
    @Query("SELECT * FROM dictionary_words WHERE word LIKE '%' || :query || '%' ORDER BY frequency DESC LIMIT :limit")
    suspend fun searchWords(query: String, limit: Int = 20): List<DictionaryWord>
    
    @Query("UPDATE dictionary_words SET frequency = frequency + 1, lastUsed = :date WHERE word = :word")
    suspend fun incrementWordFrequency(word: String, date: Date = Date())
    
    @Query("SELECT * FROM dictionary_words ORDER BY frequency DESC LIMIT :limit")
    suspend fun getMostFrequentWords(limit: Int = 100): List<DictionaryWord>
    
    @Query("SELECT * FROM dictionary_words ORDER BY lastUsed DESC LIMIT :limit")
    suspend fun getRecentlyUsedWords(limit: Int = 50): List<DictionaryWord>
    
    @Query("SELECT COUNT(*) FROM dictionary_words WHERE language = :language")
    suspend fun getWordCountByLanguage(language: String): Int
    
    @Query("SELECT COUNT(*) FROM dictionary_words")
    suspend fun getTotalWordCount(): Int
    
    @Query("DELETE FROM dictionary_words WHERE language = :language")
    suspend fun deleteWordsByLanguage(language: String)
    
    @Query("DELETE FROM dictionary_words")
    suspend fun deleteAllWords()
}