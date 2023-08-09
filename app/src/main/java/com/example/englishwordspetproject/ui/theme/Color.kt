package com.example.englishwordspetproject.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val md_theme_light_primary = Color(0xFFFFFFFF)
val md_theme_light_onPrimary = Color(0xFF000000)
val md_theme_light_primaryContainer = Color(0xFFffbf08)
val md_theme_light_onPrimaryContainer = Color(0xFFFFFFFF)
val md_theme_light_secondary = Color(0xFF6C5C3F)
val md_theme_light_onSecondary = Color(0xFFFFFFFF)
val md_theme_light_secondaryContainer = Color(0xFFF5E0BB)
val md_theme_light_onSecondaryContainer = Color(0xFFffbf08)
val md_theme_light_tertiary = Color(0xFF4B6546)
val md_theme_light_onTertiary = Color(0xFFFFFFFF)
val md_theme_light_tertiaryContainer = Color(0xFFCCEBC4)
val md_theme_light_onTertiaryContainer = Color(0xFF082008)
val md_theme_light_error = Color(0xFFBA1A1A)
val md_theme_light_errorContainer = Color(0xFFFFDAD6)
val md_theme_light_onError = Color(0xFFFFFFFF)
val md_theme_light_onErrorContainer = Color(0xFF410002)
val md_theme_light_background = Color(0xFFFFFBFF)
val md_theme_light_onBackground = Color(0xFF1E1B16)
val md_theme_light_outline = Color(0xFF7F7667)
val md_theme_light_inverseOnSurface = Color(0xFFF8EFE7)
val md_theme_light_inverseSurface = Color(0xFF34302A)
val md_theme_light_inversePrimary = Color(0xFFFCBC00)
val md_theme_light_surfaceTint = Color(0xFF795900)
val md_theme_light_outlineVariant = Color(0xFFD0C5B4)
val md_theme_light_scrim = Color(0xFF000000)
val md_theme_light_surface = Color(0xFFFFFFFF)
val md_theme_light_onSurface = Color(0xFF000000)
val md_theme_light_surfaceVariant = Color(0xFFFFFFFF) //surface container
val md_theme_light_onSurfaceVariant = Color(0xFFd8dde4)

val md_theme_dark_primary = Color(0xFFFCBC00)
val md_theme_dark_onPrimary = Color(0xFF402D00)
val md_theme_dark_primaryContainer = Color(0xFF5C4300)
val md_theme_dark_onPrimaryContainer = Color(0xFFFFDEA0)
val md_theme_dark_secondary = Color(0xFFD8C4A0)
val md_theme_dark_onSecondary = Color(0xFF3B2F15)
val md_theme_dark_secondaryContainer = Color(0xFF53452A)
val md_theme_dark_onSecondaryContainer = Color(0xFFF5E0BB)
val md_theme_dark_tertiary = Color(0xFFB1CFA9)
val md_theme_dark_onTertiary = Color(0xFF1D361B)
val md_theme_dark_tertiaryContainer = Color(0xFF334D30)
val md_theme_dark_onTertiaryContainer = Color(0xFFCCEBC4)
val md_theme_dark_error = Color(0xFFFFB4AB)
val md_theme_dark_errorContainer = Color(0xFF93000A)
val md_theme_dark_onError = Color(0xFF690005)
val md_theme_dark_onErrorContainer = Color(0xFFFFDAD6)
val md_theme_dark_background = Color(0xFF1E1B16)
val md_theme_dark_onBackground = Color(0xFFE9E1D8)
val md_theme_dark_outline = Color(0xFF998F80)
val md_theme_dark_inverseOnSurface = Color(0xFF1E1B16)
val md_theme_dark_inverseSurface = Color(0xFFE9E1D8)
val md_theme_dark_inversePrimary = Color(0xFF795900)
val md_theme_dark_surfaceTint = Color(0xFFFCBC00)
val md_theme_dark_outlineVariant = Color(0xFF4D4639)
val md_theme_dark_scrim = Color(0xFF000000)
val md_theme_dark_surface = Color(0xFF16130E)
val md_theme_dark_onSurface = Color(0xFFCDC5BD)
val md_theme_dark_surfaceVariant = Color(0xFF4D4639)
val md_theme_dark_onSurfaceVariant = Color(0xFFD0C5B4)


val seed = Color(0xFFFFBF08)


//Icons colors
val new_words_progress_color = Color(0xFFd8dde4)
val in_progress_words_progress_color = Color(0xFFFCBC00)
val learned_words_progress_color = Color(0xFF6ba038)

//GridCardColors

val firstCard = Color(0xFFff6c6c)
val secondCard = Color(0xFFa1ffd6)
val thirdCard = Color(0xFFffaa66)
val fourthCard = Color(0xFF9eaffd)

val gridCardColors = listOf(firstCard, secondCard, thirdCard, fourthCard)

//Games colors

//Find Pair
val DraggableItemColor = Color(0xFFE8E8E8)

@Composable
fun CustomTextFieldColors() =  TextFieldDefaults.colors(errorTextColor = MaterialTheme.colorScheme.error,
    errorLeadingIconColor = MaterialTheme.colorScheme.error,
    focusedIndicatorColor = Color.Transparent,
    unfocusedIndicatorColor = Color.Transparent)
