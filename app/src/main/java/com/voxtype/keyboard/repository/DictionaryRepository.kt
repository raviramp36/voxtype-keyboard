package com.voxtype.keyboard.repository

import com.voxtype.keyboard.database.VoxTypeDatabase
import com.voxtype.keyboard.database.entities.DictionaryWord
import kotlinx.coroutines.flow.Flow
import java.util.Date
class DictionaryRepository(
    private val database: VoxTypeDatabase
) {
    private val dictionaryDao = database.dictionaryWordDao()
    
    fun getAllWords(): Flow<List<DictionaryWord>> = dictionaryDao.getAllWords()
    
    fun getWordsByLanguage(language: String): Flow<List<DictionaryWord>> = 
        dictionaryDao.getWordsByLanguage(language)
    
    suspend fun addWord(word: String, language: String = "en"): Long {
        val dictionaryWord = DictionaryWord(
            word = word,
            language = language,
            frequency = 1,
            createdDate = Date(),
            lastUsed = Date()
        )
        return dictionaryDao.insertWord(dictionaryWord)
    }
    
    suspend fun updateWord(word: DictionaryWord) = dictionaryDao.updateWord(word)
    
    suspend fun deleteWord(word: DictionaryWord) = dictionaryDao.deleteWord(word)
    
    suspend fun searchWords(query: String, limit: Int = 20): List<DictionaryWord> = 
        dictionaryDao.searchWords(query, limit)
    
    suspend fun incrementWordFrequency(word: String) {
        dictionaryDao.incrementWordFrequency(word, Date())
    }
    
    suspend fun getMostFrequentWords(limit: Int = 100): List<DictionaryWord> = 
        dictionaryDao.getMostFrequentWords(limit)
    
    suspend fun getRecentlyUsedWords(limit: Int = 50): List<DictionaryWord> = 
        dictionaryDao.getRecentlyUsedWords(limit)
    
    suspend fun getWordCountByLanguage(language: String): Int = 
        dictionaryDao.getWordCountByLanguage(language)
    
    suspend fun getTotalWordCount(): Int = dictionaryDao.getTotalWordCount()
    
    suspend fun deleteWordsByLanguage(language: String) = 
        dictionaryDao.deleteWordsByLanguage(language)
    
    suspend fun deleteAllWords() = dictionaryDao.deleteAllWords()
}