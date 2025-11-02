# VoxType Testing Guide - Samsung Fold 7

## ✅ Current Status
- **Keyboard Installed:** Yes
- **Keyboard Enabled:** Yes  
- **Keyboard Selected:** Yes (com.voxtype.keyboard)
- **Groq API Integration:** Ready (needs API key)

## 🎯 Quick Test Steps

### 1. Open VoxType Settings
```bash
adb shell am start -n com.voxtype.keyboard/.MainActivity
```
Or manually: Open VoxType app from launcher

### 2. Configure Groq API
1. Get your Groq API key from: https://console.groq.com/keys
2. In VoxType app:
   - Enter your Groq API key
   - API URL: `https://api.groq.com/` (default)
   - Tap "Save Settings"

### 3. Test Keyboard Appearance
```bash
# Open messaging app
adb shell am start -a android.intent.action.SENDTO -d sms:

# Or open any app with text input
```
- Tap on any text field
- VoxType keyboard should appear with big voice button

### 4. Test Voice Input
1. **Tap once** on voice button = Single recording
2. **Hold** voice button = Continuous recording
3. Speak clearly in English (Indian accent supported)
4. Text will appear with Groq AI enhancement

### 5. Test Language Switching
- Tap the language button (globe icon)
- Available languages:
  - English (India) - Default
  - English (US)
  - English (UK)
  - Hindi

### 6. Test Quick Actions
- **Settings button** - Opens VoxType settings
- **Language button** - Switches language
- **Keyboard button** - Shows keyboard picker
- **Clear button** - Clears text

## 🔧 Troubleshooting

### Keyboard Not Appearing?
```bash
# Reset keyboard
adb shell ime reset
adb shell ime enable com.voxtype.keyboard/.VoxTypeKeyboardService
adb shell ime set com.voxtype.keyboard/.VoxTypeKeyboardService
```

### Check Keyboard Status
```bash
# List enabled keyboards
adb shell ime list -s

# Check if VoxType is selected
adb shell settings get secure default_input_method
```

### View Logs
```bash
# Check for errors
adb logcat | grep -i voxtype

# Clear and monitor
adb logcat -c
# Try using keyboard
adb logcat | grep -E "voxtype|groq"
```

## 📝 Test Phrases for Indian English

Try these phrases to test recognition:
1. "I will do the needful"
2. "Please revert back to me"
3. "I am out of station"
4. "Can we prepone the meeting"
5. "What is your good name"

## 🚀 Groq AI Models

VoxType uses **llama3-8b-8192** for fast response times.

### Text Enhancement Modes:
- **General** - Standard text improvement
- **Email** - Professional email formatting
- **Chat** - Casual messaging style
- **Formal** - Business/formal writing

## 📱 Manual Testing on Device

1. Open any messaging app
2. Tap on text field - VoxType should appear
3. Tap voice button
4. Say: "Hello this is a test message"
5. Groq will enhance and insert the text
6. Try different modes for different styles

## ⚡ Quick Commands

```bash
# Install latest build
./gradlew assembleDebug && adb install -r app/build/outputs/apk/debug/app-debug.apk

# Launch settings
adb shell am start -n com.voxtype.keyboard/.MainActivity

# Test keyboard
adb shell am start -a android.intent.action.SENDTO -d sms:

# Check if keyboard service is running
adb shell dumpsys activity services | grep voxtype
```

## 🔑 Important Notes

1. **Microphone Permission:** Must be granted for voice input
2. **Internet Required:** For speech recognition and Groq AI
3. **Groq API Key:** Get free key at https://console.groq.com/keys
4. **Default Language:** Set to Indian English (en_IN)

## ✨ What's Working

- ✅ Keyboard installation and enablement
- ✅ Basic text input
- ✅ Groq API integration (replaces RAG)
- ✅ Indian English support
- ✅ Multiple language support
- ✅ Quick action buttons
- ✅ Voice button UI

## 🔄 Next Steps

1. Add your Groq API key in settings
2. Test voice recognition with Indian accent
3. Try different text enhancement modes
4. Test on folded and unfolded states