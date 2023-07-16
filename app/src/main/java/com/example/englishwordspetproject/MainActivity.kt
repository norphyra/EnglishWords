package com.example.englishwordspetproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.englishwordspetproject.screens.EducationScreen
import com.example.englishwordspetproject.screens.HomeScreen
import com.example.englishwordspetproject.screens.ProfileScreen
import com.example.englishwordspetproject.screens.Screen
import com.example.englishwordspetproject.ui.theme.EnglishWordspetProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EnglishWordspetProjectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.surface
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    
    Scaffold(bottomBar = { BottomAppBar(navController = navController)}) {
        NavHost(navController = navController, startDestination = Screen.HomeScreen.route,
        modifier = Modifier.padding(it)) {
            composable(Screen.HomeScreen.route) { HomeScreen()}
            composable(Screen.EducationScreen.route) { EducationScreen()}
            composable(Screen.ProfileScreen.route) { ProfileScreen()}
        }
    }
}

