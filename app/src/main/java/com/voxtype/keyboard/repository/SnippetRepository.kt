package com.voxtype.keyboard.repository

import com.voxtype.keyboard.database.VoxTypeDatabase
import com.voxtype.keyboard.database.entities.Snippet
import kotlinx.coroutines.flow.Flow
import java.util.Date
class SnippetRepository(
    private val database: VoxTypeDatabase
) {
    private val snippetDao = database.snippetDao()
    
    fun getAllSnippets(): Flow<List<Snippet>> = snippetDao.getAllSnippets()
    
    fun getSnippetsByCategory(category: String): Flow<List<Snippet>> = 
        snippetDao.getSnippetsByCategory(category)
    
    suspend fun addSnippet(trigger: String, content: String, category: String = "General"): Long {
        val snippet = Snippet(
            trigger = trigger,
            content = content,
            category = category,
            usageCount = 0,
            createdDate = Date()
        )
        return snippetDao.insertSnippet(snippet)
    }
    
    suspend fun updateSnippet(snippet: Snippet) = snippetDao.updateSnippet(snippet)
    
    suspend fun deleteSnippet(snippet: Snippet) = snippetDao.deleteSnippet(snippet)
    
    suspend fun getSnippetById(id: Long): Snippet? = snippetDao.getSnippetById(id)
    
    suspend fun getSnippetByTrigger(trigger: String): Snippet? = 
        snippetDao.getSnippetByTrigger(trigger)
    
    suspend fun searchSnippets(query: String): List<Snippet> = 
        snippetDao.searchSnippets(query)
    
    suspend fun incrementUsageCount(id: Long) = snippetDao.incrementUsageCount(id)
    
    suspend fun getMostUsedSnippets(limit: Int = 10): List<Snippet> = 
        snippetDao.getMostUsedSnippets(limit)
    
    suspend fun getAllCategories(): List<String> = snippetDao.getAllCategories()
    
    suspend fun getSnippetCountByCategory(category: String): Int = 
        snippetDao.getSnippetCountByCategory(category)
    
    suspend fun getTotalSnippetCount(): Int = snippetDao.getTotalSnippetCount()
    
    suspend fun deleteSnippetsByCategory(category: String) = 
        snippetDao.deleteSnippetsByCategory(category)
    
    suspend fun deleteAllSnippets() = snippetDao.deleteAllSnippets()
}