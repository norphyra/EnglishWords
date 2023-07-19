package com.example.englishwordspetproject.utils

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

@Composable
fun Int.pxToDp () = with(LocalDensity.current) { this@pxToDp.toDp() }

@Composable
fun CalculatePaddings(screenHeight: Int, screenWidth: Int): Modifier {

    var top = 20.dp
    var bottom = 20.dp

    var start = 20.dp
    var end = 20.dp

    if (screenWidth + screenHeight > 4560) {
        top = (screenHeight / 20).pxToDp()
        bottom = (screenHeight / 20).pxToDp()

        start = (screenWidth / 6).pxToDp()
        end = (screenWidth / 6).pxToDp()
    }

    return Modifier.padding(top = top, bottom = bottom, start = start, end = end)
}