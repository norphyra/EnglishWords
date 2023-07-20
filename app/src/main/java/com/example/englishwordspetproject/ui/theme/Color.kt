package com.example.englishwordspetproject.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Primary = Color(0xFFee6548)
val OnPrimary = Color.White
val PrimaryContainer = Color(0xFFb8f397)
val OnPrimaryContainer = Color(0xFF042100)

val Secondary = Color(0xFF55624c)
val OnSecondary = Color.White
val SecondaryContainer = Color(0xFFd9e7cb)
val OnSecondaryContainer = Color(0xFF131f0d)

val TertiaryContainer = Color(0xFFbbebeb)
val OnTertiaryContainer = Color(0xFF002021)

val Error = Color(0xFFba1b1b)
val OnError = Color.White

val Outline = Color(0xFF73796d)
val OnOutline = Color(0xFFc4c8ba)

val Surface = Color.White
val OnSurface = Color(0xFF848c7c)

val Background = Color(0xFFf9f1de)
val OnBackground = Color(0xFF1c1c16)

val SurfaceContainer = Color(0xFFedefe5)
val OnSurfaceContainer = Color(0xFF848c7c)

//Icons clors
val new_word_icon_color = Color(0xFFc4bebe)
val in_progress_icon_color = Color(0xFFf0e516)
val learned_icon_color = Color(0xFFa1ed2f)

@Composable
fun CustomTextFieldColors() =  TextFieldDefaults.colors(errorTextColor = MaterialTheme.colorScheme.error,
    errorLeadingIconColor = MaterialTheme.colorScheme.error,
    focusedIndicatorColor = Color.Transparent,
    unfocusedIndicatorColor = Color.Transparent)
