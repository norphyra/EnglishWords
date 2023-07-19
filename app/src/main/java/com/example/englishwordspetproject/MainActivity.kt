package com.example.englishwordspetproject

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.englishwordspetproject.DI.appComponent
import com.example.englishwordspetproject.data.viewModels.BaseViewModel
import com.example.englishwordspetproject.data.viewModels.sections
import com.example.englishwordspetproject.screens.DictionaryScreen
import com.example.englishwordspetproject.screens.EducationScreen
import com.example.englishwordspetproject.screens.HomeScreen
import com.example.englishwordspetproject.screens.ProfileScreen
import com.example.englishwordspetproject.screens.Screen
import com.example.englishwordspetproject.ui.theme.EnglishWordspetProjectTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appComponent.injectMainActivity(this)

        setContent {
            EnglishWordspetProjectTheme {
                // A surface container using the 'background' color from the theme
                //val activity = checkNotNull(LocalViewModelStoreOwner.current)

                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = MaterialTheme.colorScheme.surface
                ) {
                    MainScreen()
                }
            }
        }
    }
}

//viewModelStoreOwner: ViewModelStoreOwner

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()

//    val showBottomSheet by viewModel.showBottomSheet.collectAsState()

    //val sheetState = rememberModalBottomSheetState()

    val context = LocalContext.current

    
    Scaffold(bottomBar = { BottomAppBar(navController = navController)}) {
        NavHost(navController = navController, startDestination = Screen.DictionaryScreen.route,
            modifier = Modifier.padding(it)) {
            composable(Screen.HomeScreen.route) { HomeScreen() }
            composable(Screen.TrainingScreen.route) { EducationScreen() }
            composable(Screen.DictionaryScreen.route) { DictionaryScreen() }
            composable(Screen.ProfileScreen.route) { ProfileScreen() }
        }

//        if (showBottomSheet) {
//            ModalBottomSheet(onDismissRequest = viewModel::hideBottomSheet,
//            sheetState = sheetState,
//            modifier = Modifier
//                .fillMaxWidth()
//                .fillMaxHeight(0.4f)) {
//                Surface(shape = RoundedCornerShape(20.dp),
//                    color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.6f),
//                    modifier = Modifier
//                        .padding(top = 10.dp)
//                        .clickable {
//                            viewModel.selectItem(sections.find { it.sectionName != viewModel.selectedItem.value }!!.sectionName)
//                        }
//                ) {
//                    Row(
//                        Modifier
//                            .fillMaxWidth(0.4f)
//                            .padding(start = 30.dp, top = 10.dp, end = 30.dp, bottom = 10.dp), horizontalArrangement = Arrangement.SpaceBetween,
//                        verticalAlignment = Alignment.CenterVertically
//                    ) {
//                        Text(text = stringResource(id = if (selectedSection == R.string.my_dictionary) R.string.my_sets else R.string.my_dictionary),
//                            fontSize = 30.sp,
//                            fontWeight = FontWeight.Bold,
//                            textAlign = TextAlign.Center,
//                            modifier = Modifier.fillMaxWidth(),
//                            color = Color.Black.copy(alpha = 0.6f))
//                    }
//                }
//            }
//        }
    }
}

