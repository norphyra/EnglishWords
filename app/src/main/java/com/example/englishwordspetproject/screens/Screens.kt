package com.example.englishwordspetproject.screens

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.englishwordspetproject.R

sealed class Screen (val route: String,
                     @StringRes val resourcedId: Int,
                     @DrawableRes val icon: Int,
                     @DrawableRes val selectedIcon: Int) {
    object HomeScreen: Screen("homeScreen", R.string.home_screen, R.drawable.home_icon, R.drawable.filled_home_icon)
    object EducationScreen: Screen("educationScreen", R.string.education_screen, R.drawable.education_icon, R.drawable.filled_education_icon)
    object ProfileScreen: Screen("profileScreen", R.string.profile_screen, R.drawable.profile_icon, R.drawable.filled_profile_icon)

}

val items = listOf(Screen.HomeScreen, Screen.EducationScreen, Screen.ProfileScreen)
