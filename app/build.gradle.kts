plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.momocoffe.mx"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.momocoffe.mx"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        buildFeatures {
            buildConfig = true
        }
        defaultConfig {
            buildConfigField("String", "API_BASE_URL", "\"https://tasks-planner-api.herokuapp.com/\"")

        }

        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "API_BASE_URL", "\"https://tasks-planner-api.herokuapp.com/\"")

        }

    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "/META-INF/*.kotlin_module"
        }
    }
}


dependencies {
    implementation("com.github.MahboubehSeyedpour:jetpack-loading:1.1.0")
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.9.0")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.material:material:1.6.7")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.3")
    implementation("com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // Navigation Compose
    implementation("androidx.navigation:navigation-compose:2.8.0-alpha08")

    // ViewModel Compose
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
    // Core always needed
    implementation("com.zettle.sdk:core:2.11.1")

    // For Card Reader Payments
    implementation("com.zettle.sdk.feature.cardreader:ui:2.11.1")

    // For QRC Payments, choose PayPal or Venmo depending on the Market.
    implementation("com.zettle.sdk.feature.qrc:core:2.11.1")
    implementation("com.zettle.sdk.feature.qrc:paypal-ui:2.11.1")
    implementation("com.zettle.sdk.feature.qrc:venmo-ui:2.11.1")

    // For Manual card entry Payments.
    implementation("com.zettle.sdk.feature.manualcardentry:ui:2.11.1")
}