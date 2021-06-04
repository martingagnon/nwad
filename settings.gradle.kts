pluginManagement {
    resolutionStrategy {
        repositories {
            maven(url = "https://plugins.gradle.org/m2/")
            maven(url = "https://s3.amazonaws.com/mirego-maven/public")
            maven(url = "https://jitpack.io")
            jcenter()
        }
        
        eachPlugin {
            val kotlinPluginNames = listOf("kotlin-multiplatform", "kotlinx-serialization")

            if (kotlinPluginNames.contains(requested.id.id)) {
                useModule("org.jetbrains.kotlin:${requested.id.id}:${requested.version}")
            }

            if (requested.id.namespace == "mirego") {
                useModule("mirego:${requested.id.name}-plugin:${requested.version}")
            }
        }
    }
}

rootProject.name = "NWAD"

include(":android", ":common", ":web")
