# Instagram Saver — final build

This is a build-ready Android starter for a FastDL-style user experience:
paste an Instagram URL, validate it, and continue to permitted/public media handling.

Important:
- No Instagram login or password is collected.
- No private-account bypass or access-control bypass is included.
- An actual arbitrary Instagram media resolver requires an authorized API/backend or a permitted direct media URL. This project intentionally does not pretend to have such an API key.
- The project is configured for AndroidX, Kotlin 2.0.21, Compose compiler plugin, Java 17, and Gradle 8+.

## GitHub Actions
Use JDK 17 and run:
`./gradlew assembleDebug --no-daemon`

APK:
`app/build/outputs/apk/debug/app-debug.apk`
