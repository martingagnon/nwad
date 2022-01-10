plugins {
    id("com.android.application")
    id("kotlin-android")
    id("org.jetbrains.kotlin.kapt")
    id("kotlinx-serialization")
    id("org.jlleitschuh.gradle.ktlint")
}

android {
    compileSdkVersion(Versions.COMPILE_SDK)

    defaultConfig {
        minSdkVersion(Versions.MIN_SDK)
        targetSdkVersion(Versions.TARGET_SDK)

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        compose = true
    }

    lintOptions {
        isCheckReleaseBuilds = true
        isAbortOnError = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    repositories {
        mavenLocal()
        google()
        mavenCentral()
        maven(url = "https://s3.amazonaws.com/mirego-maven/public")
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
        useIR = true
        freeCompilerArgs = freeCompilerArgs +
            "-Xopt-in=kotlin.time.ExperimentalTime" +
            "-Xopt-in=kotlin.ExperimentalStdlibApi"
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.JETPACK_COMPOSE
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

configurations.all {
    exclude(group = "org.reactivestreams")
}

dependencies {
    api(project(":common"))

    implementation(Libs.Kotlin.Stdlib)
    implementation(Libs.Kotlinx.SerializationJson)
    implementation(Libs.AndroidX.AppCompat)
    implementation(Libs.Trikot(project).ViewmodelsDeclarativeCompose)

    implementation("androidx.activity:activity-compose:1.4.0")
    implementation("androidx.compose.runtime:runtime:${Versions.JETPACK_COMPOSE}")
    implementation("androidx.compose.ui:ui:${Versions.JETPACK_COMPOSE}")
    implementation("androidx.compose.foundation:foundation:${Versions.JETPACK_COMPOSE}")
    implementation("androidx.compose.foundation:foundation-layout:${Versions.JETPACK_COMPOSE}")
    implementation("androidx.compose.material:material:${Versions.JETPACK_COMPOSE}")
    implementation("androidx.compose.runtime:runtime-livedata:${Versions.JETPACK_COMPOSE}")
    implementation("androidx.compose.ui:ui-tooling:${Versions.JETPACK_COMPOSE}")
    implementation("com.google.android.material:compose-theme-adapter:1.1.2")
}

ktlint {
    android.set(true)
}
//
//  tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile).configureEach {
//    kotlinOptions {
//        jvmTarget = "11"
//        useIR = true
//    }
//  }
