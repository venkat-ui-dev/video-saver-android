# Video Saver Android

Builds an Android debug APK through GitHub Actions.

## Build online
1. Upload this project to the `video-saver-android` GitHub repository.
2. Open **Actions → Build Android APK → Run workflow**.
3. Wait for **Build debug APK** to finish.
4. Open the workflow run and download the **video-saver-debug-apk** artifact.

The project uses Java 17 and Kotlin JVM target 17, so Java/Kotlin target compatibility is aligned for Gradle 8.7.

The app does not bypass YouTube/Instagram access controls. Use official platform download/save features or direct media URLs you are authorized to save.
