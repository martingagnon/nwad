import com.github.gradle.node.npm.task.NpxTask

buildscript {
    repositories {
        google()
        mavenCentral()
        maven(url = "https://plugins.gradle.org/m2/")
    }

    dependencies {
        classpath("com.android.tools.build:gradle:7.0.4")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN}")
        classpath("org.jetbrains.kotlin:kotlin-serialization:${Versions.KOTLIN}")
        classpath("com.google.gms:google-services:4.3.10")
        classpath("org.jlleitschuh.gradle:ktlint-gradle:10.2.0")
    }
}

plugins {
    id("org.jetbrains.kotlin.js")
    id("com.github.node-gradle.node") version "3.1.0"
}

dependencies {
    api(project(":common"))
    implementation(Libs.Kotlin.Js.Stdlib)
    implementation(Libs.Kotlin.Js.KotlinWrappers.React)
    implementation(Libs.Kotlin.Js.KotlinWrappers.ReactDom)
    implementation(Libs.Kotlin.Js.KotlinWrappers.ReactRouterDom)
    implementation(Libs.Kotlin.Js.KotlinWrappers.Extensions)

    testImplementation(Libs.Kotlin.Js.Test)

    implementation(Libs.Kotlinx.SerializationJson)

    implementation(npm("exports-loader", "1.1.1"))
    implementation(npm("raw-loader", "4.0.2"))
    implementation(npm("file-loader", "6.2.0"))
    implementation(npm("postcss", "8.2.15"))
    implementation(npm("postcss-loader", "4.2.0"))
    implementation(npm("autoprefixer", "10.2.5"))
    implementation(npm("tailwindcss", "2.1.2"))
    implementation(npm("@svgr/webpack", "5.5.0"))
}

kotlin {
    js(IR) {
        browser {
            commonWebpackConfig {
                cssSupport.enabled = true
            }

            testTask {
                useKarma {
                    useChromeHeadless()
                }
            }
        }

        binaries.executable()
    }
}

val buildTailwindCss = tasks.register<NpxTask>("buildTailwindCss") {
    // Output CSS location
    val generatedFile = "build/processedResources/js/main/css/tailwind.css"

    // Location of the tailwind config file
    val tailwindConfig = "src/main/resources/css/tailwind-config.css"

    command.set("tailwind")
    args.set(listOf("build", tailwindConfig, "-o", generatedFile))

    dependsOn(tasks.npmInstall)

    // The location of the source files which Tailwind scans when running `purgecss`
    inputs.dir("src/main/kotlin")

    inputs.file(tailwindConfig)
    outputs.file(generatedFile)
}

val processResources by tasks.existing(ProcessResources::class) {
    dependsOn("buildTailwindCss")
}

rootProject.plugins.withType(org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootPlugin::class.java) {
    rootProject.the<org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootExtension>().versions.webpackCli.version =
        "4.9.0"
}
