package com.example.englishwordspetproject

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.rememberNavController
import com.example.englishwordspetproject.DI.appComponent
import com.example.englishwordspetproject.navigation.BottomNavigationBar
import com.example.englishwordspetproject.navigation.BottomNavigationBarItem
import com.example.englishwordspetproject.navigation.BottomNavigationRail
import com.example.englishwordspetproject.navigation.BottomNavigationRailItem
import com.example.englishwordspetproject.navigation.CustomNavHost
import com.example.englishwordspetproject.navigation.Destinations
import com.example.englishwordspetproject.ui.theme.EnglishWordspetProjectTheme

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
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
                ) {
                    val windowSizeClass = calculateWindowSizeClass(this)

                    MainScreen(windowSizeClass)
                }
            }
        }
    }
}

//viewModelStoreOwner: ViewModelStoreOwner

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MainScreen(windowSizeClass: WindowSizeClass) {

    val navController = rememberNavController()

    val appState = AppState(navController, windowSizeClass)

    Log.d("MainActivity", "shouldShowBottomBar: ${appState.shouldShowBottomBar} ")

    Scaffold(
        containerColor = Color.Transparent,
        contentColor = MaterialTheme.colorScheme.onSurface,
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        bottomBar = {
            if (appState.shouldShowBottomBar) {

                BottomNavigation(destinations = appState.destinationsList,
                    onNavigateToDestination = appState::navigateToDestinationBottom,
                    currentDestination = appState.currentDestination)
            }
        },
    ) { padding ->
        Row(
            Modifier
                .fillMaxSize()
                .padding(padding)
                .consumeWindowInsets(padding)
                .windowInsetsPadding(
                    WindowInsets.safeDrawing.only(
                        WindowInsetsSides.Horizontal,
                    ),
                ),
        ) {
            if (appState.shouldShowNavRail) {
                BottomNavRail(
                    destinations = appState.destinationsList,
                    onNavigateToDestination = appState::navigateToDestinationBottom,
                    currentDestination = appState.currentDestination,
                    modifier = Modifier
                        .safeDrawingPadding(),
                )
            }
        }
        Column(
            Modifier
                .fillMaxSize()
        ) {
            CustomNavHost(navController = navController, windowSizeClass = windowSizeClass)
        }
    }
}

@Composable
private fun BottomNavigation(
    destinations: List<Destinations>,
    onNavigateToDestination: (Destinations) -> Unit,
    currentDestination: NavDestination?,
    modifier: Modifier = Modifier,
) {
    BottomNavigationBar(modifier = modifier
    ) {
        destinations.forEach {destination ->
            val selected = currentDestination.isDestinationInHierarchy(destination)

            BottomNavigationBarItem(selected = selected,
                onClick = { onNavigateToDestination(destination) },
                icon = {
                    Icon(
                        painter = painterResource(id = destination.unselectedIcon),
                        contentDescription = null,
                    )
                },
                selectedIcon = {
                    Icon(
                        painter = painterResource(id = destination.selectedIcon),
                        contentDescription = null,
                    )
                },
                label = {Text(stringResource(destination.titleTextId))},
                modifier = modifier
            )
        }
    }
}

@Composable
private fun BottomNavRail(
    destinations: List<Destinations>,
    onNavigateToDestination: (Destinations) -> Unit,
    currentDestination: NavDestination?,
    modifier: Modifier = Modifier,
) {
    BottomNavigationRail(modifier = modifier) {
        destinations.forEach { destination ->
            val selected = currentDestination.isDestinationInHierarchy(destination)
            BottomNavigationRailItem(
                selected = selected,
                onClick = { onNavigateToDestination(destination) },
                icon = {
                    Icon(
                        painter = painterResource(id = destination.unselectedIcon),
                        contentDescription = null,
                    )
                },
                selectedIcon = {
                    Icon(
                        painter = painterResource(id = destination.selectedIcon),
                        contentDescription = null,
                    )
                },
                label = { Text(stringResource(destination.titleTextId)) },
                modifier = Modifier
            )
        }
    }
}

private fun NavDestination?.isDestinationInHierarchy(destination: Destinations) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false

