package com.voxtype.keyboard

import android.Manifest
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.GrantPermissionRule
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    
    @get:Rule
    val permissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.RECORD_AUDIO
    )
    
    @Test
    fun testMainActivityLaunches() {
        ActivityScenario.launch(MainActivity::class.java).use { scenario ->
            // Check if main views are displayed
            onView(withId(R.id.api_key_input))
                .check(matches(isDisplayed()))
            
            onView(withId(R.id.api_url_input))
                .check(matches(isDisplayed()))
            
            onView(withId(R.id.save_button))
                .check(matches(isDisplayed()))
            
            onView(withId(R.id.status_text))
                .check(matches(isDisplayed()))
        }
    }
    
    @Test
    fun testSaveSettingsWithEmptyApiKey() {
        ActivityScenario.launch(MainActivity::class.java).use { scenario ->
            // Clear API key field
            onView(withId(R.id.api_key_input))
                .perform(clearText())
            
            // Try to save
            onView(withId(R.id.save_button))
                .perform(click())
            
            // Should show toast message (can't directly test toast, but button should still be enabled)
            onView(withId(R.id.save_button))
                .check(matches(isEnabled()))
        }
    }
    
    @Test
    fun testSaveSettingsWithValidData() {
        ActivityScenario.launch(MainActivity::class.java).use { scenario ->
            // Enter API key
            onView(withId(R.id.api_key_input))
                .perform(clearText(), typeText("test-api-key-123"))
            
            // Enter API URL
            onView(withId(R.id.api_url_input))
                .perform(clearText(), typeText("https://api.test.com/"))
            
            // Close keyboard
            onView(withId(R.id.api_url_input))
                .perform(closeSoftKeyboard())
            
            // Save settings
            onView(withId(R.id.save_button))
                .perform(click())
            
            // Button should remain enabled
            onView(withId(R.id.save_button))
                .check(matches(isEnabled()))
        }
    }
    
    @Test
    fun testKeyboardStatusDisplay() {
        ActivityScenario.launch(MainActivity::class.java).use { scenario ->
            // Status text should be visible
            onView(withId(R.id.status_text))
                .check(matches(isDisplayed()))
            
            // Should show some status (enabled, disabled, or selected)
            onView(withId(R.id.status_text))
                .check(matches(withText(containsString("VoxType"))))
        }
    }
    
    @Test
    fun testEnableKeyboardButton() {
        ActivityScenario.launch(MainActivity::class.java).use { scenario ->
            onView(withId(R.id.enable_keyboard_button))
                .check(matches(isDisplayed()))
            
            // If keyboard is not enabled, button should be enabled
            // If keyboard is already enabled, button should be disabled
            // This depends on the current state
        }
    }
    
    @Test
    fun testSelectKeyboardButton() {
        ActivityScenario.launch(MainActivity::class.java).use { scenario ->
            onView(withId(R.id.select_keyboard_button))
                .check(matches(isDisplayed()))
            
            // Button state depends on whether keyboard is enabled
        }
    }
    
    @Test
    fun testDefaultApiUrl() {
        ActivityScenario.launch(MainActivity::class.java).use { scenario ->
            // Check if default URL is set
            onView(withId(R.id.api_url_input))
                .check(matches(withText("https://api.example.com/")))
        }
    }
    
    @Test
    fun testApiKeyInputType() {
        ActivityScenario.launch(MainActivity::class.java).use { scenario ->
            // API key input should be password type (dots)
            onView(withId(R.id.api_key_input))
                .perform(typeText("test"))
            
            // Check if it's a TextInputEditText (Material Design)
            onView(withId(R.id.api_key_input))
                .check(matches(isAssignableFrom(com.google.android.material.textfield.TextInputEditText::class.java)))
        }
    }
}