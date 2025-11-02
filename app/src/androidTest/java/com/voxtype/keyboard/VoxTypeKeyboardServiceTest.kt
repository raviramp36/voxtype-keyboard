package com.voxtype.keyboard

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiSelector
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Until
import android.content.Intent
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class VoxTypeKeyboardServiceTest {
    
    private lateinit var context: Context
    private lateinit var device: UiDevice
    
    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
    }
    
    @Test
    fun testKeyboardIsEnabled() {
        // Test that VoxType keyboard is enabled in the system
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val enabledMethods = imm.enabledInputMethodList
        
        val isVoxTypeEnabled = enabledMethods.any { 
            it.packageName == context.packageName 
        }
        
        assertTrue("VoxType keyboard should be enabled", isVoxTypeEnabled)
    }
    
    @Test
    fun testKeyboardAppearsWhenTextFieldTapped() {
        // Navigate to a text field
        device.pressHome()
        
        // Open a messaging app or browser with text field
        val browserPackage = "com.android.chrome"
        val intent = context.packageManager.getLaunchIntentForPackage(browserPackage)
        if (intent != null) {
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            context.startActivity(intent)
            device.wait(Until.hasObject(By.pkg(browserPackage).depth(0)), 5000)
        }
        
        // Click on URL bar or any text field
        val textField = device.findObject(UiSelector().className("android.widget.EditText"))
        if (textField.exists()) {
            textField.click()
            Thread.sleep(1000)
            
            // Check if keyboard is shown
            val keyboardView = device.findObject(
                UiSelector().packageName(context.packageName)
            )
            assertTrue("VoxType keyboard should appear", keyboardView.exists())
        }
    }
    
    @Test
    fun testVoiceButtonExists() {
        // Open keyboard
        openKeyboard()
        
        // Check if voice button exists
        val voiceButton = device.findObject(
            UiSelector()
                .resourceId("${context.packageName}:id/voice_button")
        )
        
        assertTrue("Voice button should exist", voiceButton.exists())
    }
    
    @Test
    fun testQuickActionsExist() {
        // Open keyboard
        openKeyboard()
        
        // Check quick action buttons
        val settingsButton = device.findObject(
            UiSelector()
                .resourceId("${context.packageName}:id/settings_button")
        )
        val languageButton = device.findObject(
            UiSelector()
                .resourceId("${context.packageName}:id/language_button")
        )
        val keyboardButton = device.findObject(
            UiSelector()
                .resourceId("${context.packageName}:id/keyboard_button")
        )
        val clearButton = device.findObject(
            UiSelector()
                .resourceId("${context.packageName}:id/clear_button")
        )
        
        assertTrue("Settings button should exist", settingsButton.exists())
        assertTrue("Language button should exist", languageButton.exists())
        assertTrue("Keyboard switch button should exist", keyboardButton.exists())
        assertTrue("Clear button should exist", clearButton.exists())
    }
    
    @Test
    fun testLanguageSupport() {
        // Test that Indian English is available
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val voxtypeMethod = imm.enabledInputMethodList.find { 
            it.packageName == context.packageName 
        }
        
        assertNotNull("VoxType method should be found", voxtypeMethod)
        
        voxtypeMethod?.let { method ->
            val subtypes = imm.getEnabledInputMethodSubtypeList(method, true)
            val hasIndianEnglish = subtypes.any { 
                it.locale == "en_IN" 
            }
            assertTrue("Indian English should be supported", hasIndianEnglish)
        }
    }
    
    @Test
    fun testClearButtonFunctionality() {
        // Open keyboard and test clear button
        openKeyboard()
        
        // Type some text first
        val editText = device.findObject(UiSelector().className("android.widget.EditText"))
        if (editText.exists()) {
            editText.setText("Test text")
            
            // Click clear button
            val clearButton = device.findObject(
                UiSelector()
                    .resourceId("${context.packageName}:id/clear_button")
            )
            if (clearButton.exists()) {
                clearButton.click()
                Thread.sleep(500)
                
                // Check if text was cleared
                val currentText = editText.text
                assertTrue("Text should be cleared", currentText.isEmpty())
            }
        }
    }
    
    @Test
    fun testKeyboardSwitchButton() {
        openKeyboard()
        
        // Click keyboard switch button
        val keyboardButton = device.findObject(
            UiSelector()
                .resourceId("${context.packageName}:id/keyboard_button")
        )
        
        if (keyboardButton.exists()) {
            keyboardButton.click()
            Thread.sleep(1000)
            
            // Check if input method picker appears
            val picker = device.findObject(
                UiSelector().textContains("Choose input method")
            )
            assertTrue("Input method picker should appear", picker.exists())
        }
    }
    
    private fun openKeyboard() {
        // Helper method to open keyboard
        device.pressHome()
        Thread.sleep(500)
        
        // Open any app with text field (e.g., Messages)
        val messagingPackage = "com.google.android.apps.messaging"
        val intent = context.packageManager.getLaunchIntentForPackage(messagingPackage)
        if (intent != null) {
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            context.startActivity(intent)
            device.wait(Until.hasObject(By.pkg(messagingPackage).depth(0)), 5000)
        }
        
        // Click on a text field to open keyboard
        val textField = device.findObject(UiSelector().className("android.widget.EditText"))
        if (textField.exists()) {
            textField.click()
            Thread.sleep(1000)
        }
    }
}