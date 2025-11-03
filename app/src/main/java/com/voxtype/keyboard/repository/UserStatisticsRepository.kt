package com.voxtype.keyboard.repository

import com.voxtype.keyboard.database.VoxTypeDatabase
import com.voxtype.keyboard.database.entities.UserStatistics
import kotlinx.coroutines.flow.Flow
import java.text.SimpleDateFormat
import java.util.*
class UserStatisticsRepository(
    private val database: VoxTypeDatabase
) {
    private val userStatisticsDao = database.userStatisticsDao()
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    
    fun getAllStatistics(): Flow<List<UserStatistics>> = userStatisticsDao.getAllStatistics()
    
    suspend fun addOrUpdateTodayStatistics(
        wordsTyped: Int = 0,
        wpm: Float = 0f,
        appPackage: String? = null,
        language: String? = null
    ) {
        val today = dateFormat.format(Date())
        val existingStats = userStatisticsDao.getStatisticsByDate(today)
        
        if (existingStats != null) {
            val updatedStats = existingStats.copy(
                wordsTyped = existingStats.wordsTyped + wordsTyped,
                wpm = if (wpm > 0) ((existingStats.wpm + wpm) / 2) else existingStats.wpm,
                appsUsed = updateCommaSeparatedList(existingStats.appsUsed, appPackage),
                languagesUsed = updateCommaSeparatedList(existingStats.languagesUsed, language)
            )
            userStatisticsDao.updateStatistics(updatedStats)
        } else {
            val newStats = UserStatistics(
                date = today,
                wordsTyped = wordsTyped,
                wpm = wpm,
                appsUsed = appPackage ?: "",
                languagesUsed = language ?: ""
            )
            userStatisticsDao.insertStatistics(newStats)
        }
    }
    
    suspend fun getStatisticsByDate(date: String): UserStatistics? = 
        userStatisticsDao.getStatisticsByDate(date)
    
    suspend fun getStatisticsByDateRange(startDate: String, endDate: String): List<UserStatistics> = 
        userStatisticsDao.getStatisticsByDateRange(startDate, endDate)
    
    suspend fun getRecentStatistics(limit: Int = 30): List<UserStatistics> = 
        userStatisticsDao.getRecentStatistics(limit)
    
    suspend fun getTotalWordsTypedInRange(startDate: String, endDate: String): Int = 
        userStatisticsDao.getTotalWordsTypedInRange(startDate, endDate) ?: 0
    
    suspend fun getAverageWpmInRange(startDate: String, endDate: String): Float = 
        userStatisticsDao.getAverageWpmInRange(startDate, endDate) ?: 0f
    
    suspend fun getMaxWpmInRange(startDate: String, endDate: String): Float = 
        userStatisticsDao.getMaxWpmInRange(startDate, endDate) ?: 0f
    
    suspend fun getLastWeekStatistics(): List<UserStatistics> = 
        userStatisticsDao.getLastWeekStatistics()
    
    suspend fun getLastMonthStatistics(): List<UserStatistics> = 
        userStatisticsDao.getLastMonthStatistics()
    
    suspend fun deleteStatisticsBeforeDate(beforeDate: String) = 
        userStatisticsDao.deleteStatisticsBeforeDate(beforeDate)
    
    suspend fun deleteAllStatistics() = userStatisticsDao.deleteAllStatistics()
    
    private fun updateCommaSeparatedList(existingList: String, newItem: String?): String {
        if (newItem.isNullOrBlank()) return existingList
        
        val items = existingList.split(",").map { it.trim() }.filter { it.isNotEmpty() }.toMutableSet()
        items.add(newItem)
        return items.joinToString(", ")
    }
    
    // Helper methods for common date operations
    fun getTodayDateString(): String = dateFormat.format(Date())
    
    fun getWeekAgoDateString(): String {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, -7)
        return dateFormat.format(calendar.time)
    }
    
    fun getMonthAgoDateString(): String {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.MONTH, -1)
        return dateFormat.format(calendar.time)
    }
}