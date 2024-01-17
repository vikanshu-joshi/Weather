plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.vikanshu.core_ui"
    compileSdk = BuildConfig.compileSdk

    defaultConfig {
        minSdk = BuildConfig.minSdk

        testInstrumentationRunner = BuildConfig.testInstrumentationRunner
    }

    compileOptions {
        sourceCompatibility = BuildConfig.sourceCompatibility
        targetCompatibility = BuildConfig.targetCompatibility
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = BuildConfig.kotlinCompilerExtensionVersion
    }
    kotlin {
        jvmToolchain {
            languageVersion.set(JavaLanguageVersion.of(BuildConfig.jvmTarget))
        }
    }
    kotlinOptions {
        jvmTarget = BuildConfig.jvmTarget
    }
}

kapt {
    correctErrorTypes = true
}

dependencies {

    implementation(AndroidX.coreKtx)

    implementation(Hilt.hilt)
    kapt(Hilt.hiltAndroidCompiler)
    kapt(Hilt.hiltCompiler)
    implementation(AndroidX.workManagerKtx)

    implementation(platform(Compose.composeBom))
    implementation(Compose.composeUi)
    implementation(Compose.composeGraphics)
    implementation(Compose.composeUiTooling)
    implementation(Compose.composeToolingPreview)
    implementation(Compose.composeMaterial3)
    implementation(Compose.composeMaterial3WindowSize)
    implementation(Compose.composeRuntimeLivedata)
    implementation(Compose.viewModelCompose)
}