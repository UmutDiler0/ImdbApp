# ImdbApp

ImdbApp is an Android application designed to showcase movie details using the IMDB API. The app demonstrates modern Android development practices, including Kotlin DSL, Jetpack Navigation, and the integration of an API key for secure network communication.


https://github.com/user-attachments/assets/0de0e05b-e978-49d1-ae66-d74b1a866b89


## Features
- **Browse Movies**: View a list of movies fetched from the IMDB API.
- **Movie Details**: Get detailed information about a selected movie.
- **Jetpack Navigation**: Smooth navigation between screens.
- **Secure API Key Integration**: Ensures the API key is not exposed in the source code.

## Technologies Used
- **Kotlin**: Primary programming language.
- **Jetpack Navigation**: For navigating between screens.
- **Kotlin DSL**: For build script configuration.
- **Retrofit**: For HTTP networking.
- **Coroutines**: For asynchronous programming.
- **MVVM**: For App architecture.
- **StateFlow**: For observing data changes.
- **Hilt**: For Dependency Injection.
- **Glide**: For download images from internet.

## Setup Instructions
### Prerequisites
- Android Studio Flamingo or higher
- Minimum SDK: 24
- Target SDK: 35

### Steps
1. Clone the repository:
   ```bash
   git clone https://github.com/UmutDiler0/ImdbApp.git
   ```
2. Open the project in Android Studio.
3. Add your IMDB API key to the `local.properties` file in the project root:
   ```properties
   token=your_api_key_here
   ```
4. Sync the project with Gradle files.
5. Run the app on an emulator or physical device.

## Project Structure
```
ImdbApp
├── app
│   ├── src
│   │   ├── main
│   │   │   ├── java/com/example/imdbapp
│   │   │   ├── res
│   │   │   └── AndroidManifest.xml
├── build.gradle.kts
├── local.properties
└── settings.gradle.kts
```

## API Integration
This app uses Retrofit for API communication. The API key is securely managed using Kotlin DSL and retrieved dynamically from the `local.properties` file. This ensures that sensitive information is not exposed in version control.

## Contributing
Contributions are welcome! Please follow these steps:
1. Fork the repository.
2. Create a new branch for your feature or bug fix.
3. Commit your changes and push them to your fork.
4. Submit a pull request.

## License
This project is licensed under the MIT License. See the LICENSE file for details.

## Contact
For any inquiries or issues, please contact:
- **Name**: Umut Diler
- **Email**: diler.2029@gmail.com
- **LinkedIn**: [Umut Diler](https://www.linkedin.com/in/umut-diler-3b04aa2b2/)

---
**Happy Coding!** 🎉
