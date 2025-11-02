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
                val systemPrompt = """You are a voice-to-text formatter. Transform raw transcriptions into properly formatted text.

RULES:
1. Add punctuation (. , ? ! : ;) based on sentence structure and tone
2. Capitalize: sentences, proper nouns, "I", acronyms
3. Fix only obvious errors, preserve speaker's natural voice
4. Handle speech patterns:
   - Remove excessive "um", "uh" (keep if meaningful)
   - Fix "gonna"→"going to", "wanna"→"want to" ONLY in formal mode
5. Punctuation commands: "period"→".", "comma"→",", "question mark"→"?"
6. Indian English: Keep phrases like "do the needful", "prepone", "revert back"
7. Numbers: Keep "lakh", "crore" as spoken

MODE: ${mode.name}
${when (mode) {
    TextMode.GENERAL -> "Natural conversational style"
    TextMode.EMAIL -> "Professional email format"
    TextMode.CHAT -> "Casual messaging style"
    TextMode.FORMAL -> "Business formal style"
}}

OUTPUT: Return ONLY formatted text, nothing else."""
                
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
                    model = "mixtral-8x7b-32768", // Better quality model for formatting
                    messages = listOf(
                        Message("system", systemPrompt),
                        Message("user", userPrompt)
                    ),
                    temperature = 0.3,  // Lower for consistent formatting
                    max_tokens = 200,    // Slightly more tokens for complete sentences
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
                
                // Use "text" format for cleaner output
                val responseFormatBody = "text".toRequestBody("text/plain".toMediaTypeOrNull())
                val languageBody = language.toRequestBody("text/plain".toMediaTypeOrNull())
                
                // Improved prompt for Indian English with proper context
                val whisperPrompt = buildString {
                    append("This is conversational speech in ${if (language == "hi") "Hindi" else "Indian English"}. ")
                    append("Common phrases include: do the needful, prepone, postpone, out of station, revert back, ")
                    append("kindly do the same, good name, pass out (graduate), cousin brother/sister. ")
                    append("Numbers may be in lakhs and crores. ")
                    if (!prompt.isNullOrEmpty()) {
                        append("Previous context: $prompt ")
                    }
                    append("Transcribe accurately preserving the speaker's words.")
                }
                
                val promptBody = whisperPrompt.toRequestBody("text/plain".toMediaTypeOrNull())
                val temperatureBody = "0.0".toRequestBody("text/plain".toMediaTypeOrNull())  // Zero for maximum accuracy
                
                // Call Whisper API
                val response = api.transcribe(
                    model = modelBody,
                    file = audioPart,
                    responseFormat = responseFormatBody,
                    language = languageBody,
                    prompt = promptBody,
                    temperature = temperatureBody
                )
                
                // Return the raw transcription (will be formatted separately)
                return@withContext response.text.trim()
                
            } ?: throw Exception("Groq API not configured. Please add your API key in settings.")
        } catch (e: Exception) {
            android.util.Log.e("GroqProcessor", "Transcription error: ${e.message}")
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