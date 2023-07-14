object Dependencies {

    object Core {
        const val version = "1.10.0"

        const val core = "androidx.core:core-ktx:$version"
    }

    object Kotlin {
        const val version = "1.9.0"

        const val kotlin = "org.jetbrains.kotlin:kotlin-bom:$version"
    }

    object Activity {
        const val version = "1.7.2"

        const val activity = "androidx.activity:activity-compose:$version"
    }

    object Lifecycle {
        const val version = "2.6.1"

        const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:$version"
    }

    object Compose {

        const val version = "1.4.3"

        const val ui = "androidx.compose.ui:ui:$version"
        const val graphics = "androidx.compose.ui:ui-graphics:$version"
        const val preview = "androidx.compose.ui:ui-tooling-preview:$version"
        const val material3 = "androidx.compose.material3:material3:1.1.1"

        const val composeJUnit = "androidx.compose.ui:ui-test-junit4:$version"
        const val composeUITooling = "androidx.compose.ui:ui-tooling:$version"
        const val composeManifest = "androidx.compose.ui:ui-test-manifest:$version"
    }

    object BOM {
        const val version = "2023.06.01"

        const val compose = "androidx.compose:compose-bom:$version"
    }

    object Test {
        const val espresso = "androidx.test.espresso:espresso-core:3.6.0-alpha01"
        const val ExtJunit = "androidx.test.ext:junit:1.1.5"
        const val junit = "junit:junit:4.13.2"
    }
}