package com.example.buildPlugin.dependencies

import com.example.buildPlugin.Versions

object Compose {
    const val ui = "androidx.compose.ui:ui:${Versions.compose}"
    const val graphics = "androidx.compose.ui:ui-graphics:${Versions.compose}"
    const val preview = "androidx.compose.ui:ui-tooling-preview:${Versions.compose}"
    const val tooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
    const val util = "androidx.compose.ui:ui-util:${Versions.compose}"
    const val material3 = "androidx.compose.material3:material3:${Versions.composeMaterial3}"
    const val navigation = "androidx.navigation:navigation-compose:${Versions.navigation}"
    const val icons = "androidx.compose.material:material-icons-extended:${Versions.compose}"
    const val windowSizeClass = "androidx.compose.material3:material3-window-size-class:${Versions.composeMaterial3}"
}