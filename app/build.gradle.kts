plugins {
    alias(libs.plugins.android.application)
    kotlin("plugin.serialization") version "1.9.23"
}

android {
    namespace = "com.example.sapiens"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "com.example.sapiens"
        minSdk = 28
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"))
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.core.ktx)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    //serializacion
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")
    implementation("com.squareup.retrofit2:converter-kotlinx-serialization:2.11.0")
    //retrofit
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    //http
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    //Glide para imagenes
    implementation("com.github.bumptech.glide:glide:4.16.0")
    //coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.1")
}