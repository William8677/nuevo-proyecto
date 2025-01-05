plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.hiltAndroid)
    alias(libs.plugins.googleServices)
    alias(libs.plugins.crashlytics)
    alias(libs.plugins.ksp)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlinKapt)
}

android {
    namespace = "com.williamfq.xhat"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.williamfq.xhat"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        multiDexEnabled = true

        ndk {
            abiFilters += listOf("armeabi-v7a", "arm64-v8a", "x86", "x86_64")
        }

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isDebuggable = true
            isMinifyEnabled = false
        }
    }

    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
        languageVersion = "1.9"
        apiVersion = "1.9"
        freeCompilerArgs += listOf(
            "-Xopt-in=kotlin.RequiresOptIn",
            "-Xallow-jvm-ir-dependencies",
            "-Xjvm-default=all"
        )
    }

    buildFeatures {
        buildConfig = true
        compose = true
        viewBinding = true
        dataBinding = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
    }

    packaging {
        resources {
            excludes += setOf(
                "libsignal_jni*.dylib",
                "signal_jni*.dll",
                "META-INF/*.kotlin_module",
                "META-INF/versions/**",
                "/META-INF/{AL2.0,LGPL2.1}",
                "META-INF/DEPENDENCIES",
                "META-INF/LICENSE",
                "META-INF/LICENSE.txt",
                "META-INF/NOTICE",
                "META-INF/NOTICE.txt",
                "META-INF/DEPENDENCIES.txt",
                "META-INF/services/javax.annotation.processing.Processor"
            )
        }
    }

    testOptions {
        unitTests.isIncludeAndroidResources = true
    }

    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}


tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile>().configureEach {
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
        freeCompilerArgs.addAll(
            "-Xopt-in=kotlin.RequiresOptIn",
            "-Xallow-jvm-ir-dependencies",
            "-Xjvm-default=all"
        )
    }
}

configurations.all {
    resolutionStrategy {
        // Forzar versiones de Signal
        force("org.signal:libsignal-android:0.64.1")
        force("org.signal:libsignal-client:0.64.1")
        // Forzar versiones de AndroidX
        force("androidx.core:core-ktx:1.12.0")
        force("androidx.appcompat:appcompat:1.6.1")
    }

    exclude(group = "com.google.api.grpc", module = "proto-google-common-protos")
    exclude(group = "com.google.firebase", module = "protolite-well-known-types")
    exclude(group = "com.google.protobuf", module = "protobuf-javalite")
    exclude(group = "com.google.protobuf", module = "protobuf-java")

}

