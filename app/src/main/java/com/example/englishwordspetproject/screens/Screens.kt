package com.example.englishwordspetproject.screens

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.englishwordspetproject.R

sealed class Screen (val route: String,
                     @StringRes val resourcedId: Int,
                     @DrawableRes val icon: Int,
                     @DrawableRes val selectedIcon: Int) {
    object HomeScreen: Screen("homeScreen", R.string.home_screen, R.drawable.home_icon, R.drawable.filled_home_icon)
    object TrainingScreen: Screen("trainingScreen", R.string.training_screen, R.drawable.training_icon, R.drawable.filled_training_icon)
    object DictionaryScreen: Screen("dictionaryScreen", R.string.dictionary_screen, R.drawable.dictionary_icon, R.drawable.filled_dictionary_icon)
    object ProfileScreen: Screen("profileScreen", R.string.profile_screen, R.drawable.profile_icon, R.drawable.filled_profile_icon)

}

val items = listOf(Screen.HomeScreen, Screen.TrainingScreen, Screen.DictionaryScreen, Screen.ProfileScreen)
