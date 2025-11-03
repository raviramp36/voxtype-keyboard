package com.voxtype.keyboard.database.dao

import androidx.room.*
import com.voxtype.keyboard.database.entities.Snippet
import kotlinx.coroutines.flow.Flow

@Dao
interface SnippetDao {
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSnippet(snippet: Snippet): Long
    
    @Update
    suspend fun updateSnippet(snippet: Snippet)
    
    @Delete
    suspend fun deleteSnippet(snippet: Snippet)
    
    @Query("SELECT * FROM snippets WHERE id = :id")
    suspend fun getSnippetById(id: Long): Snippet?
    
    @Query("SELECT * FROM snippets WHERE trigger = :trigger LIMIT 1")
    suspend fun getSnippetByTrigger(trigger: String): Snippet?
    
    @Query("SELECT * FROM snippets ORDER BY createdDate DESC")
    fun getAllSnippets(): Flow<List<Snippet>>
    
    @Query("SELECT * FROM snippets WHERE category = :category ORDER BY createdDate DESC")
    fun getSnippetsByCategory(category: String): Flow<List<Snippet>>
    
    @Query("SELECT * FROM snippets WHERE trigger LIKE '%' || :query || '%' OR content LIKE '%' || :query || '%' ORDER BY usageCount DESC")
    suspend fun searchSnippets(query: String): List<Snippet>
    
    @Query("UPDATE snippets SET usageCount = usageCount + 1 WHERE id = :id")
    suspend fun incrementUsageCount(id: Long)
    
    @Query("SELECT * FROM snippets ORDER BY usageCount DESC LIMIT :limit")
    suspend fun getMostUsedSnippets(limit: Int = 10): List<Snippet>
    
    @Query("SELECT DISTINCT category FROM snippets ORDER BY category")
    suspend fun getAllCategories(): List<String>
    
    @Query("SELECT COUNT(*) FROM snippets WHERE category = :category")
    suspend fun getSnippetCountByCategory(category: String): Int
    
    @Query("SELECT COUNT(*) FROM snippets")
    suspend fun getTotalSnippetCount(): Int
    
    @Query("DELETE FROM snippets WHERE category = :category")
    suspend fun deleteSnippetsByCategory(category: String)
    
    @Query("DELETE FROM snippets")
    suspend fun deleteAllSnippets()
}