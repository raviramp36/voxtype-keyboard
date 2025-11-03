# Contributing to VoxType

First off, thank you for considering contributing to VoxType! It's people like you that make VoxType such a great tool for the community.

## <¯ Code of Conduct

This project and everyone participating in it is governed by our Code of Conduct. By participating, you are expected to uphold this code:

- Use welcoming and inclusive language
- Be respectful of differing viewpoints and experiences
- Gracefully accept constructive criticism
- Focus on what is best for the community
- Show empathy towards other community members

## =€ Getting Started

### Prerequisites

Before you begin, ensure you have:
- Android Studio Hedgehog (2023.1.1) or newer
- JDK 17 or higher
- Android SDK with API level 24+
- Git installed on your machine
- A Groq API key for testing (get one at [console.groq.com](https://console.groq.com))

### Setting Up Your Development Environment

1. **Fork the repository**
   - Navigate to [VoxType on GitHub](https://github.com/raviramp36/voxtype-keyboard)
   - Click the "Fork" button in the top right

2. **Clone your fork**
   ```bash
   git clone https://github.com/YOUR_USERNAME/voxtype-keyboard.git
   cd voxtype-keyboard/android-app
   ```

3. **Add upstream remote**
   ```bash
   git remote add upstream https://github.com/raviramp36/voxtype-keyboard.git
   ```

4. **Create a branch**
   ```bash
   git checkout -b feature/your-feature-name
   ```

## =' How to Contribute

### Reporting Bugs

Before creating bug reports, please check existing issues to avoid duplicates. When creating a bug report, include:

- **Clear title and description**
- **Steps to reproduce**
- **Expected behavior**
- **Actual behavior**
- **Screenshots** (if applicable)
- **Device information**:
  - Android version
  - Device model
  - VoxType version
- **Logs** (use `adb logcat | grep VoxType`)

### Suggesting Enhancements

Enhancement suggestions are tracked as GitHub issues. When creating an enhancement suggestion, include:

- **Use case**: Why is this enhancement needed?
- **Proposed solution**: How should it work?
- **Alternatives considered**: What other solutions did you think about?
- **Additional context**: Mockups, examples, etc.

### Pull Request Process

1. **Before coding**:
   - Check if an issue exists for your change
   - If not, create one and wait for feedback
   - For major changes, discuss first in an issue

2. **While coding**:
   - Follow the [Kotlin style guide](https://kotlinlang.org/docs/coding-conventions.html)
   - Write meaningful commit messages
   - Add/update tests as needed
   - Update documentation

3. **Commit message format**:
   ```
   type(scope): subject
   
   body (optional)
   
   footer (optional)
   ```
   
   Types:
   - `feat`: New feature
   - `fix`: Bug fix
   - `docs`: Documentation changes
   - `style`: Code style changes
   - `refactor`: Code refactoring
   - `test`: Test additions/changes
   - `chore`: Maintenance tasks

   Example:
   ```
   feat(keyboard): add support for voice commands
   
   Implemented voice commands for common editing actions like
   delete word, new line, and undo. Commands are triggered by
   specific keywords during voice input.
   
   Closes #123
   ```

4. **Before submitting**:
   ```bash
   # Run tests
   ./gradlew test
   
   # Check code style
   ./gradlew ktlintCheck
   
   # Build the project
   ./gradlew assembleDebug
   ```

5. **Submit PR**:
   - Push your branch to your fork
   - Create a Pull Request from your fork to the main repository
   - Fill out the PR template completely
   - Link related issues

## =Ý Development Guidelines

### Code Style

- **Kotlin conventions**: Follow official Kotlin coding conventions
- **Naming**:
  - Classes: `PascalCase`
  - Functions/Variables: `camelCase`
  - Constants: `UPPER_SNAKE_CASE`
  - Packages: `lowercase`
- **Comments**:
  - Add KDoc for public APIs
  - Use `//` for inline comments
  - Explain "why" not "what"

### Architecture

VoxType follows MVVM (Model-View-ViewModel) architecture:

```
View (Activities/Fragments)
    “ observes
ViewModel
    “ uses
Repository
    “ accesses
DataSource (API/Database)
```

### Testing

- **Unit tests**: Required for business logic
- **Integration tests**: For API and database operations
- **UI tests**: For critical user flows

Test file structure:
```
app/src/test/        # Unit tests
app/src/androidTest/ # Instrumented tests
```

### Documentation

- Update README.md for user-facing changes
- Add KDoc comments for public APIs
- Update inline comments for complex logic
- Include examples in documentation

## < Translations

Help make VoxType available in more languages:

1. Create a new values folder: `values-{language_code}/`
2. Copy `strings.xml` from `values/`
3. Translate all string resources
4. Submit a PR with the title: `i18n: Add {language} translation`

## <¨ Design Guidelines

- Follow Material Design 3 principles
- Maintain dark theme consistency (#121212 background)
- Ensure accessibility (contrast ratios, touch targets)
- Test on different screen sizes

## =Ê Performance Considerations

- Minimize API calls
- Use coroutines for async operations
- Profile memory usage
- Optimize battery consumption
- Test on low-end devices

## = Where to Contribute

### Good First Issues

Look for issues labeled `good first issue` - these are great for newcomers.

### Priority Areas

- **Language Support**: Add support for more languages
- **Accessibility**: Improve keyboard for users with disabilities
- **Performance**: Optimize for lower-end devices
- **Testing**: Increase test coverage
- **Documentation**: Improve user and developer docs

### Feature Ideas

- Offline mode with local models
- Custom voice commands
- Theme customization
- Gesture typing
- Multi-device sync

## =¬ Communication

- **GitHub Issues**: For bugs and features
- **GitHub Discussions**: For questions and ideas
- **Pull Request comments**: For code review

##  Checklist

Before submitting a PR, ensure:

- [ ] Code compiles without warnings
- [ ] All tests pass
- [ ] Code follows style guidelines
- [ ] Documentation is updated
- [ ] Commit messages are clear
- [ ] PR description explains changes
- [ ] Related issues are linked
- [ ] Changes are tested on physical device

## =O Recognition

Contributors will be:
- Listed in the README
- Mentioned in release notes
- Given credit in commit messages

## =Ü License

By contributing, you agree that your contributions will be licensed under the MIT License.

## > Thank You!

Your contributions make VoxType better for everyone. Whether it's fixing a typo, adding a translation, or implementing a new feature, every contribution is valuable.

Happy coding! =€