dependencies {
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(libs.espressoCore)

    // Desugaring
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.1.4")

    // 🔧 Firebase
    implementation(platform(libs.firebaseBom))
    implementation(libs.firebaseAnalyticsKtx)
    implementation(libs.firebaseCrashlyticsKtx)
    implementation(libs.firebaseAuthKtx)
    implementation(libs.firebaseFirestoreKtx)
    implementation(libs.firebaseDatabaseKtx)
    implementation(libs.firebaseMessagingKtx)
    implementation(libs.firebaseDynamicLinksKtx)
    implementation(libs.firebaseAppdistributionKtx)

    implementation(libs.firebaseMlVision) {
        exclude(group = "com.google.api.grpc", module = "proto-google-common-protos")
        exclude(group = "com.google.firebase", module = "protolite-well-known-types")
        exclude(group = "com.google.protobuf", module = "protobuf-javalite")
        exclude(group = "com.google.protobuf", module = "protobuf-java")
        exclude(group = "com.google.android.gms", module = "play-services-vision-common")
    }
    implementation(libs.firebaseMlModelInterpreter)
    implementation(libs.firebaseMlNaturalLanguage)
    implementation(libs.firebaseMlNaturalLanguageTranslateModel)

    // 📱 AndroidX
    implementation(libs.androidxCoreKtx)
    implementation(libs.androidxAppcompat)
    implementation(libs.androidxMaterial)
    implementation(libs.androidxConstraintlayout)
    implementation(libs.androidxMultidex)
    implementation(libs.androidxNavigationFragmentKtx)
    implementation(libs.androidxNavigationUiKtx)

    // 📍 Google Maps
    implementation(libs.playServicesAds)
    implementation(libs.playServicesMeasurementApi)
    implementation(libs.playServicesMaps) {
        exclude(group = "com.google.android.gms", module = "play-services-measurement-api")
    }

    // Room Dependencies
    implementation(libs.androidxRoomRuntime)
    implementation(libs.androidxRoomKtx)
    implementation(libs.foundationAndroid)
    implementation(libs.foundationDesktop)
    implementation(libs.androidx.hilt.common)
    implementation(libs.sqlcipher.android)
    implementation(libs.androidx.hilt.work)
    implementation(libs.firebase.inappmessaging)
    implementation(libs.playServicesLocation)
    implementation(libs.firebase.storage.ktx)
    ksp(libs.androidxRoomCompiler)
    implementation(libs.androidxRoomMigration)

    implementation(libs.androidxLifecycleRuntimeKtx)
    implementation(libs.androidxLifecycleViewmodelKtx)
    implementation(libs.androidxWorkmanager)
    implementation(libs.androidxBiometric)

    // 🌐 Networking
    implementation(libs.okhttp)
    implementation(libs.retrofit)
    implementation(libs.retrofitConverterGson)
    implementation(libs.lottie)
    implementation(libs.webrtc)
    implementation(libs.mediator)

    // 🖼️ Image Loading
    implementation(libs.glide)
    ksp(libs.glideCompiler)

    // 🖌️ UI Components
    implementation(libs.circleImageView)
    implementation(libs.mpAndroidChart)
    implementation(libs.toasty)

    // 🔗 Dependency Injection
    implementation(libs.dagger)
    implementation(libs.hiltAndroid)
    ksp(libs.daggerCompiler)
    ksp(libs.hiltCompiler)

    // 🎬 Media3
    implementation(libs.media3ExoPlayer)
    implementation(libs.media3ExoPlayerDash)
    implementation(libs.media3ExoPlayerHls)
    implementation(libs.media3ExoPlayerSmoothstreaming)
    implementation(libs.media3ExoPlayerRtsp)
    implementation(libs.media3ExoPlayerMidi)
    implementation(libs.media3ExoPlayerIma)
    implementation(libs.media3DataSourceCronet)
    implementation(libs.media3DataSourceOkhttp)
    implementation(libs.media3DataSourceRtmp)
    implementation(libs.media3Ui)
    implementation(libs.media3UiLeanback)
    implementation(libs.media3Session)
    implementation(libs.media3Extractor)
    implementation(libs.media3Cast)
    implementation(libs.media3ExoPlayerWorkmanager)
    implementation(libs.media3Transformer)
    implementation(libs.media3Effect)
    implementation(libs.media3Muxer)
    implementation(libs.media3TestUtils)
    implementation(libs.media3TestUtilsRobolectric)
    implementation(libs.media3Container)
    implementation(libs.media3Database)
    implementation(libs.media3Decoder)
    implementation(libs.media3DataSource)
    implementation(libs.media3Common)
    implementation(libs.media3CommonKtx)

    // 📚 Otras Librerías
    implementation(libs.coroutinesCore)
    implementation(libs.googleCloudTranslate)
    implementation(libs.googleCloudSpeech)
    implementation(libs.protoGoogleCommonProtos)
    implementation(libs.protoliteWellKnownTypes)
    implementation(libs.mlkitFaceDetection)
    implementation(libs.timber)

    // Compose
    implementation(libs.androidxComposeUi)
    implementation(libs.androidxComposeMaterial)
    implementation(libs.androidxComposeUiToolingPreview)
    debugImplementation(libs.androidxComposeUiTooling)
    implementation(libs.activityCompose)
    implementation(libs.androidxMaterial)
    implementation(libs.androidxMaterial3)
    implementation(libs.databinding)
    implementation("androidx.compose.material:material-icons-core:1.7.6")
    implementation("androidx.compose.material:material-icons-extended:1.7.6")
    implementation ("com.google.code.gson:gson:2.11.0")
    implementation(libs.bundles.serialization)

    // Signal dependencies
    implementation("org.signal:libsignal-android:0.64.1") {
        exclude(group = "org.whispersystems", module = "signal-protocol-java")
    }
    implementation("org.signal:libsignal-client:0.64.1") {
        exclude(group = "org.whispersystems", module = "signal-protocol-java")
    }
}

kotlin {
    jvmToolchain(17)
}
