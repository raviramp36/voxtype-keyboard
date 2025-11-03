package com.voxtype.keyboard.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.voxtype.keyboard.database.VoxTypeDatabase
import com.voxtype.keyboard.database.entities.Snippet
import com.voxtype.keyboard.repository.SnippetRepository
import kotlinx.coroutines.launch

class SnippetViewModel(application: Application) : AndroidViewModel(application) {
    
    private val repository: SnippetRepository
    
    val allSnippets: LiveData<List<Snippet>>
    
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    
    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage
    
    private val _searchResults = MutableLiveData<List<Snippet>>()
    val searchResults: LiveData<List<Snippet>> = _searchResults
    
    private val _categories = MutableLiveData<List<String>>()
    val categories: LiveData<List<String>> = _categories
    
    init {
        val database = VoxTypeDatabase.getInstance(application)
        repository = SnippetRepository(database)
        allSnippets = repository.getAllSnippets().asLiveData()
        loadCategories()
    }
    
    fun addSnippet(trigger: String, content: String, category: String = "General") {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                repository.addSnippet(trigger, content, category)
                loadCategories() // Refresh categories
                _errorMessage.value = null
            } catch (e: Exception) {
                _errorMessage.value = "Failed to add snippet: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    fun updateSnippet(snippet: Snippet) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                repository.updateSnippet(snippet)
                loadCategories() // Refresh categories in case category changed
                _errorMessage.value = null
            } catch (e: Exception) {
                _errorMessage.value = "Failed to update snippet: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    fun deleteSnippet(snippet: Snippet) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                repository.deleteSnippet(snippet)
                loadCategories() // Refresh categories
                _errorMessage.value = null
            } catch (e: Exception) {
                _errorMessage.value = "Failed to delete snippet: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    fun searchSnippets(query: String) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val results = repository.searchSnippets(query)
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
    
    fun useSnippet(snippet: Snippet) {
        viewModelScope.launch {
            try {
                repository.incrementUsageCount(snippet.id)
            } catch (e: Exception) {
                _errorMessage.value = "Failed to update usage count: ${e.message}"
            }
        }
    }
    
    fun getSnippetsByCategory(category: String): LiveData<List<Snippet>> {
        return repository.getSnippetsByCategory(category).asLiveData()
    }
    
    private fun loadCategories() {
        viewModelScope.launch {
            try {
                val categoryList = repository.getAllCategories()
                _categories.value = categoryList
            } catch (e: Exception) {
                _errorMessage.value = "Failed to load categories: ${e.message}"
            }
        }
    }
    
    fun clearError() {
        _errorMessage.value = null
    }
    
    fun clearSearchResults() {
        _searchResults.value = emptyList()
    }
}