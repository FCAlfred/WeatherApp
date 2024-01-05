plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("androidx.navigation.safeargs.kotlin")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}

android {
    namespace = "com.freddev.weatherapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.freddev.weatherapp"
        minSdk = 24
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    //ViewModel - LiveData
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")

    //Navigation
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.6")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.6")

    //ROOM
    implementation("androidx.room:room-runtime:2.6.1")

    //Gson
    implementation("com.google.code.gson:gson:2.9.0")

    //Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2")

    //Kodein
    implementation("org.kodein.di:kodein-di-generic-jvm:5.2.0")
    implementation("org.kodein.di:kodein-di-framework-android-x:5.2.0")

    // Better dateTime-time support even on older Android versions
    implementation("com.jakewharton.threetenabp:threetenabp:1.1.0")

    // GLide
    implementation("com.github.bumptech.glide:glide:4.13.0")

    // Groupie RecycLerView
    implementation("com.github.lisawray.groupie:groupie:2.10.1")
    implementation ("com.github.lisawray.groupie:groupie:2.10.1")
    implementation ("com.github.lisawray.groupie:groupie-viewbinding:2.10.1")

    // Preference
    implementation("androidx.preference:preference-ktx:1.2.1")

    // Location
    implementation("com.google.android.gms:play-services-location:21.0.1")

    // New Material Design
    implementation("com.google.android.material:material:1.11.0")

    //Maps
    implementation("com.google.android.gms:play-services-maps:18.2.0")
    implementation ("com.google.android.gms:play-services-location:21.0.1")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}