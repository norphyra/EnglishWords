package com.example.englishwordspetproject.Training

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.englishwordspetproject.reusable_components.CardGrid
import com.example.englishwordspetproject.ui.theme.EnglishWordspetProjectTheme

@Composable
fun TrainingScreen() {

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .wrapContentHeight()
            .verticalScroll(scrollState, enabled = true)
    ) {
        CardGrid(titles = exercises,
            modifier = Modifier.heightIn(min = 240.dp, max = 480.dp))
    }
}

@Preview(device = Devices.PHONE, showBackground = true, name = "Phone")
//@Preview(device = Devices.TABLET, showBackground = true, name = "TABLET")
//@Preview(device = Devices.DESKTOP, showBackground = true, name = "DESKTOP")
@Composable
fun TrainingScreenPreview() {
    EnglishWordspetProjectTheme {
        BoxWithConstraints {
            TrainingScreen()
        }
    }
}