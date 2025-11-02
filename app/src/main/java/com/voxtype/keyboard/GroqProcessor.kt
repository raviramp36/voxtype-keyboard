package com.voxtype.keyboard

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import java.util.concurrent.TimeUnit

class GroqProcessor(private val context: Context) {
    
    private var groqApi: GroqApi? = null
    
    init {
        loadSettings()
    }
    
    private fun loadSettings() {
        val prefs = context.getSharedPreferences("voxtype_prefs", Context.MODE_PRIVATE)
        val apiKey = prefs.getString("groq_api_key", "") ?: ""
        var baseUrl = prefs.getString("groq_base_url", "https://api.groq.com/") ?: "https://api.groq.com/"
        
        // Ensure base URL ends with /
        if (!baseUrl.endsWith("/")) {
            baseUrl = "$baseUrl/"
        }
        
        if (apiKey.isNotEmpty()) {
            setupRetrofit(apiKey, baseUrl)
            android.util.Log.d("GroqProcessor", "API configured with key: ${apiKey.take(10)}...")
        } else {
            android.util.Log.w("GroqProcessor", "No API key found in settings")
        }
    }
    
    private fun setupRetrofit(apiKey: String, baseUrl: String) {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        
        val client = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer $apiKey")
                    .addHeader("Content-Type", "application/json")
                    .build()
                chain.proceed(request)
            }
            .build()
        
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        
        groqApi = retrofit.create(GroqApi::class.java)
    }
    
    fun saveSettings(apiKey: String, baseUrl: String = "https://api.groq.com/") {
        var finalUrl = baseUrl
        if (!finalUrl.endsWith("/")) {
            finalUrl = "$finalUrl/"
        }
        
        val prefs = context.getSharedPreferences("voxtype_prefs", Context.MODE_PRIVATE)
        prefs.edit().apply {
            putString("groq_api_key", apiKey)
            putString("groq_base_url", finalUrl)
            apply()
        }
        setupRetrofit(apiKey, finalUrl)
        android.util.Log.d("GroqProcessor", "Settings saved and API reconfigured")
    }
    
    fun reloadSettings() {
        loadSettings()
    }
    
    suspend fun processText(
        rawText: String, 
        context: String = "", 
        mode: TextMode = TextMode.GENERAL
    ): String = withContext(Dispatchers.IO) {
        try {
            groqApi?.let { api ->
                val systemPrompt = """You are an intelligent voice-to-text formatting assistant, similar to Wispr Flow. Your job is to transform raw speech transcription into properly formatted, contextually appropriate text.

CRITICAL RULES:
1. Add proper punctuation (periods, commas, question marks, exclamation points)
2. Capitalize appropriately (sentence starts, proper nouns, I)
3. Fix grammar while preserving the speaker's intent and voice
4. Understand context - if previous text exists, ensure smooth continuation
5. Handle common speech patterns:
   - Remove filler words like "um", "uh", "like" (unless intentionally emphatic)
   - Convert "gonna" → "going to", "wanna" → "want to" (in formal mode)
   - Keep contractions in casual mode
6. Format based on detected intent:
   - Questions should end with ?
   - Excited statements with !
   - Lists with proper bullets or numbering if mentioned
7. Preserve technical terms, names, and specific phrases exactly
8. If user says "period", "comma", "new line" etc., treat as punctuation commands
9. Handle Indian English naturally (recognise phrases like "do the needful", "revert back")

OUTPUT: Return ONLY the formatted text, no explanations or alternatives.

MODE: ${mode.name}
${when (mode) {
    TextMode.GENERAL -> "Balance between casual and professional. Natural flow."
    TextMode.EMAIL -> "Professional email format. Complete sentences. Proper salutations if starting email."
    TextMode.CHAT -> "Casual messaging. Can use shortcuts, emojis if appropriate. Keep conversational."
    TextMode.FORMAL -> "Business formal. No contractions. Professional vocabulary."
}}"""
                
                val userPrompt = buildString {
                    if (context.isNotEmpty()) {
                        appendLine("PREVIOUS TEXT (for context continuation):")
                        appendLine(context)
                        appendLine()
                    }
                    appendLine("RAW SPEECH TO FORMAT:")
                    appendLine(rawText)
                    appendLine()
                    appendLine("INSTRUCTIONS:")
                    appendLine("- Transform the raw speech into properly formatted text")
                    appendLine("- Ensure it flows naturally from any previous text")
                    appendLine("- Apply appropriate punctuation and capitalization")
                    appendLine("- Fix grammar while keeping the speaker's voice")
                    if (context.isEmpty()) {
                        appendLine("- This appears to be the start of a new message/document")
                    } else {
                        appendLine("- This is a continuation - ensure smooth flow")
                    }
                }
                
                val request = GroqRequest(
                    model = "llama3-8b-8192", // Fast model for keyboard use
                    messages = listOf(
                        Message("system", systemPrompt),
                        Message("user", userPrompt)
                    ),
                    temperature = 0.7,
                    max_tokens = 150,
                    stream = false
                )
                
                val response = api.complete(request)
                return@withContext response.choices.firstOrNull()?.message?.content?.trim() 
                    ?: fallbackProcessing(rawText, mode)
            } ?: fallbackProcessing(rawText, mode)
        } catch (e: Exception) {
            e.printStackTrace()
            return@withContext fallbackProcessing(rawText, mode)
        }
    }
    
    private fun fallbackProcessing(text: String, mode: TextMode): String {
        // Basic fallback processing when Groq API is not available
        return when (mode) {
            TextMode.EMAIL -> {
                val processed = text.trim()
                    .replaceFirstChar { it.uppercase() }
                if (!processed.endsWith(".")) "$processed." else processed
            }
            TextMode.FORMAL -> {
                text.trim()
                    .split(". ")
                    .joinToString(". ") { sentence ->
                        sentence.trim().replaceFirstChar { it.uppercase() }
                    }
            }
            else -> {
                // Basic capitalization and punctuation
                text.trim().replaceFirstChar { it.uppercase() }
            }
        }
    }
    
    enum class TextMode {
        GENERAL, EMAIL, CHAT, FORMAL
    }
    
    suspend fun transcribeAudio(
        audioFile: File,
        language: String = "en",  // Default to English, can be "hi" for Hindi
        prompt: String? = null  // Optional context for better transcription
    ): String = withContext(Dispatchers.IO) {
        try {
            groqApi?.let { api ->
                // Prepare multipart request
                val modelBody = "whisper-large-v3-turbo".toRequestBody("text/plain".toMediaTypeOrNull())
                
                val requestFile = audioFile.asRequestBody("audio/*".toMediaTypeOrNull())
                val audioPart = MultipartBody.Part.createFormData("file", audioFile.name, requestFile)
                
                val responseFormatBody = "verbose_json".toRequestBody("text/plain".toMediaTypeOrNull())
                val languageBody = language.toRequestBody("text/plain".toMediaTypeOrNull())
                
                val promptBody = prompt?.let {
                    // Add context hints for better transcription
                    val contextPrompt = """
                        Context for better transcription:
                        - This is voice input for text messaging/typing
                        - User may use Indian English phrases
                        - Common terms: "prepone", "do the needful", "revert back"
                        - User may say punctuation commands like "period", "comma", "new line"
                        - $it
                    """.trimIndent()
                    contextPrompt.toRequestBody("text/plain".toMediaTypeOrNull())
                }
                
                val temperatureBody = "0.2".toRequestBody("text/plain".toMediaTypeOrNull())  // Lower temperature for more accurate transcription
                
                // Call Whisper API
                val response = api.transcribe(
                    model = modelBody,
                    file = audioPart,
                    responseFormat = responseFormatBody,
                    language = languageBody,
                    prompt = promptBody,
                    temperature = temperatureBody
                )
                
                // Whisper already includes punctuation!
                return@withContext response.text
                
            } ?: throw Exception("Groq API not configured. Please add your API key in settings.")
        } catch (e: Exception) {
            e.printStackTrace()
            throw Exception("Transcription failed: ${e.message}")
        }
    }
}

