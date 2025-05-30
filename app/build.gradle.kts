plugins { // Pag 26
    alias(libs.plugins.android.application) // Pag 26.1
    alias(libs.plugins.kotlin.android) // Pag 26.2
    id("kotlin-kapt") // Pag 26.3
    alias(libs.plugins.kotlin.compose) // Pag 26.4
}

android { // Pag 26.5
    namespace = "com.example.LivrariaAppV2" // Pag 26.5.1
    compileSdk = 36 // Pag 26.5.2

    defaultConfig { // Pag 26.5.3
        applicationId = "com.example.LivrariaAppV2" // Pag 26.5.3.1
        minSdk = 24 // Pag 26.5.3.2
        targetSdk = 36 // Pag 26.5.3.3
        versionCode = 1 // Pag 26.5.3.4
        versionName = "1.0" // Pag 26.5.3.5

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner" // Pag 26.5.3.6
        vectorDrawables { // Pag 26.5.3.7
            useSupportLibrary = true // Pag 26.5.3.7.1
        }
    }

    buildTypes { // Pag 26.5.4
        release { // Pag 26.5.4.1
            isMinifyEnabled = false // Pag 26.5.4.1.1
            proguardFiles( // Pag 26.5.4.1.2
                getDefaultProguardFile("proguard-android-optimize.txt"), // Pag 26.5.4.1.2
                "proguard-rules.pro" // Pag 26.5.4.1.2
            )
        }
    }
    compileOptions { // Pag 26.5.5
        sourceCompatibility = JavaVersion.VERSION_11 // Pag 26.5.5.1
        targetCompatibility = JavaVersion.VERSION_11 // Pag 26.5.5.2
    }
    kotlinOptions { // Pag 26.5.6
        jvmTarget = "11" // Pag 26.5.6.1
    }
    buildFeatures { // Pag 26.5.7
        compose = true // Pag 26.5.7.1
    }
    composeOptions { // Pag 26.5.8
        kotlinCompilerExtensionVersion = "1.6.10" // Pag 26.5.8.1
    }
    packaging { // Pag 26.5.9
        resources { // Pag 26.5.9.1
            excludes += "/META-INF/{AL2.0,LGPL2.1}" // Pag 26.5.9.1.1
        }
    }
}

dependencies { // Pag 26.6

    // Dependências do Compose
    implementation(libs.androidx.core.ktx) // Pag 26.6.1
    implementation(libs.androidx.lifecycle.runtime.ktx) // Pag 26.6.2
    implementation(libs.androidx.activity.compose) // Pag 26.6.3
    implementation(platform(libs.androidx.compose.bom)) // Pag 26.6.4
    implementation(libs.androidx.ui) // Pag 26.6.5
    implementation(libs.androidx.ui.graphics) // Pag 26.6.6
    implementation(libs.androidx.ui.tooling.preview) // Pag 26.6.7
    implementation(libs.androidx.material3) // Pag 26.6.8

    // --- Novas Dependências ---

    // Room (SQLite)
    implementation(libs.androidx.room.runtime) // Pag 26.6.9
    kapt(libs.androidx.room.compiler) // Pag 26.6.10
    implementation(libs.androidx.room.ktx) // Pag 26.6.11

    // Coroutines
    implementation(libs.kotlinx.coroutines.core) // Pag 26.6.12
    implementation(libs.kotlinx.coroutines.android) // Pag 26.6.13

    // ViewModel e LiveData/StateFlow (Específicos para Compose)
    implementation(libs.androidx.lifecycle.viewmodel.ktx) // Pag 26.6.14
    implementation(libs.androidx.lifecycle.viewmodel.compose) // Pag 26.6.15

    // Navigation Compose
    implementation(libs.androidx.navigation.compose) // Pag 26.6.16

    // Material Icons Extended (para ícones adicionais)
    implementation(libs.androidx.compose.material.icons.extended) // Pag 26.6.17

    // Hashing de senha (BCrypt)
    implementation(libs.jbcrypt) // Pag 26.6.18

    // Carregamento de imagens (Coil)
    implementation(libs.coil.compose) // Pag 26.6.19

    // DataStore (para FavoriteManager)
    implementation(libs.androidx.datastore.preferences) // Pag 26.6.20
    implementation("org.mindrot:jbcrypt:0.4") // Pag 26.6.21

    // --- Fim das Novas Dependências ---

    // Dependências de teste
    testImplementation(libs.junit) // Pag 26.6.22
    androidTestImplementation(libs.androidx.junit) // Pag 26.6.23
    androidTestImplementation(platform(libs.androidx.compose.bom)) // Pag 26.6.24
    androidTestImplementation(libs.androidx.espresso.core) // Pag 26.6.25
    androidTestImplementation(libs.androidx.ui.test.junit4) // Pag 26.6.26
    debugImplementation(libs.androidx.ui.tooling) // Pag 26.6.27
    debugImplementation(libs.androidx.ui.test.manifest) // Pag 26.6.28
}