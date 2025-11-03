package com.voxtype.keyboard.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.voxtype.keyboard.database.VoxTypeDatabase
import com.voxtype.keyboard.database.entities.DictionaryWord
import com.voxtype.keyboard.repository.DictionaryRepository
import kotlinx.coroutines.launch

class DictionaryViewModel(application: Application) : AndroidViewModel(application) {
    
    private val repository: DictionaryRepository
    
    val allWords: LiveData<List<DictionaryWord>>
    
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    
    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage
    
    private val _searchResults = MutableLiveData<List<DictionaryWord>>()
    val searchResults: LiveData<List<DictionaryWord>> = _searchResults
    
    init {
        val database = VoxTypeDatabase.getInstance(application)
        repository = DictionaryRepository(database)
        allWords = repository.getAllWords().asLiveData()
    }
    
    fun addWord(word: String, language: String = "en") {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                repository.addWord(word, language)
                _errorMessage.value = null
            } catch (e: Exception) {
                _errorMessage.value = "Failed to add word: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    fun updateWord(word: DictionaryWord) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                repository.updateWord(word)
                _errorMessage.value = null
            } catch (e: Exception) {
                _errorMessage.value = "Failed to update word: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    fun deleteWord(word: DictionaryWord) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                repository.deleteWord(word)
                _errorMessage.value = null
            } catch (e: Exception) {
                _errorMessage.value = "Failed to delete word: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    fun searchWords(query: String, limit: Int = 20) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val results = repository.searchWords(query, limit)
                _searchResults.value = results
                _errorMessage.value = null
            } catch (e: Exception) {
                _errorMessage.value = "Search failed: ${e.message}"
                _searchResults.value = emptyList()
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    fun incrementWordFrequency(word: String) {
        viewModelScope.launch {
            try {
                repository.incrementWordFrequency(word)
            } catch (e: Exception) {
                _errorMessage.value = "Failed to update word frequency: ${e.message}"
            }
        }
    }
    
    fun getWordsByLanguage(language: String): LiveData<List<DictionaryWord>> {
        return repository.getWordsByLanguage(language).asLiveData()
    }
    
    fun clearError() {
        _errorMessage.value = null
    }
    
    fun clearSearchResults() {
        _searchResults.value = emptyList()
    }
}