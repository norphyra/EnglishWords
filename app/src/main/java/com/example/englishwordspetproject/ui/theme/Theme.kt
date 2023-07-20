package com.example.englishwordspetproject.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = OnPrimary,
    onPrimary = Primary,
    primaryContainer = OnPrimaryContainer,
    onPrimaryContainer = PrimaryContainer,

    secondary = OnSecondary,
    onSecondary = Secondary,
    secondaryContainer = OnSecondaryContainer,
    onSecondaryContainer = SecondaryContainer,

    tertiaryContainer = OnTertiaryContainer,
    onTertiaryContainer = TertiaryContainer,

    error = OnError,
    onError = Error,

    outline = OnOutline,
    outlineVariant = Outline,

    surface = OnSurface,
    onSurface = Surface,

    background = OnBackground,
    onBackground = Background,

    surfaceVariant = OnSurfaceContainer,
    onSurfaceVariant = SurfaceContainer
)

private val LightColorScheme = lightColorScheme(
    primary = Primary,
    onPrimary = OnPrimary,
    primaryContainer = PrimaryContainer,
    onPrimaryContainer = OnPrimaryContainer,

    secondary = Secondary,
    onSecondary = OnSecondary,
    secondaryContainer = SecondaryContainer,
    onSecondaryContainer = OnSecondaryContainer,

    tertiaryContainer = TertiaryContainer,
    onTertiaryContainer = OnTertiaryContainer,

    error = Error,
    onError = OnError,

    outline = Outline,
    outlineVariant = OnOutline,

    surface = Surface,
    onSurface = OnSurface,

    background = Background,
    onBackground = OnBackground,

    surfaceVariant = SurfaceContainer,
    onSurfaceVariant = OnSurfaceContainer,

//    tertiary = Color(0xFF606042),
//    onTertiary = Color.White,
)

@Composable
fun EnglishWordspetProjectTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
//        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
//            val context = LocalContext.current
//            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
//        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}