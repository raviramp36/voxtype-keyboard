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
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class GroqProcessor(private val context: Context) {
    
    private var groqApi: GroqApi? = null
    private lateinit var advancedSettings: AdvancedSettingsManager
    
    init {
        loadSettings()
        advancedSettings = AdvancedSettingsManager(context)
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
        mode: TextMode = TextMode.GENERAL,
        language: String = "auto",
        isWhisperMode: Boolean = false
    ): String = withContext(Dispatchers.IO) {
        try {
            groqApi?.let { api ->
                val detectedLanguage = if (language == "auto") detectLanguage(rawText) else language
                val whisperModeText = if (isWhisperMode) "\nWHISPER MODE: This text was whispered - adjust confidence and be more conservative with changes." else ""
                
                val systemPrompt = """You are a voice-to-text formatter. Transform raw transcriptions into properly formatted text.

LANGUAGE: ${detectedLanguage.uppercase()}
${getLanguageSpecificRules(detectedLanguage)}

RULES:
1. Add punctuation (. , ? ! : ;) based on sentence structure and tone
2. Capitalize: sentences, proper nouns, "I", acronyms
3. Fix only obvious errors, preserve speaker's natural voice
4. Handle speech patterns:
   - Remove excessive "um", "uh" (keep if meaningful)
   - Fix "gonna"→"going to", "wanna"→"want to" ONLY in formal mode
5. Punctuation commands: "period"→".", "comma"→",", "question mark"→"?"
6. Support code-switching between ${detectedLanguage} and English naturally
${whisperModeText}

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
    
    suspend fun applySmartFormatting(text: String, context: String = ""): String {
        var formattedText = text
        
        try {
            // Apply list formatting first (detects numbered lists)
            formattedText = applyListFormatting(formattedText)
            
            // Apply smart punctuation if enabled
            if (advancedSettings.isSmartPunctuationEnabled()) {
                formattedText = applySmartPunctuation(formattedText)
            }
            
            // Apply smart capitalization if enabled
            if (advancedSettings.isSmartCapitalizationEnabled()) {
                formattedText = applySmartCapitalization(formattedText)
            }
            
            // Apply number and date formatting
            formattedText = applyNumberFormatting(formattedText)
            formattedText = applyDateFormatting(formattedText)
            
        } catch (e: Exception) {
            android.util.Log.e("GroqProcessor", "Error in smart formatting: ${e.message}")
        }
        
        return formattedText
    }
    
    private fun applyListFormatting(text: String): String {
        var result = text
        
        // Pattern for numbered lists spoken naturally
        // Examples: "one is this two is that three is something"
        // "first do this second do that third do something"
        
        // Handle numbered lists with "is", "do", "says", etc.
        val numberWords = listOf(
            "one" to "1", "two" to "2", "three" to "3", "four" to "4", "five" to "5",
            "six" to "6", "seven" to "7", "eight" to "8", "nine" to "9", "ten" to "10",
            "first" to "1", "second" to "2", "third" to "3", "fourth" to "4", "fifth" to "5",
            "sixth" to "6", "seventh" to "7", "eighth" to "8", "ninth" to "9", "tenth" to "10"
        )
        
        // Detect list patterns and format them
        for ((word, number) in numberWords) {
            // Pattern: "one is/do/says/means X two is/do/says/means Y"
            result = result.replace(
                Regex("\\b$word\\s+(is|do|does|says|means|contains|includes)\\s+([^.!?]+?)(?=\\s+(?:one|two|three|four|five|six|seven|eight|nine|ten|first|second|third|fourth|fifth|sixth|seventh|eighth|ninth|tenth)\\s+(?:is|do|does|says|means|contains|includes)|$)", RegexOption.IGNORE_CASE),
                "\n$number. ${"\$2".replaceFirstChar { it.uppercase() }}"
            )
        }
        
        // Clean up if the text starts with a newline
        result = result.trimStart('\n')
        
        // If we detected a list, ensure proper formatting
        if (result.contains(Regex("\\n\\d+\\."))) {
            // Ensure each list item ends with proper punctuation
            result = result.replace(Regex("(\\n\\d+\\.[^\\n.!?]+)(?=\\n|$)")) { match ->
                "${match.value}."
            }
            
            // Check if list starts with a number (for future title addition if needed)
            if (result.matches(Regex("^\\d+\\..*"))) {
                // Don't add title if it already starts with a number
            }
        }
        
        return result
    }
    
    private fun applySmartPunctuation(text: String): String {
        var result = text
        
        // Add periods to sentences that clearly end
        result = result.replace(Regex("\\b(thank you|thanks|bye|goodbye)\\s*$"), "$1.")
        
        // Add question marks to obvious questions
        result = result.replace(Regex("^(what|how|when|where|why|who|which|can|could|would|will|is|are|do|does|did)\\b.*(?<!\\?)$"), "$0?")
        
        // Add commas before common conjunctions
        result = result.replace(Regex("\\s+(and|but|or|so|yet)\\s+"), ", $1 ")
        
        // Fix spacing around punctuation
        result = result.replace(Regex("\\s+([.!?])"), "$1")
        result = result.replace(Regex("([.!?])\\s*([A-Z])"), "$1 $2")
        
        return result
    }
    
    private fun applySmartCapitalization(text: String): String {
        var result = text
        
        // Capitalize first letter
        result = result.replaceFirstChar { it.uppercase() }
        
        // Capitalize after periods, exclamation marks, question marks
        result = result.replace(Regex("([.!?])\\s+([a-z])")) { match ->
            "${match.groupValues[1]} ${match.groupValues[2].uppercase()}"
        }
        
        // Capitalize "I"
        result = result.replace(Regex("\\bi\\b"), "I")
        
        // Capitalize common proper nouns (basic set)
        val properNouns = listOf("google", "apple", "microsoft", "amazon", "facebook", "twitter", "linkedin", "instagram", "whatsapp", "telegram")
        properNouns.forEach { noun ->
            result = result.replace(Regex("\\b$noun\\b", RegexOption.IGNORE_CASE), noun.replaceFirstChar { it.uppercase() })
        }
        
        return result
    }
    
    private fun applyNumberFormatting(text: String): String {
        var result = text
        
        // Convert written numbers to digits in specific contexts
        val numberWords = mapOf(
            "one" to "1", "two" to "2", "three" to "3", "four" to "4", "five" to "5",
            "six" to "6", "seven" to "7", "eight" to "8", "nine" to "9", "ten" to "10"
        )
        
        // Only convert in contexts like "call me at", "room number", "page", etc.
        val numberContexts = listOf("call me at", "room", "page", "number", "at", "phone")
        
        numberContexts.forEach { context ->
            numberWords.forEach { (word, digit) ->
                result = result.replace(Regex("($context\\s+)$word\\b", RegexOption.IGNORE_CASE), "$1$digit")
            }
        }
        
        // Format phone numbers (basic Indian format)
        result = result.replace(Regex("(\\d{3})\\s*(\\d{3})\\s*(\\d{4})"), "$1-$2-$3")
        
        return result
    }
    
    private fun applyDateFormatting(text: String): String {
        var result = text
        
        // Convert common date expressions
        val today = LocalDate.now()
        val dateFormatter = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")
        
        result = result.replace(Regex("\\btoday\\b", RegexOption.IGNORE_CASE), today.format(dateFormatter))
        result = result.replace(Regex("\\btomorrow\\b", RegexOption.IGNORE_CASE), today.plusDays(1).format(dateFormatter))
        result = result.replace(Regex("\\byesterday\\b", RegexOption.IGNORE_CASE), today.minusDays(1).format(dateFormatter))
        
        return result
    }
    
    suspend fun getContextualEmojiSuggestions(text: String, appContext: String): List<String> {
        return advancedSettings.getEmojiSuggestions(text, appContext)
    }
    
    private fun detectLanguage(text: String): String {
        // Simple language detection based on script and common words
        val hindiPattern = "[\\u0900-\\u097F]".toRegex()
        val englishWords = setOf("the", "and", "is", "to", "of", "in", "that", "have", "for", "not", "with", "he", "as", "you", "do", "at")
        val hindiWords = setOf("है", "का", "के", "की", "में", "से", "को", "और", "यह", "वह", "पर", "तो", "भी", "हो", "अगर")
        
        val words = text.lowercase().split("\\s+".toRegex())
        val hindiScriptCount = hindiPattern.findAll(text).count()
        val englishWordCount = words.count { it in englishWords }
        val hindiWordCount = words.count { it in hindiWords }
        
        return when {
            hindiScriptCount > 0 || hindiWordCount > englishWordCount -> "hi"
            else -> "en"
        }
    }
    
    private fun getLanguageSpecificRules(language: String): String {
        return when (language) {
            "hi" -> """
HINDI SPECIFIC RULES:
- Keep Devanagari script intact
- Handle romanized Hindi naturally
- Common English mixing: okay, sorry, thanks, please
- Respectful forms: aap, ji, sahab
- Numbers: ek, do, teen, paanch, das, sau, hazaar, lakh, crore
            """.trimIndent()
            
            "en" -> """
INDIAN ENGLISH SPECIFIC RULES:
- Keep phrases: do the needful, prepone, revert back, out of station
- Numbers: lakh, crore, paisa
- Respectful terms: sir, madam, ji
- Common Hindi mixing: acha, theek hai, bas, kitna, kya
            """.trimIndent()
            
            else -> "Standard English formatting rules"
        }
    }
    
    enum class TextMode {
        GENERAL, EMAIL, CHAT, FORMAL
    }
    
    suspend fun transcribeAudio(
        audioFile: File,
        language: String = "auto",  // Auto-detect or specific language
        prompt: String? = null,  // Optional context for better transcription
        isWhisperMode: Boolean = false  // Adjust settings for whisper
    ): String = withContext(Dispatchers.IO) {
        try {
            groqApi?.let { api ->
                // Prepare multipart request
                val modelBody = "whisper-large-v3-turbo".toRequestBody("text/plain".toMediaTypeOrNull())
                
                val requestFile = audioFile.asRequestBody("audio/*".toMediaTypeOrNull())
                val audioPart = MultipartBody.Part.createFormData("file", audioFile.name, requestFile)
                
                // Use "json" format for proper parsing
                val responseFormatBody = "json".toRequestBody("text/plain".toMediaTypeOrNull())
                
                // Detect language if auto
                val finalLanguage = if (language == "auto") "en" else language  // Default to English for Whisper auto-detect
                val languageBody = finalLanguage.toRequestBody("text/plain".toMediaTypeOrNull())
                
                // Improved prompt for multi-language with whisper mode support
                val whisperPrompt = buildString {
                    when (finalLanguage) {
                        "hi" -> {
                            append("This is conversational speech in Hindi with possible English code-switching. ")
                            append("Common English words mixed in: okay, sorry, thanks, please, computer, mobile, etc. ")
                        }
                        else -> {
                            append("This is conversational speech in Indian English with possible Hindi code-switching. ")
                            append("Common phrases include: do the needful, prepone, postpone, out of station, revert back, ")
                            append("kindly do the same, good name, pass out (graduate), cousin brother/sister. ")
                            append("Hindi words may be mixed in naturally. ")
                        }
                    }
                    append("Numbers may be in lakhs and crores. ")
                    if (isWhisperMode) {
                        append("This was whispered speech - may be softer or less clear. ")
                    }
                    if (!prompt.isNullOrEmpty()) {
                        append("Previous context: $prompt ")
                    }
                    append("Transcribe accurately preserving the speaker's words and any natural language mixing.")
                }
                
                val promptBody = whisperPrompt.toRequestBody("text/plain".toMediaTypeOrNull())
                // Adjust temperature based on whisper mode
                val temperature = if (isWhisperMode) "0.1" else "0.0"  // Slightly higher for whisper mode
                val temperatureBody = temperature.toRequestBody("text/plain".toMediaTypeOrNull())
                
                // Call Whisper API
                val response = try {
                    api.transcribe(
                        model = modelBody,
                        file = audioPart,
                        responseFormat = responseFormatBody,
                        language = languageBody,
                        prompt = promptBody,
                        temperature = temperatureBody
                    )
                } catch (e: retrofit2.HttpException) {
                    val errorBody = e.response()?.errorBody()?.string()
                    android.util.Log.e("GroqProcessor", "HTTP error: ${e.code()} - $errorBody")
                    throw Exception("API error: ${e.code()} - ${e.message()}")
                }
                
                // Return the raw transcription (will be formatted separately)
                return@withContext response.text.trim()
                
            } ?: throw Exception("Groq API not configured. Please add your API key in settings.")
        } catch (e: com.google.gson.JsonSyntaxException) {
            android.util.Log.e("GroqProcessor", "JSON parsing error: ${e.message}")
            throw Exception("Response format error. Please check API configuration.")
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
    val segments: List<Segment>? = null,
    val task: String? = null,
    val temperature: Float? = null
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