package com.example.englishwordspetproject.navigation

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.englishwordspetproject.screens.DictionaryScreen
import com.example.englishwordspetproject.screens.HomeScreen
import com.example.englishwordspetproject.screens.ProfileScreen
import com.example.englishwordspetproject.screens.TrainingScreen

@Composable
fun CustomNavHost(
    modifier: Modifier = Modifier,
    startDestination: String = Destinations.DICTIONARY.route,
    navController: NavHostController,
    windowSizeClass: WindowSizeClass
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        composable(route = Destinations.HOME.route) { HomeScreen() }

        composable(route = Destinations.TRAINING.route) { TrainingScreen() }

        composable(route = Destinations.DICTIONARY.route) { DictionaryScreen(windowSizeClass = windowSizeClass) }

        composable(route = Destinations.PROFILE.route) { ProfileScreen() }
    }
}