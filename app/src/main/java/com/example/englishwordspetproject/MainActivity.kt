package com.example.englishwordspetproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.activity
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.englishwordspetproject.DI.appComponent
import com.example.englishwordspetproject.data.LanguageViewModel
import com.example.englishwordspetproject.data.ViewModelFactory
import com.example.englishwordspetproject.screens.DictionaryScreen
import com.example.englishwordspetproject.screens.EducationScreen
import com.example.englishwordspetproject.screens.HomeScreen
import com.example.englishwordspetproject.screens.ProfileScreen
import com.example.englishwordspetproject.screens.Screen
import com.example.englishwordspetproject.ui.theme.EnglishWordspetProjectTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appComponent.injectMainActivity(this)

        setContent {
            EnglishWordspetProjectTheme {
                // A surface container using the 'background' color from the theme
                val activity = checkNotNull(LocalViewModelStoreOwner.current)

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.surface
                ) {
                    MainScreen(viewModel(activity), activity)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(languageViewModel: LanguageViewModel, viewModelStoreOwner: ViewModelStoreOwner) {
    val navController = rememberNavController()

    val showBottomSheet by languageViewModel.showBottomSheet.collectAsState()

    val sheetState = rememberModalBottomSheetState()
    
    Scaffold(bottomBar = { BottomAppBar(navController = navController)}) {
        NavHost(navController = navController, startDestination = Screen.DictionaryScreen.route,
            modifier = Modifier.padding(it)) {
            composable(Screen.HomeScreen.route) { HomeScreen() }
            composable(Screen.TrainingScreen.route) { EducationScreen() }
            composable(Screen.DictionaryScreen.route) { DictionaryScreen(viewModel(viewModelStoreOwner)) }
            composable(Screen.ProfileScreen.route) { ProfileScreen() }
        }

        if (showBottomSheet) {
            ModalBottomSheet(onDismissRequest = languageViewModel::hideBottomSheet,
            sheetState = sheetState,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.4f)) {
                Row(modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .padding(20.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                    Image(painter = painterResource(id = R.drawable.usa_flag_icon), contentDescription = null,
                        Modifier.size(60.dp))
                    Text("English")
                }
            }
        }
    }
}

