package com.voxtype.keyboard

import android.content.Context
import android.content.SharedPreferences
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import kotlinx.coroutines.runBlocking

@RunWith(MockitoJUnitRunner::class)
class RAGProcessorTest {
    
    @Mock
    private lateinit var mockContext: Context
    
    @Mock
    private lateinit var mockSharedPreferences: SharedPreferences
    
    @Mock
    private lateinit var mockEditor: SharedPreferences.Editor
    
    private lateinit var ragProcessor: RAGProcessor
    
    @Before
    fun setUp() {
        `when`(mockContext.getSharedPreferences("voxtype_prefs", Context.MODE_PRIVATE))
            .thenReturn(mockSharedPreferences)
        `when`(mockSharedPreferences.edit()).thenReturn(mockEditor)
        `when`(mockEditor.putString(anyString(), anyString())).thenReturn(mockEditor)
        
        ragProcessor = RAGProcessor(mockContext)
    }
    
    @Test
    fun testSaveSettings() {
        // Test saving API settings
        val apiKey = "test-api-key"
        val baseUrl = "https://api.test.com/"
        
        ragProcessor.saveSettings(apiKey, baseUrl)
        
        verify(mockEditor).putString("rag_api_key", apiKey)
        verify(mockEditor).putString("rag_base_url", baseUrl)
        verify(mockEditor).apply()
    }
    
    @Test
    fun testProcessTextWithGeneralMode() = runBlocking {
        // Test text processing in general mode
        val rawText = "hello how are you"
        val context = ""
        
        // Since API isn't configured, it should use fallback processing
        val result = ragProcessor.processText(rawText, context, RAGProcessor.TextMode.GENERAL)
        
        // Fallback should capitalize first letter
        assertEquals("Hello how are you", result)
    }
    
    @Test
    fun testProcessTextWithEmailMode() = runBlocking {
        // Test email mode processing
        val rawText = "thanks for your email"
        val context = "Dear John,"
        
        val result = ragProcessor.processText(rawText, context, RAGProcessor.TextMode.EMAIL)
        
        // Fallback email mode adds greeting
        assertTrue(result.contains("regards") || result.contains("Regards"))
    }
    
    @Test
    fun testProcessTextWithFormalMode() = runBlocking {
        // Test formal mode processing
        val rawText = "i want to apply for the position"
        val context = ""
        
        val result = ragProcessor.processText(rawText, context, RAGProcessor.TextMode.FORMAL)
        
        // Should capitalize and make more formal
        assertTrue(result.startsWith("I"))
        assertTrue(result.contains("position"))
    }
    
    @Test
    fun testProcessTextWithEmptyInput() = runBlocking {
        // Test with empty input
        val result = ragProcessor.processText("", "", RAGProcessor.TextMode.GENERAL)
        assertEquals("", result)
    }
    
    @Test
    fun testProcessTextWithSpecialCharacters() = runBlocking {
        // Test with special characters
        val rawText = "what's the weather like?"
        val result = ragProcessor.processText(rawText, "", RAGProcessor.TextMode.GENERAL)
        
        // Should preserve apostrophes and question marks
        assertTrue(result.contains("'"))
        assertTrue(result.contains("?"))
    }
    
    @Test
    fun testProcessTextWithIndianEnglishPhrases() = runBlocking {
        // Test common Indian English phrases
        val testCases = listOf(
            "i will do the needful" to "I will do the needful",
            "please revert back" to "Please revert back",
            "out of station" to "Out of station",
            "prepone the meeting" to "Prepone the meeting"
        )
        
        for ((input, expected) in testCases) {
            val result = ragProcessor.processText(input, "", RAGProcessor.TextMode.GENERAL)
            assertEquals(expected, result)
        }
    }
    
    @Test
    fun testProcessTextPreservesNumbers() = runBlocking {
        // Test that numbers are preserved
        val rawText = "meet me at 3 pm in room 205"
        val result = ragProcessor.processText(rawText, "", RAGProcessor.TextMode.GENERAL)
        
        assertTrue(result.contains("3"))
        assertTrue(result.contains("205"))
    }
    
    @Test
    fun testProcessTextWithMixedCase() = runBlocking {
        // Test mixed case handling
        val rawText = "hELLo WoRLD"
        val result = ragProcessor.processText(rawText, "", RAGProcessor.TextMode.GENERAL)
        
        // Should normalize to proper case
        assertEquals("HELLo WoRLD", result)
    }
}