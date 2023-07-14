package com.example.buildPlugin.dependencies

import com.example.buildPlugin.Versions

object Test {
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val extJunit = "androidx.test.ext:junit:${Versions.extJunit}"
    const val junit = "junit:junit:${Versions.junit}"

    const val composeJUnit = "androidx.compose.ui:ui-test-junit4:${Versions.compose}"
    const val composeManifest = "androidx.compose.ui:ui-test-manifest:${Versions.compose}"
}