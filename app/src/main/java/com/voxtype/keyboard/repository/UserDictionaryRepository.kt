package com.voxtype.keyboard.repository

import com.voxtype.keyboard.database.VoxTypeDatabase
import com.voxtype.keyboard.database.entities.UserDictionaryEntry
import kotlinx.coroutines.flow.Flow
import java.util.Date
class UserDictionaryRepository(
    private val database: VoxTypeDatabase
) {
    private val userDictionaryDao = database.userDictionaryEntryDao()
    
    fun getAllWords(): Flow<List<UserDictionaryEntry>> = userDictionaryDao.getAllWords()
    
    suspend fun addWord(word: String, replacement: String): Long {
        val entry = UserDictionaryEntry(
            word = word,
            replacement = replacement,
            createdDate = Date()
        )
        return userDictionaryDao.insertWord(entry)
    }
    
    suspend fun updateWord(word: UserDictionaryEntry) = userDictionaryDao.updateWord(word)
    
    suspend fun deleteWord(word: UserDictionaryEntry) = userDictionaryDao.deleteWord(word)
    
    suspend fun getWordById(id: Long): UserDictionaryEntry? = userDictionaryDao.getWordById(id)
    
    suspend fun getWordByText(word: String): UserDictionaryEntry? = 
        userDictionaryDao.getWordByText(word)
    
    suspend fun searchWords(query: String): List<UserDictionaryEntry> = 
        userDictionaryDao.searchWords(query)
    
    suspend fun getRecentlyAddedWords(limit: Int = 20): List<UserDictionaryEntry> = 
        userDictionaryDao.getRecentlyAddedWords(limit)
    
    suspend fun getTotalWordCount(): Int = userDictionaryDao.getTotalWordCount()
    
    suspend fun deleteWordByText(word: String) = userDictionaryDao.deleteWordByText(word)
    
    suspend fun deleteAllWords() = userDictionaryDao.deleteAllWords()
}