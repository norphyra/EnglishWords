package com.example.buildPlugin.dependencies

import com.example.buildPlugin.Versions

object Compose {
    const val ui = "androidx.compose.ui:ui:${Versions.compose}"
    const val graphics = "androidx.compose.ui:ui-graphics:${Versions.compose}"
    const val preview = "androidx.compose.ui:ui-tooling-preview:${Versions.compose}"
    const val tooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
    const val material3 = "androidx.compose.material3:material3:${Versions.composeMaterial3}"
}