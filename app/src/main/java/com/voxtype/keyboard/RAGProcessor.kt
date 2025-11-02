package com.voxtype.keyboard

import android.content.Context
import android.content.SharedPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

class RAGProcessor(private val context: Context) {
    
    private val prefs: SharedPreferences = context.getSharedPreferences("voxtype_prefs", Context.MODE_PRIVATE)
    private val ragService: RAGService
    
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(getRAGBaseUrl())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        
        ragService = retrofit.create(RAGService::class.java)
    }
    
    suspend fun processText(
        rawText: String, 
        context: String = "",
        mode: TextMode = TextMode.GENERAL
    ): String = withContext(Dispatchers.IO) {
        try {
            val apiKey = getRAGApiKey()
            if (apiKey.isEmpty()) {
                // If no API key, return with basic processing
                return@withContext basicProcessing(rawText)
            }
            
            val request = TranscriptionRequest(
                text = rawText,
                context = context,
                language = getCurrentLanguage(),
                mode = mode.value,
                features = listOf(
                    "remove_fillers",
                    "add_punctuation",
                    "fix_grammar",
                    "smart_formatting",
                    "context_awareness"
                )
            )
            
            val response = ragService.processText(
                apiKey = "Bearer $apiKey",
                request = request
            )
            
            return@withContext response.processedText
            
        } catch (e: Exception) {
            // Fallback to basic processing if RAG fails
            return@withContext basicProcessing(rawText)
        }
    }
    
    private fun basicProcessing(text: String): String {
        // Basic text processing without AI
        return text
            .removeFillerWords()
            .addBasicPunctuation()
            .fixBasicCapitalization()
    }
    
    private fun String.removeFillerWords(): String {
        val fillers = listOf(
            "um", "uh", "er", "ah", "like", "you know", 
            "I mean", "basically", "actually", "sort of", "kind of"
        )
        var result = this
        fillers.forEach { filler ->
            result = result.replace(Regex("\\b$filler\\b", RegexOption.IGNORE_CASE), "")
        }
        return result.replace(Regex("\\s+"), " ").trim()
    }
    
    private fun String.addBasicPunctuation(): String {
        var result = this
        
        // Add period at the end if missing
        if (result.isNotEmpty() && !result.last().isPunctuation()) {
            result += "."
        }
        
        // Capitalize after periods
        result = result.replace(Regex("\\.\\s+(.)")) { match ->
            ". ${match.groupValues[1].uppercase()}"
        }
        
        return result
    }
    
    private fun String.fixBasicCapitalization(): String {
        if (isEmpty()) return this
        
        // Capitalize first letter
        var result = this[0].uppercase() + substring(1)
        
        // Capitalize I
        result = result.replace(Regex("\\bi\\b"), "I")
        
        // Capitalize proper nouns (basic list)
        val properNouns = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
        properNouns.forEach { noun ->
            result = result.replace(Regex("\\b${noun.lowercase()}\\b"), noun)
        }
        
        return result
    }
    
    private fun Char.isPunctuation(): Boolean {
        return this in ".!?,;:"
    }
    
    private fun getRAGApiKey(): String {
        return prefs.getString("rag_api_key", "") ?: ""
    }
    
    private fun getRAGBaseUrl(): String {
        return prefs.getString("rag_base_url", "https://api.example.com/") ?: "https://api.example.com/"
    }
    
    private fun getCurrentLanguage(): String {
        return prefs.getString("language", "en") ?: "en"
    }
    
    fun saveSettings(apiKey: String, baseUrl: String) {
        prefs.edit().apply {
            putString("rag_api_key", apiKey)
            putString("rag_base_url", baseUrl)
            apply()
        }
    }
    
    enum class TextMode(val value: String) {
        GENERAL("general"),
        EMAIL("email"),
        CHAT("chat"),
        FORMAL("formal"),
        CODE("code"),
        CREATIVE("creative")
    }
}

// API Interface
interface RAGService {
    @POST("process")
    suspend fun processText(
        @Header("Authorization") apiKey: String,
        @Body request: TranscriptionRequest
    ): TranscriptionResponse
}

// Data classes
data class TranscriptionRequest(
    val text: String,
    val context: String? = null,
    val language: String = "en",
    val mode: String = "general",
    val features: List<String> = emptyList()
)

data class TranscriptionResponse(
    val processedText: String,
    val corrections: List<Correction> = emptyList(),
    val suggestions: List<String> = emptyList(),
    val confidence: Float = 1.0f
)

data class Correction(
    val original: String,
    val corrected: String,
    val type: String,
    val position: Int
)