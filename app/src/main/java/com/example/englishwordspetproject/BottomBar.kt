package com.example.englishwordspetproject

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomAppBar(navController: NavHostController) {
    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        items.forEach { screen ->
            NavigationBarItem(selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                icon = { Icon(imageVector = screen.icon, contentDescription = null) },
                label = { Text(text = stringResource(screen.resourcedId)) },
                onClick = {
                    navController.navigate(screen.route) {
                        // Находим в заднем стеке навигации(сохраняются все места переходов) нужное нам
                        // и не создаем новый пункт назвачения при переходе, а перемещаемся до нужного
                        // нам места назначения удаляя все несоответствующие при перемещении по стеку во
                        // время поиска
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        // Избегаем множетсва копий при многократном нажатии на один
                        // и тот же пункт назначения
                        launchSingleTop = true
                        // Восстанавливаем состояние при повторном выборе ранее выбранного элемента
                        restoreState = true
                    }
                }
            )
        }
    }
}