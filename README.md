# StudyTrack 📚

A minimalist study tracking app to help you achieve your learning goals. Set your annual study target and track your progress with detailed session logging.

## Features ✨

- **Annual Goal Tracking** - Set your yearly study hour target (e.g., 100 hours)
- **Session Logging** - Record study sessions with date, duration, and subject
- **Progress Visualization** - Visual progress bar showing your achievement
- **Statistics Dashboard** - View total hours studied at a glance
- **Session History** - Browse all your past study sessions
- **Modern UI** - Clean, Material Design 3 interface with custom splash screen

## Screenshots 📱
<img width="610" height="350" alt="pitch_study_track" src="https://github.com/user-attachments/assets/c4d1a6ae-a73b-413f-8a83-3bb42f429f15" />



## Tech Stack 🛠️

- **Language:** Kotlin
- **UI Framework:** Jetpack Compose
- **Architecture:** MVVM (Model-View-ViewModel)
- **Database:** Room (SQLite)
- **Navigation:** Jetpack Navigation Compose
- **Async:** Kotlin Coroutines & Flow
- **Design:** Material Design 3

## Architecture 🏗️
```
com.jesscafezeiro.studytrack
├── data/
│   ├── StudySession.kt (Entity)
│   ├── StudySessionDao.kt (Data Access Object)
│   └── StudyTrackDatabase.kt (Room Database)
├── ui/
│   ├── SplashScreen.kt (Splash screen)
│   ├── MainScreen.kt (Home screen)
│   ├── RegistrationScreen.kt (Add session screen)
│   ├── MainScreenViewModel.kt (State management)
│   └── NavGraph.kt (Navigation configuration)
└── MainActivity.kt (Entry point)
```

## Installation 📲

### Requirements
- Android 7.0 (API 24) or higher
- ~10 MB storage space

### From Google Play
_Link coming soon_

### From Source
1. Clone the repository
```bash
git clone https://github.com/yourusername/studytrack.git
```

2. Open in Android Studio Arctic Fox or newer

3. Build and run on your device/emulator

## Usage 🎯

1. **Set Your Goal** - Define your annual study target (default: 100 hours)
2. **Log Sessions** - Tap the **+** button to record a study session
3. **Enter Details:**
   - Date (defaults to today)
   - Duration (hours and minutes)
   - Subject/Topic studied
4. **Track Progress** - Watch your progress bar grow as you study
5. **Review History** - Scroll through all your past sessions

## Roadmap 🗺️

- [ ] Weekly and daily statistics
- [ ] Custom goal configuration
- [ ] Date picker for sessions
- [ ] Edit/delete sessions
- [ ] Progress charts and graphs
- [ ] Data export (CSV/PDF)
- [ ] Study reminders/notifications
- [ ] Dark theme support
- [ ] Multi-language support

## Contributing 🤝

Contributions are welcome! This is a learning project, so feel free to:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'feat: Add AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License 📄

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Author 👨‍💻

**Jessica Cafezeiro**
- GitHub: [@jessicawolfie](https://github.com/jessicawolfie)
- Email: cafezeiro.dev@gmail.com.com
