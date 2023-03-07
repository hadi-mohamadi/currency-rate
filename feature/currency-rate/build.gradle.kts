plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.currency_rate"
    compileSdk = 33
    defaultConfig {
        minSdk = 23
        targetSdk = 33

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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.1.1"
    }
}

dependencies {

    implementation(project(Modules.coreNetwork))

    implementation(AndroidX.coreKtx)
    implementation(AndroidX.lifecycleRuntime)
    implementation(AndroidX.lifecycleViewModel)
    implementation(AndroidX.composeUi)
    implementation(AndroidX.composeUiToolingPreview)
    implementation(AndroidX.composeMaterial)
    testImplementation(JUnit.junit)
    androidTestImplementation(AndroidX.testJUnit)
    androidTestImplementation(AndroidX.testEspresso)
    androidTestImplementation(AndroidX.composeUiTestJUnit)
    debugImplementation(AndroidX.composeUiTooling)
    debugImplementation(AndroidX.composeTestManifest)

    implementation(Coroutines.coroutines)

    implementation(Hilt.daggerHilt)
    kapt(Hilt.hiltCompiler)
    implementation(Hilt.hiltNavigation)

    implementation(Retrofit.retrofit)
    implementation(Retrofit.gson)
    implementation(Retrofit.loggingInterceptor)

    testImplementation(Mockk.mockk)
    androidTestImplementation(Mockk.androidMockk)
}