package com.example.englishwordspetproject

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.englishwordspetproject.navigation.Destinations

@Stable
class AppState(
    private val navController: NavHostController,
    private val windowSizeClass: WindowSizeClass,
) {
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination


    val shouldShowBottomBar: Boolean
        get() = windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact

    /**
     * Map of top level destinations to be used in the BottomBar. The key is the
     * route.
     */
    val destinationsList = Destinations.values().asList()


    /**
     * UI logic for navigating to a bottom destination in the app. Bottom destinations have
     * only one copy of the destination of the back stack, and save and restore state whenever you
     * navigate to and from it.
     *
     * @param bottomDestination: The destination the app needs to navigate to.
     */
    fun navigateToDestinationBottom(bottomDestination: Destinations) {

        navController.navigate(bottomDestination.route) {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            // Avoid multiple copies of the same destination when
            // reselecting the same item
            launchSingleTop = true
            // Restore state when reselecting a previously selected item
            restoreState = true
        }
    }
}