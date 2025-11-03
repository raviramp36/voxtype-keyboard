package com.voxtype.keyboard.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.voxtype.keyboard.database.VoxTypeDatabase
import com.voxtype.keyboard.database.entities.UserStatistics
import com.voxtype.keyboard.repository.UserStatisticsRepository
import kotlinx.coroutines.launch

class UserStatisticsViewModel(application: Application) : AndroidViewModel(application) {
    
    private val repository: UserStatisticsRepository
    
    val allStatistics: LiveData<List<UserStatistics>>
    
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    
    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage
    
    private val _weeklyStats = MutableLiveData<List<UserStatistics>>()
    val weeklyStats: LiveData<List<UserStatistics>> = _weeklyStats
    
    private val _monthlyStats = MutableLiveData<List<UserStatistics>>()
    val monthlyStats: LiveData<List<UserStatistics>> = _monthlyStats
    
    private val _totalWordsThisWeek = MutableLiveData<Int>()
    val totalWordsThisWeek: LiveData<Int> = _totalWordsThisWeek
    
    private val _averageWpmThisWeek = MutableLiveData<Float>()
    val averageWpmThisWeek: LiveData<Float> = _averageWpmThisWeek
    
    init {
        val database = VoxTypeDatabase.getInstance(application)
        repository = UserStatisticsRepository(database)
        allStatistics = repository.getAllStatistics().asLiveData()
        loadWeeklyStatistics()
        loadMonthlyStatistics()
    }
    
    fun recordTypingSession(
        wordsTyped: Int,
        wpm: Float,
        appPackage: String? = null,
        language: String? = null
    ) {
        viewModelScope.launch {
            try {
                repository.addOrUpdateTodayStatistics(wordsTyped, wpm, appPackage, language)
                refreshStatistics()
                _errorMessage.value = null
            } catch (e: Exception) {
                _errorMessage.value = "Failed to record typing session: ${e.message}"
            }
        }
    }
    
    fun getStatisticsByDateRange(startDate: String, endDate: String) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val stats = repository.getStatisticsByDateRange(startDate, endDate)
                // You can emit this through another LiveData if needed
                _errorMessage.value = null
            } catch (e: Exception) {
                _errorMessage.value = "Failed to load statistics: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    private fun loadWeeklyStatistics() {
        viewModelScope.launch {
            try {
                val stats = repository.getLastWeekStatistics()
                _weeklyStats.value = stats
                
                val weekAgo = repository.getWeekAgoDateString()
                val today = repository.getTodayDateString()
                
                val totalWords = repository.getTotalWordsTypedInRange(weekAgo, today)
                _totalWordsThisWeek.value = totalWords
                
                val avgWpm = repository.getAverageWpmInRange(weekAgo, today)
                _averageWpmThisWeek.value = avgWpm
                
            } catch (e: Exception) {
                _errorMessage.value = "Failed to load weekly statistics: ${e.message}"
            }
        }
    }
    
    private fun loadMonthlyStatistics() {
        viewModelScope.launch {
            try {
                val stats = repository.getLastMonthStatistics()
                _monthlyStats.value = stats
            } catch (e: Exception) {
                _errorMessage.value = "Failed to load monthly statistics: ${e.message}"
            }
        }
    }
    
    private fun refreshStatistics() {
        loadWeeklyStatistics()
        loadMonthlyStatistics()
    }
    
    fun getTodayStatistics(): LiveData<UserStatistics?> {
        val liveData = MutableLiveData<UserStatistics?>()
        viewModelScope.launch {
            try {
                val today = repository.getTodayDateString()
                val stats = repository.getStatisticsByDate(today)
                liveData.value = stats
            } catch (e: Exception) {
                _errorMessage.value = "Failed to load today's statistics: ${e.message}"
                liveData.value = null
            }
        }
        return liveData
    }
    
    fun clearOldStatistics() {
        viewModelScope.launch {
            try {
                val monthAgo = repository.getMonthAgoDateString()
                repository.deleteStatisticsBeforeDate(monthAgo)
                refreshStatistics()
                _errorMessage.value = null
            } catch (e: Exception) {
                _errorMessage.value = "Failed to clear old statistics: ${e.message}"
            }
        }
    }
    
    fun clearError() {
        _errorMessage.value = null
    }
}