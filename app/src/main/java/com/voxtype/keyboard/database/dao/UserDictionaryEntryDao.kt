package com.voxtype.keyboard.database.dao

import androidx.room.*
import com.voxtype.keyboard.database.entities.UserDictionaryEntry
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDictionaryEntryDao {
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWord(word: UserDictionaryEntry): Long
    
    @Update
    suspend fun updateWord(word: UserDictionaryEntry)
    
    @Delete
    suspend fun deleteWord(word: UserDictionaryEntry)
    
    @Query("SELECT * FROM user_dictionary_entries WHERE id = :id")
    suspend fun getWordById(id: Long): UserDictionaryEntry?
    
    @Query("SELECT * FROM user_dictionary_entries WHERE word = :word LIMIT 1")
    suspend fun getWordByText(word: String): UserDictionaryEntry?
    
    @Query("SELECT * FROM user_dictionary_entries ORDER BY word")
    fun getAllWords(): Flow<List<UserDictionaryEntry>>
    
    @Query("SELECT * FROM user_dictionary_entries WHERE word LIKE '%' || :query || '%' OR replacement LIKE '%' || :query || '%' ORDER BY word")
    suspend fun searchWords(query: String): List<UserDictionaryEntry>
    
    @Query("SELECT * FROM user_dictionary_entries ORDER BY createdDate DESC LIMIT :limit")
    suspend fun getRecentlyAddedWords(limit: Int = 20): List<UserDictionaryEntry>
    
    @Query("SELECT COUNT(*) FROM user_dictionary_entries")
    suspend fun getTotalWordCount(): Int
    
    @Query("DELETE FROM user_dictionary_entries WHERE word = :word")
    suspend fun deleteWordByText(word: String)
    
    @Query("DELETE FROM user_dictionary_entries")
    suspend fun deleteAllWords()
}