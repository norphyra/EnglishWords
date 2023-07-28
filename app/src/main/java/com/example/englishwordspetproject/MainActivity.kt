package com.example.englishwordspetproject

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.rememberNavController
import com.example.englishwordspetproject.DI.appComponent
import com.example.englishwordspetproject.navigation.BottomNavigationBar
import com.example.englishwordspetproject.navigation.BottomNavigationBarItem
import com.example.englishwordspetproject.navigation.CustomNavHost
import com.example.englishwordspetproject.navigation.Destinations
import com.example.englishwordspetproject.navigation.SideNavigationRail
import com.example.englishwordspetproject.navigation.SideNavigationRailItem
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
                    color = MaterialTheme.colorScheme.surface
                ) {
                    val windowSizeClass = calculateWindowSizeClass(this)

                    MainScreen(windowSizeClass)
                }
            }
        }
    }
}

//viewModelStoreOwner: ViewModelStoreOwner

@Composable
fun MainScreen(windowSizeClass: WindowSizeClass) {

    val navController = rememberNavController()

    val appState = AppState(navController, windowSizeClass)

    Log.d("MainActivity", "shouldShowBottomBar: ${appState.shouldShowBottomBar} ")

    Scaffold(
        containerColor = Color.Transparent,
        contentColor = MaterialTheme.colorScheme.onSurface,
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        topBar = {},
        bottomBar = {
            if (appState.shouldShowBottomBar) {

                BottomNavigation(destinations = appState.destinationsList,
                    onNavigateToDestination = appState::navigateToDestinationBottom,
                    currentDestination = appState.currentDestination)
            }
        },
    ) { padding ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            if (appState.shouldShowNavRail
            ) {
                SideNavRail(
                    destinations = appState.destinationsList,
                    onNavigateToDestination = appState::navigateToDestinationBottom,
                    currentDestination = appState.currentDestination,
                    modifier = Modifier
                        .safeDrawingPadding(),
                )
            }
            CustomNavHost(navController = navController,
                modifier = Modifier
                    .padding(
                        top = appState.mainColumnVerticalPaddingValues,
                        bottom = if (appState.shouldShowBottomBar) 0.dp else appState.mainColumnVerticalPaddingValues,
                        start = appState.mainColumnHorizontalPaddingValues,
                        end = appState.mainColumnHorizontalPaddingValues
                    )
            )
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
private fun SideNavRail(
    destinations: List<Destinations>,
    onNavigateToDestination: (Destinations) -> Unit,
    currentDestination: NavDestination?,
    modifier: Modifier = Modifier,
) {
    SideNavigationRail(modifier = modifier) {
        destinations.forEach { destination ->
            val selected = currentDestination.isDestinationInHierarchy(destination)
            SideNavigationRailItem(
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

