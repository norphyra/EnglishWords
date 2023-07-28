package com.example.englishwordspetproject.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.englishwordspetproject.Dictionary.DictionaryScreen
import com.example.englishwordspetproject.Home.HomeScreen
import com.example.englishwordspetproject.Profile.ProfileScreen
import com.example.englishwordspetproject.Training.TrainingScreen
import com.example.englishwordspetproject.Training.exercises.find_pair.FindPair

@Composable
fun CustomNavHost(
    modifier: Modifier = Modifier,
    startDestination: String = "find pair",
    navController: NavHostController,
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

        //games
        composable(route = "find pair") {FindPair()}
    }
}