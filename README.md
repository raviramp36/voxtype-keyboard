# 🎤 VoxType Android Keyboard

AI-powered voice keyboard for Android with RAG integration, optimized for Samsung Fold devices.

## ✨ Features

### Core Features
- **Big Voice Button** - One-tap voice recording
- **Real-time Transcription** - See text as you speak
- **RAG AI Processing** - Enhanced text with your custom AI
- **Hold-to-Talk** - Long press for continuous recording
- **Smart Processing** - Removes filler words, adds punctuation

### Samsung Fold Optimized
- **Cover Display** - Simple UI for folded mode
- **Main Display** - Full features when unfolded
- **Adaptive Layout** - Responds to fold state

## 📱 Installation

### Requirements
- Android 8.0 (API 26) or higher
- Samsung Fold 7 or any Android device
- RAG API endpoint (optional)

### Build & Install

1. **Clone the repository:**
```bash
git clone https://github.com/raviramp36/voxtype.git
cd voxtype/android-app
```

2. **Open in Android Studio:**
- File → Open → Select `android-app` folder
- Let Gradle sync

3. **Configure RAG API (Optional):**
- Open `MainActivity.kt`
- Enter your RAG API URL and Key in settings

4. **Build APK:**
```bash
./gradlew assembleDebug
```
Or in Android Studio: Build → Build APK

5. **Install on Device:**
```bash
adb install app/build/outputs/apk/debug/app-debug.apk
```

## 🚀 Setup

### Enable VoxType Keyboard

1. **Open VoxType app**
2. **Tap "Enable Keyboard"** → Opens Android settings
3. **Enable "VoxType Voice Keyboard"**
4. **Grant Microphone Permission**

### Set as Default Keyboard

1. **In VoxType app, tap "Select Keyboard"**
2. **Choose "VoxType Voice Keyboard"**
3. **You're ready!**

### Configure RAG API

1. **Open VoxType app**
2. **Enter your RAG API details:**
   - API Key: Your RAG authentication key
   - API URL: Your RAG endpoint URL
3. **Tap "Save Settings"**

## 🎙 Usage

### Basic Voice Input
1. **Open any app** (Messages, WhatsApp, Email, etc.)
2. **Tap any text field**
3. **VoxType keyboard appears**
4. **Tap the big microphone button**
5. **Speak naturally**
6. **Text appears enhanced by AI**

### Recording Modes
- **Tap once** - Start/stop recording
- **Hold button** - Record while pressed
- **Double tap** - Continuous recording

### Quick Actions
- **⚙️ Settings** - Configure API
- **🌐 Language** - Switch languages
- **⌨️ Keyboard** - Switch to another keyboard
- **✖️ Clear** - Clear current text

## 🔧 RAG API Integration

### Expected API Format

Your RAG endpoint should accept POST requests:

```json
// Request
{
  "text": "raw transcribed text",
  "context": "previous text for context",
  "language": "en",
  "mode": "general",
  "features": ["remove_fillers", "add_punctuation", "fix_grammar"]
}

// Response
{
  "processedText": "Enhanced text output",
  "confidence": 0.95
}
```

### API Headers
```
Authorization: Bearer YOUR_API_KEY
Content-Type: application/json
```

## 🛠 Development

### Project Structure
```
app/
├── src/main/java/com/voxtype/keyboard/
│   ├── VoxTypeKeyboardService.kt  # Main keyboard service
│   ├── RAGProcessor.kt            # RAG API integration
│   └── MainActivity.kt            # Settings screen
├── src/main/res/
│   ├── layout/                    # UI layouts
│   ├── values/                    # Strings, colors
│   └── xml/                       # Keyboard config
```

### Key Components
- **VoxTypeKeyboardService** - Input method implementation
- **RAGProcessor** - Handles AI text enhancement
- **SpeechRecognizer** - Android's built-in speech-to-text

### Customization
- Modify `RAGProcessor.kt` for different AI endpoints
- Update `keyboard_layout.xml` for UI changes
- Edit `colors.xml` for theme customization

## 📝 Environment Variables

Create `.env` file in app folder:
```
RAG_API_KEY=your_api_key_here
RAG_API_URL=https://your-rag-endpoint.com/
DEFAULT_LANGUAGE=en-US
```

## 🐛 Troubleshooting

### Keyboard not appearing
- Ensure VoxType is enabled in Android settings
- Check if it's selected as current input method

### No voice recognition
- Check microphone permission
- Ensure internet connection
- Try clearing app cache

### RAG not working
- Verify API key and URL
- Check network connection
- Look at logs: `adb logcat | grep VoxType`

## 📱 Testing on Samsung Fold

### Fold-Specific Features
- **Cover screen**: Simplified UI automatically
- **Main screen**: Full feature set
- **Transition**: Seamless switching between screens

### Test Scenarios
1. Voice input on cover display
2. Voice input on main display
3. Switching between folded/unfolded
4. Multi-window mode

## 🚀 Deployment

### Generate Signed APK
1. Build → Generate Signed Bundle/APK
2. Choose APK
3. Create or select keystore
4. Build release APK

### Distribute
- Direct APK installation
- Google Play Store (requires developer account)
- Samsung Galaxy Store
- Internal testing via Firebase App Distribution

## 📄 License

MIT License - Feel free to use and modify!

---

**Built with ❤️ for Samsung Fold and Android devices**

Need help? Check the [main project README](../README.md) or open an issue!