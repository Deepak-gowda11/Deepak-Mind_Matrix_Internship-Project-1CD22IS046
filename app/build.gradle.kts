plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.androidappdevelopmentusinggenai_madhu_margaagriculture"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.androidappdevelopmentusinggenai_madhu_margaagriculture"
        minSdk = 24
        targetSdk = 35
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.material)

    // Room Database
    implementation(libs.androidx.room.runtime)
    annotationProcessor(libs.androidx.room.compiler)

    // MPAndroidChart
    implementation(libs.mpandroidchart)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
}