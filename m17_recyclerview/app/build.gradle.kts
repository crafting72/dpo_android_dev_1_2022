plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("com.google.devtools.ksp")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.example.m17_recyclerview"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.m17_recyclerview"
        minSdk = 26
        targetSdk = 34
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
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures{
        viewBinding = true
    }

    //Safe Args
    apply(plugin = "androidx.navigation.safeargs")
    }

dependencies {

    //Default
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //ViewModel
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.fragment.ktx)

    //Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    //Retrofit
    implementation(libs.retrofit)
    implementation(libs.adapter.rxjava2)

    //Moshi
    implementation(libs.moshi)
    implementation(libs.converter.moshi)
    ksp(libs.moshi.kotlin.codegen)

    //Glide
    implementation(libs.glide)

    //RecyclerView
    implementation(libs.androidx.recyclerview)

    //AndroidX Navigation
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
}