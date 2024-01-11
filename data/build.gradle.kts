plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.vikanshu.data"
    compileSdk = BuildConfig.compileSdk

    defaultConfig {
        minSdk = BuildConfig.minSdk

        testInstrumentationRunner = BuildConfig.testInstrumentationRunner
    }

    compileOptions {
        sourceCompatibility = BuildConfig.sourceCompatibility
        targetCompatibility = BuildConfig.targetCompatibility
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

dependencies {

    implementation(AndroidX.coreKtx)

    // api calls - retrofit
    implementation(Retrofit.retrofit)
    implementation(Retrofit.retrofitConverter)
    implementation(Retrofit.okhttp)
    implementation(Retrofit.logginInterceptor)

    // dependency injection - Koin
    implementation(Koin.koin)
    implementation(Koin.koinAndroid)
    implementation(Koin.koinAndroidXCompose)

    // room
    implementation(Room.room)
    implementation(Room.roomKtx)
    kapt(Room.roomKapt)

    // gson
    implementation(Google.gson)

    // coroutines
    implementation(Kotlin.coroutines)
}