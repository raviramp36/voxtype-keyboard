# 🎙️ VoxType - AI-Powered Voice Keyboard for Android

<div align="center">
  
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Android](https://img.shields.io/badge/Android-7.0%2B-green.svg)](https://www.android.com/)
[![Kotlin](https://img.shields.io/badge/Kotlin-1.9.0-purple.svg)](https://kotlinlang.org/)
[![API](https://img.shields.io/badge/API-24%2B-brightgreen.svg)](https://android-arsenal.com/api?level=24)
[![GitHub release](https://img.shields.io/github/release/raviramp36/voxtype-keyboard.svg)](https://github.com/raviramp36/voxtype-keyboard/releases)

**Transform your voice into perfectly formatted text with AI-powered grammar enhancement**

[Features](#features) • [Installation](#installation) • [Usage](#usage) • [Development](#development) • [Contributing](#contributing)

</div>

---

## 🚀 Overview

VoxType is an advanced Android keyboard that combines voice recognition with AI-powered text processing to help users communicate more effectively. It's specifically optimized for Indian English speakers, automatically correcting common grammatical patterns while maintaining natural, conversational tone.

### 🎯 Key Highlights

- **🗣️ Voice-First Design**: Large touch area for easy voice input
- **🧠 AI-Powered Enhancement**: Uses Groq's Whisper API for transcription and Mixtral for grammar correction
- **🇮🇳 Indian English Optimization**: Specifically trained to understand and correct Indian English patterns
- **🌙 Dark Mode Interface**: Clean, modern dark theme for comfortable use
- **📝 Smart Formatting**: Automatic punctuation, capitalization, and list detection
- **🔒 Privacy-Focused**: All processing happens securely, no data stored without permission

## ✨ Features

### Voice Recognition
- **Whisper Large v3 Turbo** integration for accurate transcription
- Support for multiple languages (English, Hindi, etc.)
- Context-aware transcription with previous text consideration
- Whisper mode for low-volume speech
- Hold-to-talk and tap-to-toggle modes

### Grammar Enhancement
Automatically fixes common Indian English patterns:
- ✅ "I am having doubt" → "I have a doubt"
- ✅ "Please revert back" → "Please reply"
- ✅ "Out of station" → "Out of town"
- ✅ "Passing out from college" → "Graduating from college"
- ✅ "Can you do the needful?" → "Can you take care of this?"
- ✅ Adds missing articles: "I am engineer" → "I am an engineer"
- ✅ Fixes redundancies: "return back" → "return"
- ✅ Corrects prepositions: "angry on" → "angry with"
- ✅ Fixes tenses: "I am thinking it is good" → "I think it's good"

### Smart Features
- **Context Detection**: Adapts formatting based on the app you're using (Email, WhatsApp, Slack, etc.)
- **List Formatting**: Automatically formats numbered lists when detected
- **Natural Time References**: Keeps "today", "tomorrow", "yesterday" as words
- **Multi-language Support**: Seamlessly handles code-switching between English and Hindi
- **Smart Punctuation**: Adds periods, commas, and question marks intelligently
- **Transcription History**: View and copy previous transcriptions

### User Interface
- **Glassmorphism Design**: Modern, translucent UI elements
- **Dark Theme**: Easy on the eyes with #121212 background
- **Minimal Controls**: Settings, Language, Space, Backspace, Clear
- **Visual Feedback**: 
  - Idle: Dark gray (#2A2A2A)
  - Recording: Dark red (#B71C1C)
  - Processing: Dark orange (#E65100)
- **No Circular Elements**: Clean rectangular design with rounded corners

## 📱 Installation

### Prerequisites
- Android 7.0 (API 24) or higher
- Groq API key (free tier available at [console.groq.com](https://console.groq.com))
- 50MB free storage space

### Quick Install

1. **Download the latest APK** from [Releases](https://github.com/raviramp36/voxtype-keyboard/releases)

2. **Enable installation from unknown sources**:
   - Settings → Security → Unknown sources (Enable)
   - Or Settings → Apps → Special access → Install unknown apps

3. **Install the APK** by opening the downloaded file

4. **Enable VoxType Keyboard**:
   ```
   Settings → System → Languages & Input → Virtual Keyboard 
   → Manage keyboards → Enable "VoxType Voice Keyboard"
   ```

5. **Set as default** (optional):
   - In any text field, tap the keyboard icon
   - Select VoxType from the list

### First-Time Setup

1. **Open VoxType app** from your app drawer

2. **Configure API Key**:
   - Tap Settings tab
   - Enter your Groq API key
   - Tap "Save Settings"

3. **Grant permissions**:
   - Microphone (required for voice input)
   - Optional: Storage (for transcription history)

4. **Test the keyboard**:
   - Open any messaging app
   - Tap a text field
   - Try voice input!

## 🎮 Usage

### Basic Voice Input

1. **Tap the dark rectangle** to start recording
2. **Speak naturally** - the rectangle turns red while recording
3. **Tap again to stop** or wait for auto-stop after pause
4. **Wait for processing** - orange color indicates AI enhancement
5. **Text appears** automatically with perfect grammar!

### Recording Modes

- **Single Tap**: Start/stop recording manually
- **Hold to Talk**: Press and hold for continuous recording
- **Auto-stop**: Automatically stops after 2 seconds of silence

### Keyboard Shortcuts

| Button | Function | Description |
|--------|----------|-------------|
| ⚙️ | Settings | Configure API key and preferences |
| 🌐 | Language | Cycle through keyboard languages |
| ␣ | Space | Insert a space character |
| ⌫ | Backspace | Delete previous character/word |
| ❌ | Clear | Clear all text in current field |

### Pro Tips

- **Speak naturally** - The AI understands context and intent
- **For lists**, say: "first... second... third..."
- **Punctuation** is added automatically based on tone
- **Explicit punctuation**: Say "comma", "period", "question mark"
- **Code-switching**: Mix English and Hindi naturally
- **Email mode**: Automatically formal when in email apps
- **Chat mode**: Casual tone in messaging apps

## 🛠️ Development

### Tech Stack

- **Language**: Kotlin 1.9.0
- **Min SDK**: 24 (Android 7.0)
- **Target SDK**: 34 (Android 14)
- **Build Tool**: Gradle 8.0
- **Architecture**: MVVM with Repository pattern

### Key Libraries

```gradle
dependencies {
    // Core Android
    implementation 'androidx.core:core-ktx:1.12.0'
    implementation 'androidx.appcompat:appcompat:1.6.1'
    implementation 'com.google.android.material:material:1.11.0'
    
    // Database
    implementation 'androidx.room:room-runtime:2.6.1'
    implementation 'androidx.room:room-ktx:2.6.1'
    
    // Networking
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation 'com.squareup.okhttp3:okhttp:4.12.0'
    
    // Coroutines
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3'
}
```

### Building from Source

1. **Clone the repository**:
```bash
git clone https://github.com/raviramp36/voxtype-keyboard.git
cd voxtype-keyboard/android-app
```

2. **Open in Android Studio**:
   - Use Android Studio Hedgehog (2023.1.1) or newer
   - Wait for Gradle sync to complete

3. **Configure API key** (for development):
```bash
# Create local.properties in project root
echo "groq.api.key=your_api_key_here" > local.properties
```

4. **Build Debug APK**:
```bash
./gradlew assembleDebug
```

5. **Install on device**:
```bash
adb install app/build/outputs/apk/debug/app-debug.apk
```

### Project Structure

```
app/src/main/
├── java/com/voxtype/keyboard/
│   ├── WhisperKeyboardService.kt    # Main keyboard service
│   ├── GroqProcessor.kt             # AI processing & API calls
│   ├── MainActivity.kt              # App settings & configuration
│   ├── database/
│   │   ├── VoxTypeDatabase.kt      # Room database setup
│   │   ├── entities/                # Database entities
│   │   └── dao/                     # Data access objects
│   ├── fragments/
│   │   ├── HomeFragment.kt         # Transcription history
│   │   ├── SettingsFragment.kt     # API configuration
│   │   ├── DictionaryFragment.kt   # Custom words
│   │   └── SnippetsFragment.kt     # Text shortcuts
│   ├── adapters/
│   │   └── TranscriptionAdapter.kt # History list adapter
│   └── utils/
│       └── AdvancedSettingsManager.kt # Feature flags
├── res/
│   ├── layout/
│   │   ├── keyboard_layout_simple.xml # Keyboard UI
│   │   ├── activity_main.xml         # Main app UI
│   │   └── fragment_*.xml            # Fragment layouts
│   ├── drawable/
│   │   ├── glass_background.xml      # Glassmorphism effects
│   │   ├── glass_button_*.xml        # Button states
│   │   └── wave_animation.xml        # Background animation
│   ├── values/
│   │   ├── colors.xml                # Color definitions
│   │   ├── strings.xml               # Text resources
│   │   └── themes.xml                # App themes
│   └── xml/
│       └── method.xml                # Keyboard configuration
└── AndroidManifest.xml               # App manifest
```

### API Integration

VoxType uses Groq's API for voice and text processing:

```kotlin
// Transcription endpoint
POST https://api.groq.com/openai/v1/audio/transcriptions
Model: whisper-large-v3-turbo

// Text enhancement endpoint  
POST https://api.groq.com/openai/v1/chat/completions
Model: mixtral-8x7b-32768
```

### Database Schema

```sql
-- Transcription history
CREATE TABLE transcriptions (
    id INTEGER PRIMARY KEY,
    timestamp DATETIME,
    raw_text TEXT,
    enhanced_text TEXT,
    app_package TEXT,
    word_count INTEGER,
    duration REAL
);

-- User dictionary
CREATE TABLE dictionary_words (
    id INTEGER PRIMARY KEY,
    word TEXT UNIQUE,
    replacement TEXT,
    language TEXT,
    frequency INTEGER
);

-- Text snippets
CREATE TABLE snippets (
    id INTEGER PRIMARY KEY,
    trigger TEXT UNIQUE,
    expansion TEXT,
    category TEXT
);
```

## 🤝 Contributing

We welcome contributions! Please see our [Contributing Guidelines](CONTRIBUTING.md) for details.

### How to Contribute

1. **Fork** the repository
2. **Create** your feature branch:
```bash
git checkout -b feature/AmazingFeature
```
3. **Commit** your changes:
```bash
git commit -m 'Add some AmazingFeature'
```
4. **Push** to the branch:
```bash
git push origin feature/AmazingFeature
```
5. **Open** a Pull Request

### Development Guidelines

- Follow Kotlin coding conventions
- Add KDoc comments for public APIs
- Write unit tests for new features
- Update README for significant changes
- Use meaningful commit messages

### Areas for Contribution

- 🌐 Additional language support
- 🎨 Custom themes and UI improvements
- 🔧 Performance optimizations
- 📝 Documentation improvements
- 🐛 Bug fixes
- ✨ New features

## 📄 License

This project is licensed under the MIT License:

```
MIT License

Copyright (c) 2024 Ravi Kumar

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```

## 🙏 Acknowledgments

- **[Groq](https://groq.com)** - Lightning-fast AI inference
- **[OpenAI Whisper](https://openai.com/research/whisper)** - State-of-the-art speech recognition
- **[Android Open Source Project](https://source.android.com)** - Input method framework
- **[Material Design](https://material.io)** - UI components and guidelines
- **All contributors** who help improve VoxType!

## 📞 Support

- **Bug Reports**: [GitHub Issues](https://github.com/raviramp36/voxtype-keyboard/issues)
- **Feature Requests**: [GitHub Discussions](https://github.com/raviramp36/voxtype-keyboard/discussions)
- **Security Issues**: Please email directly to maintainer
- **General Questions**: Use GitHub Discussions

## 🗺️ Roadmap

### Version 2.0 (Q1 2025)
- [ ] Offline mode with local Whisper models
- [ ] Voice commands for text editing
- [ ] Custom wake words
- [ ] Batch transcription processing

### Version 2.5 (Q2 2025)
- [ ] Multi-accent support (US, UK, Australian)
- [ ] Real-time translation
- [ ] Voice shortcuts and macros
- [ ] Cloud sync for settings

### Version 3.0 (Q3 2025)
- [ ] iOS version
- [ ] Desktop browser extension
- [ ] Team collaboration features
- [ ] Enterprise API integration

## 📊 Stats

- ⭐ Star us on GitHub
- 🍴 Fork for your own customization
- 👁️ Watch for updates
- 📥 Download from [Releases](https://github.com/raviramp36/voxtype-keyboard/releases)

---

<div align="center">

**Built with ❤️ in India**

VoxType is designed to make voice typing accessible and intelligent for everyone.

If you find VoxType helpful, please ⭐ **star this repository** to show your support!

[Report Bug](https://github.com/raviramp36/voxtype-keyboard/issues) • [Request Feature](https://github.com/raviramp36/voxtype-keyboard/discussions) • [Join Discussion](https://github.com/raviramp36/voxtype-keyboard/discussions)

</div>