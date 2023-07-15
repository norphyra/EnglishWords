package com.example.buildPlugin.dependencies

import com.example.buildPlugin.Versions

object Other {
    const val ktxCore = "androidx.core:core-ktx:${Versions.ktxCore}"

    const val bomKotlin = "org.jetbrains.kotlin:kotlin-bom:${Versions.bomKotlin}"

    const val composeActivity = "androidx.activity:activity-compose:${Versions.composeActivity}"

    const val ktxViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.ktxViewModel}"

    const val ktxFragment = "androidx.fragment:fragment-ktx:${Versions.ktxFragment}"

    const val ktxLifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.ktxLifecycle}"

    const val bomCompose = "androidx.compose:compose-bom:${Versions.bomCompose}"

    const val serialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.serialization}"

}