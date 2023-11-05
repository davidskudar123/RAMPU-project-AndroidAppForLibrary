plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.scriptify.hr"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.scriptify.hr"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildToolsVersion = "33.0.1"

}

dependencies {
    implementation ("com.android.volley:volley:1.2.1")
    implementation ("com.squareup.okhttp3:okhttp:4.9.1")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.4.0")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2")

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    implementation ("com.google.android.material:material:<1.5.0>")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}