// Groq API interfaces
interface GroqApi {
    @POST("openai/v1/chat/completions")
    suspend fun complete(@Body request: GroqRequest): GroqResponse
    
    @Multipart
    @POST("openai/v1/audio/transcriptions")
    suspend fun transcribe(
        @Part("model") model: RequestBody,
        @Part file: MultipartBody.Part,
        @Part("response_format") responseFormat: RequestBody? = null,
        @Part("language") language: RequestBody? = null,
        @Part("prompt") prompt: RequestBody? = null,
        @Part("temperature") temperature: RequestBody? = null
    ): WhisperResponse
}

data class GroqRequest(
    val model: String,
    val messages: List<Message>,
    val temperature: Double = 0.7,
    val max_tokens: Int = 150,
    val stream: Boolean = false
)

data class Message(
    val role: String,
    val content: String
)

data class GroqResponse(
    val id: String,
    val choices: List<Choice>,
    val usage: Usage
)

data class Choice(
    val index: Int,
    val message: Message,
    val finish_reason: String?
)

data class Usage(
    val prompt_tokens: Int,
    val completion_tokens: Int,
    val total_tokens: Int
)

// Whisper API models
data class WhisperResponse(
    val text: String,
    val language: String? = null,
    val duration: Float? = null,
    val words: List<Word>? = null,
    val segments: List<Segment>? = null
)

data class Word(
    val word: String,
    val start: Float,
    val end: Float,
    val probability: Float
)

data class Segment(
    val id: Int,
    val seek: Int,
    val start: Float,
    val end: Float,
    val text: String,
    val tokens: List<Int>,
    val temperature: Float,
    val avg_logprob: Float,
    val compression_ratio: Float,
    val no_speech_prob: Float
)