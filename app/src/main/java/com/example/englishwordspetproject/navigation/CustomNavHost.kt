package com.example.englishwordspetproject.navigation

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
    startDestination: String = Destinations.HOME.route,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        composable(route = Destinations.HOME.route) { HomeScreen() }

        composable(route = Destinations.TRAINING.route) { TrainingScreen() }

        composable(route = Destinations.DICTIONARY.route) { DictionaryScreen() }

        composable(route = Destinations.PROFILE.route) { ProfileScreen() }
    }
}