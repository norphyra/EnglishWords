package com.example.englishwordspetproject.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.colorResource
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF386a20),
    onPrimary = Color.White,
    primaryContainer = Color(0xFFb8f397),
    onPrimaryContainer =Color(0xFF042100),

    secondary = Color(0xFF55624c),
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFd9e7cb),
    onSecondaryContainer = Color(0xFF131f0d),

    tertiaryContainer = Color(0xFFbbebeb),
    onTertiaryContainer = Color(0xFF002021),

    error = Color(0xFFba1b1b),
    onError = Color.White,

    outline = Color(0xFF74796e),

    surface = Color(0xFFfdfdf5),
    onSurface = Color(0xFF848c7c),

    background = Color(0xFFfffcf3),
    onBackground = Color(0xFF1c1c16),

    surfaceVariant = Color(0xFFedefe5), //surface container
    onSurfaceVariant = Color(0xFF848c7c) //onSurface container

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