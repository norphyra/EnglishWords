package com.example.englishwordspetproject.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Primary = Color(0xFFff9c33)
val OnPrimary = Color.White
val PrimaryContainer = Color(0xFFffd5ba)
val OnPrimaryContainer = Color(0xFF042100)

val Secondary = Color(0xFF55624c)
val OnSecondary = Color.White
val SecondaryContainer = Color(0xFFd9e7cb)
val OnSecondaryContainer = Color(0xFF8282ff)

val TertiaryContainer = Color(0xFFbbebeb)
val OnTertiaryContainer = Color(0xFF002021)

val Error = Color(0xFFba1b1b)
val OnError = Color.White

val Outline = Color(0xFF73796d)
val OnOutline = Color(0xFFc4c8ba)

val Surface = Color.White
val OnSurface = Color(0xFF1c1c16)

val Background = Color.White
val OnBackground = Color(0xFF1c1c16)

val SurfaceContainer = Color.White
val OnSurfaceContainer = Color(0xFFd8d8ff)

//Icons colors
val new_words_progress_color = Color(0xFFd6dbe1)
val in_progress_words_progress_color = Color(0xFFEEE626)
val learned_words_progress_color = Color(0xFF9db482)

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
