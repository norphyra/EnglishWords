package com.example.englishwordspetproject.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.englishwordspetproject.R

enum class Destinations(
    @DrawableRes val selectedIcon: Int,
    @DrawableRes val unselectedIcon: Int,
    @StringRes val titleTextId: Int,
    val route: String
) {
    HOME (
        selectedIcon = R.drawable.filled_home_icon,
        unselectedIcon = R.drawable.home_icon,
        titleTextId = R.string.home_screen,
        route = "Home_screen"
    ),
    TRAINING (
        selectedIcon = R.drawable.filled_training_icon,
        unselectedIcon = R.drawable.training_icon,
        titleTextId = R.string.training_screen,
        route = "Training_screen"
    ),
    DICTIONARY (
        selectedIcon = R.drawable.filled_dictionary_icon,
        unselectedIcon = R.drawable.dictionary_icon,
        titleTextId = R.string.dictionary_screen,
        route = "Dictionary_screen"
    ),
    PROFILE (
        selectedIcon = R.drawable.filled_profile_icon,
        unselectedIcon = R.drawable.profile_icon,
        titleTextId = R.string.profile_screen,
        route = "Profile_screen"
    ),
}
