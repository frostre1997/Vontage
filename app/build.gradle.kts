plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.vontage.app"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.vontage.app"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "0.1-beta"
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.15"
    }
}

dependencies {
    implementation(platform("androidx.compose:compose-bom:2025.02.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.activity:activity-compose:1.9.3")

    implementation("androidx.media3:media3-exoplayer:1.5.0")
    implementation("androidx.media3:media3-ui-compose:1.5.0")
    implementation("androidx.media3:media3-transformer:1.5.0")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.7")

    implementation("androidx.datastore:datastore-preferences:1.1.2")
    implementation("com.google.accompanist:accompanist-permissions:0.36.0")
}
