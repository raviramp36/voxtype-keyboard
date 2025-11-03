package com.voxtype.keyboard.database.dao

import androidx.room.*
import com.voxtype.keyboard.database.entities.UserStatistics
import kotlinx.coroutines.flow.Flow

@Dao
interface UserStatisticsDao {
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStatistics(statistics: UserStatistics): Long
    
    @Update
    suspend fun updateStatistics(statistics: UserStatistics)
    
    @Delete
    suspend fun deleteStatistics(statistics: UserStatistics)
    
    @Query("SELECT * FROM user_statistics WHERE id = :id")
    suspend fun getStatisticsById(id: Long): UserStatistics?
    
    @Query("SELECT * FROM user_statistics WHERE date = :date LIMIT 1")
    suspend fun getStatisticsByDate(date: String): UserStatistics?
    
    @Query("SELECT * FROM user_statistics ORDER BY date DESC")
    fun getAllStatistics(): Flow<List<UserStatistics>>
    
    @Query("SELECT * FROM user_statistics WHERE date >= :startDate AND date <= :endDate ORDER BY date DESC")
    suspend fun getStatisticsByDateRange(startDate: String, endDate: String): List<UserStatistics>
    
    @Query("SELECT * FROM user_statistics ORDER BY date DESC LIMIT :limit")
    suspend fun getRecentStatistics(limit: Int = 30): List<UserStatistics>
    
    @Query("SELECT SUM(wordsTyped) FROM user_statistics WHERE date >= :startDate AND date <= :endDate")
    suspend fun getTotalWordsTypedInRange(startDate: String, endDate: String): Int?
    
    @Query("SELECT AVG(wpm) FROM user_statistics WHERE date >= :startDate AND date <= :endDate AND wpm > 0")
    suspend fun getAverageWpmInRange(startDate: String, endDate: String): Float?
    
    @Query("SELECT MAX(wpm) FROM user_statistics WHERE date >= :startDate AND date <= :endDate")
    suspend fun getMaxWpmInRange(startDate: String, endDate: String): Float?
    
    @Query("SELECT COUNT(DISTINCT CASE WHEN appsUsed != '' THEN appsUsed END) FROM user_statistics WHERE date >= :startDate AND date <= :endDate")
    suspend fun getUniqueAppsUsedInRange(startDate: String, endDate: String): Int
    
    @Query("SELECT COUNT(DISTINCT CASE WHEN languagesUsed != '' THEN languagesUsed END) FROM user_statistics WHERE date >= :startDate AND date <= :endDate")
    suspend fun getUniqueLanguagesUsedInRange(startDate: String, endDate: String): Int
    
    @Query("SELECT * FROM user_statistics ORDER BY date DESC LIMIT 7")
    suspend fun getLastWeekStatistics(): List<UserStatistics>
    
    @Query("SELECT * FROM user_statistics ORDER BY date DESC LIMIT 30")
    suspend fun getLastMonthStatistics(): List<UserStatistics>
    
    @Query("DELETE FROM user_statistics WHERE date < :beforeDate")
    suspend fun deleteStatisticsBeforeDate(beforeDate: String)
    
    @Query("DELETE FROM user_statistics")
    suspend fun deleteAllStatistics()
}