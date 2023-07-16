package com.example.englishwordspetproject

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.englishwordspetproject.screens.items

@Composable
fun BottomAppBar(navController: NavHostController) {
    NavigationBar(containerColor = MaterialTheme.colorScheme.surfaceVariant) {
        //val navBackStackEntry by navController.currentBackStackEntryAsState()
        //val currentDestination = navBackStackEntry?.destination

        var selectedItem by rememberSaveable {
            mutableStateOf(0)
        }

        items.forEachIndexed { index, screen ->
            NavigationBarItem(selected = selectedItem == index,
                icon = { Icon(painter = painterResource(id = if(selectedItem == index) screen.selectedIcon else screen.icon),
                              contentDescription = null) },
                label = { Text(text = stringResource(screen.resourcedId)) },
                onClick = {
                    selectedItem = index

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