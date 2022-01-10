import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    id("com.android.library")
    id("kotlin-multiplatform")
    id("kotlinx-serialization")
    id("org.jlleitschuh.gradle.ktlint")
    id("mirego.kword") version Versions.TRIKOT_KWORD_PLUGIN
    id("jacoco")
}

group = "com.mirego.nwad"

android {
    compileSdk = Versions.COMPILE_SDK
    defaultConfig {
        minSdk = Versions.MIN_SDK
    }
    sourceSets {
        val main by getting {
            resources.srcDir("src/commonMain/resources/")
        }
    }

    testOptions {
        unitTests {
            isReturnDefaultValues = true
            isIncludeAndroidResources = true
        }
    }
}

kword {
    translationFile = file("src/commonMain/resources/translations/translation.en.json")
    enumClassName = "com.mirego.nwad.localization.KWordTranslation"
    generatedDir = file("src/commonMain/generated")
}

ktlint {
    filter {
        exclude {
            it.file.path.contains("/build/") || it.file.path.contains("/generated/")
            it.file.path.contains("KWordTranslation")
        }
    }
}

kotlin {
    android {
        publishLibraryVariants("release", "debug")
    }

    ios {
        binaries {
            framework {
                embedBitcode("bitcode")
                baseName = Consts.TRIKOT_FRAMEWORK_NAME
                transitiveExport = true
                export(Libs.Trikot(project).Foundation)
                export(Libs.Trikot(project).Streams)
                export(Libs.Trikot(project).ViewmodelsDeclarative)
                export(Libs.Trikot(project).Http)
                export(Libs.Trikot(project).Kword)
            }
        }
    }

    js(IR) {
        moduleName = "nwad"

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

    sourceSets {
        all {
            languageSettings.useExperimentalAnnotation("kotlin.Experimental")
            languageSettings.useExperimentalAnnotation("kotlin.time.ExperimentalTime")
            languageSettings.useExperimentalAnnotation("kotlin.js.ExperimentalJsExport")
            languageSettings.useExperimentalAnnotation("kotlinx.serialization.ExperimentalSerializationApi")
        }

        val commonMain by getting {
            dependencies {
                api(Libs.Trikot(project).Foundation)
                api(Libs.Trikot(project).Streams)
                api(Libs.Trikot(project).ViewmodelsDeclarative)
                api(Libs.Trikot(project).Http)
                api(Libs.Trikot(project).Kword)
                api(Libs.Trikot(project).GraphQL)
                api(Libs.Trikot(project).DataSources)
                implementation(Libs.Kotlinx.SerializationJson)
                implementation(Libs.MultiplatformSettings)
            }
            kotlin.srcDir(kword.generatedDir)
        }

        val commonTest by getting {
            dependencies {
                implementation(Libs.Kotlin.TestCommon)
                implementation(Libs.Kotlin.TestAnnotationCommon)
            }
        }

        val androidMain by getting {
            dependsOn(commonMain)
            dependencies {
                implementation(Libs.AndroidX.LifecycleViewModel)
                implementation(Libs.AndroidX.LifecycleViewModelKtx)
            }
        }

        val androidTest by getting {
            dependsOn(commonTest)
            dependencies {
                implementation(Libs.Kotlin.Test)
                implementation(Libs.Kotlin.TestJUnit)
                implementation(Libs.Mockk.Mockk)
            }
        }

        val iosMain by getting {
            dependsOn(commonMain)
        }

        val jsMain by getting {
            dependsOn(commonMain)
            dependencies {
                implementation(Libs.Kotlin.Js.Stdlib)
                implementation(Libs.Kotlin.Js.KotlinWrappers.Extensions)

                implementation(npm("exports-loader", "1.1.1"))
                implementation(npm("raw-loader", "4.0.2"))
            }
        }

        val jsTest by getting {
            dependencies {
                implementation(Libs.Kotlin.Js.Test)

                implementation(npm("exports-loader", "1.1.1"))
                implementation(npm("raw-loader", "4.0.2"))
            }
        }
    }
}

/**
 * This task attaches native framework built from ios module to Xcode project (see iosApp directory)
 * Don't run this task directly, Xcode runs this task itself during its build process.
 * Before opening the project from iosApp directory in Xcode, make sure all Gradle infrastructure exists
 * (gradle.wrapper, gradlew).
 */
val copyFramework by tasks.creating {
    val buildType = project.findProperty("kotlin.build.type")?.toString() ?: "RELEASE"
    val target = project.findProperty("kotlin.target")?.toString() ?: "iosArm64"
    val kotlinNativeTarget = kotlin.targets.getByName<KotlinNativeTarget>(target)
    val framework = kotlinNativeTarget.binaries.getFramework(buildType)
    dependsOn(framework.linkTask)

    doLast {
        val srcFile = framework.outputFile
        val targetDir = project.property("configuration.build.dir")
        val frameworkDir = "$targetDir/${Consts.TRIKOT_FRAMEWORK_NAME}.framework"
        val translationDir = "$projectDir/../common/src/commonMain/resources/translations-jasper"

        copy {
            from(srcFile.parent)
            into(targetDir!!)
            include("${Consts.TRIKOT_FRAMEWORK_NAME}.framework/**")
            include("${Consts.TRIKOT_FRAMEWORK_NAME}.framework.dSYM/**")
        }
        copy {
            from(translationDir)
            into(frameworkDir)
            include("**")
        }
    }
}

project.afterEvaluate {
    project.tasks.filter { task -> task.name.startsWith("compile") && task.name.contains("Kotlin") }
            .forEach { task ->
                task.dependsOn("kwordGenerateEnum")
            }
}

jacoco {
    toolVersion = "0.8.7"
    reportsDir = file("build/reports")
}

val jacocoTestReport by tasks.creating(JacocoReport::class) {
    dependsOn("test")

    group = "Reporting"
    description = "Generate Jacoco coverage reports"

    reports {
        xml.isEnabled = true
        html.isEnabled = true
    }
    val excludes: (ConfigurableFileTree) -> Unit = {
        it.exclude(
                "**/serializer.class",
                "**/factories**",
                "**/models**",
                "com/mirego/jasper/Environment**",
                "com/mirego/jasper/localization",
                "com/mirego/jasper/api/DataState**"
        )
    }
    classDirectories.setFrom(
            listOf(
                    fileTree("build/intermediates/classes/debug", excludes),
                    fileTree("build/tmp/kotlin-classes/debug", excludes)
            )
    )
    executionData.setFrom(files("build/jacoco/testDebugUnitTest.exec"))
    sourceDirectories.setFrom(files(listOf("src/commonMain/kotlin")))
}
