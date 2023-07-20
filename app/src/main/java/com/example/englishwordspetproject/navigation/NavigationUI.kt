package com.example.englishwordspetproject.navigation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit,
) {
    NavigationBar(
        modifier = modifier,
        tonalElevation = 0.dp,
        content = content,
    )
}

@Composable
fun RowScope.BottomNavigationBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    selectedIcon: @Composable () -> Unit = icon,
    enabled: Boolean = true,
    label: @Composable (() -> Unit)? = null,
) {
    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        icon = if (selected) selectedIcon else icon,
        modifier = modifier,
        enabled = enabled,
        label = label,
        alwaysShowLabel = true,

    )
}

//object NavigationDefaults {
//    @Composable
//    fun navigationContentColor() = MaterialTheme.colorScheme.onSurfaceVariant
//
//    @Composable
//    fun navigationSelectedItemColor() = MaterialTheme.colorScheme.onPrimaryContainer
//
//    @Composable
//    fun navigationIndicatorColor() = MaterialTheme.colorScheme.primaryContainer
//}