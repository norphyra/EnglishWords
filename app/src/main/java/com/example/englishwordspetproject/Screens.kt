package com.example.englishwordspetproject

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Psychology
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen (val route: String, val resourcedId: Int, val icon: ImageVector) {

    object HomeScreen: Screen("homeScreen", R.string.home_screen, Icons.Filled.Home)
    object EducationScreen: Screen("educationScreen", R.string.education_screen, Icons.Outlined.Psychology)
    object ProfileScreen: Screen("profileScreen", R.string.profile_screen, Icons.Filled.Person)

}

val items = listOf(Screen.HomeScreen, Screen.EducationScreen, Screen.ProfileScreen)
