import org.gradle.api.Project

object Libs {
    object AndroidX {
        const val AppCompat = "androidx.appcompat:appcompat:${Versions.ANDROID_APP_COMPAT}"
        const val ConstraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.ANDROIDX_CONSTRAINT_LAYOUT}"
        const val LifecycleRuntimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.ANDROIDX_LIFECYCLE}"
        const val LifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel:${Versions.ANDROIDX_LIFECYCLE}"
        const val LifecycleViewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.ANDROIDX_LIFECYCLE}"
        const val LifecycleReactiveStreams = "androidx.lifecycle:lifecycle-reactivestreams-ktx:${Versions.ANDROIDX_LIFECYCLE}"
        const val Material = "com.google.android.material:material:${Versions.ANDROID_MATERIAL}"
    }

    class Trikot(project: Project) {
        val Foundation = "com.mirego.trikot:trikotFoundation:${project.property("trikot_foundation_version")}"
        val Streams = "com.mirego.trikot:streams:${project.property("trikot_streams_version")}"
        val ViewmodelsDeclarative = "com.mirego.trikot:viewmodels-declarative-compose:${project.property("trikot_viewmodels_declarative_version")}"
        val Http = "com.mirego.trikot:http:${project.property("trikot_http_version")}"
        val Kword = "com.mirego.trikot:kword:${project.property("trikot_kword_version")}"
        val GraphQL = "com.mirego.trikot:graphql:${project.property("trikot_graphql_version")}"
        val DataSources = "com.mirego.trikot:datasources:${project.property("trikot_datasources_version")}"
    }

    object Kotlin {
        const val Stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.KOTLIN}"
        const val TestCommon = "org.jetbrains.kotlin:kotlin-test-common"
        const val TestAnnotationCommon = "org.jetbrains.kotlin:kotlin-test-annotations-common"
        const val Test = "org.jetbrains.kotlin:kotlin-test"
        const val TestJUnit = "org.jetbrains.kotlin:kotlin-test-junit"

        object Js {
            const val Stdlib = "org.jetbrains.kotlin:kotlin-stdlib-js:${Versions.KOTLIN}"
            const val Test = "org.jetbrains.kotlin:kotlin-test-js"

            object KotlinWrappers {
                const val Extensions = "org.jetbrains.kotlin-wrappers:kotlin-extensions:${Versions.KotlinWrappers.EXTENSIONS}-kotlin-${Versions.KOTLIN}"
                const val React = "org.jetbrains.kotlin-wrappers:kotlin-react:${Versions.REACT}-kotlin-${Versions.KOTLIN}"
                const val ReactDom = "org.jetbrains.kotlin-wrappers:kotlin-react-dom:${Versions.REACT}-kotlin-${Versions.KOTLIN}"
                const val ReactRouterDom = "org.jetbrains.kotlin-wrappers:kotlin-react-router-dom:${Versions.KotlinWrappers.ROUTER}-kotlin-${Versions.KOTLIN}"
                const val Styled = "org.jetbrains.kotlin-wrappers:kotlin-styled:${Versions.KotlinWrappers.STYLED}-kotlin-${Versions.KOTLIN}"
            }
        }
    }

    object Kotlinx {
        const val SerializationJson = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.KOTLINX_SERIALIZATION}"
    }

    object Mockk {
        const val Common = "io.mockk:mockk-common:${Versions.MOCKK}"
        const val Mockk =  "io.mockk:mockk:${Versions.MOCKK}"
    }

    const val Picasso = "com.squareup.picasso:picasso:${Versions.PICASSO}"
    const val PicassoTransformations = "jp.wasabeef:picasso-transformations:${Versions.PICASSO_TRANSFORMATIONS}"
    const val Ktor = "io.ktor:ktor-client-core:${Versions.KTOR}"
    const val MultiplatformSettings = "com.russhwolf:multiplatform-settings-no-arg:${Versions.MULTIPLATFORM_SETTINGS}"
}
