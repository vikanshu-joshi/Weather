import org.gradle.api.JavaVersion

object BuildConfig {
    const val compileSdk = 34
    const val targetSdk = 34
    const val applicationId = "com.vikanshu.weather"
    const val minSdk = 24
    const val versionCode = 1
    const val versionName = "1.0"
    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    val sourceCompatibility = JavaVersion.VERSION_18
    val targetCompatibility = JavaVersion.VERSION_18
    const val jvmTarget = "18"
    const val kotlinCompilerExtensionVersion = "1.5.1"
}