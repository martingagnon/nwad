buildscript {
    repositories {
        google()
        maven(url = "https://plugins.gradle.org/m2/")
    }

    dependencies {
        classpath("com.android.tools.build:gradle:4.2.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN}")
        classpath("org.jetbrains.kotlin:kotlin-serialization:${Versions.KOTLIN}")
        classpath("com.google.gms:google-services:4.3.8")
        classpath("org.jlleitschuh.gradle:ktlint-gradle:10.0.0")
    }
}

repositories {
    google()
}

allprojects {
    buildscript {
        repositories {
            google()
        }
    }
    repositories {
        mavenLocal()
        google()
        mavenCentral()
        maven(url = "https://kotlin.bintray.com/kotlinx")
        maven(url = "https://jitpack.io")
        maven(url = "https://s3.amazonaws.com/mirego-maven/public")
        // Deprecated - Required for old transitive dependencies from Trikot, to be removed
        // when Trikot upgrade to Kotlin 1.5.10
        // See https://github.com/JetBrains/kotlin-wrappers for more information.
        maven(url = "https://maven.pkg.jetbrains.space/kotlin/p/kotlin/kotlin-js-wrappers")
    }
}