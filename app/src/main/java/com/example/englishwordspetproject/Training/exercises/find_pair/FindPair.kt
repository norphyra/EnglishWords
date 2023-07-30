package com.example.englishwordspetproject.Training.exercises.find_pair

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.englishwordspetproject.Training.TrainingViewModel
import com.example.englishwordspetproject.Training.trainingWords
import com.example.englishwordspetproject.ui.theme.EnglishWordspetProjectTheme
import kotlin.math.roundToInt

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FindPair() {

    val trainingViewModel: TrainingViewModel = viewModel()

    val coroutineScope = rememberCoroutineScope()

    var currentRect by remember {
        mutableStateOf(Rect(0f, 0f, 0f, 0f))
    }

    Box (modifier = Modifier
        .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
//        var rect by remember {
//            mutableStateOf(Rect(0f, 0f, 0f, 0f))
//        }

//        Text(text = "targetTop: ${rect.top}", modifier = Modifier.align(Alignment.TopCenter))
//        Text(text = "targetBottom: ${rect.bottom}")
//
//        var string = "no coincidence"
//
//        Text(text = string, modifier = Modifier.align(Alignment.TopCenter))

        LazyVerticalGrid(columns = GridCells.Adaptive(100.dp),
            contentPadding = PaddingValues(10.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxSize()
        ) {

            item(span = { GridItemSpan(maxLineSpan) }
            ) {
                Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 30.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    trainingWords.keys.forEachIndexed {index, word ->

//                        Text(text = "currentTop: ${currentRect.top}")
//                        Text(text = "currentBottom: ${currentRect.bottom}")

//                        if (trainingWords[word]?.coincidence() == true) {
//                            string = "coincidence"
//                        }

                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .height(70.dp)
                            .padding(bottom = 10.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Box(modifier = Modifier
                                .weight(1f)
                                .border(BorderStroke(2.dp, Color.DarkGray))
                                .fillMaxHeight(),
                                contentAlignment = Alignment.CenterStart
                            ) {
                                Text(text = word,
                                    modifier = Modifier
                                        .padding(10.dp)
                                        .fillMaxWidth(0.4f),
                                    textAlign = TextAlign.Start)
                            }

                            Box(modifier = Modifier
                                .weight(1f)
                                .border(BorderStroke(2.dp,
                                    color = if (trainingWords[word]?.isCoincidence?.value ?: false  == true) Color.Green else Color.DarkGray))
                                .fillMaxHeight()
                                .onGloballyPositioned { coordinates ->
//                                    rect = coordinates.boundsInWindow()
                                    trainingWords[word]?.targetRect = coordinates.boundsInWindow()
                            },
                                contentAlignment = Alignment.CenterStart
                            ) {
                                Text(text = "",
                                    modifier = Modifier
                                        .padding(10.dp)
                                        .fillMaxWidth(0.4f),
                                    textAlign = TextAlign.Center)
                            }
                        }
                    }
                }
            }

            items(trainingWords.values.toList()
            ) {item ->

                var offsetX by remember { mutableStateOf(0f) }
                var offsetY by remember { mutableStateOf(0f) }

                Surface(shape = RoundedCornerShape(20.dp),
                    color = Color.LightGray.copy(alpha = 0.5f),
                    modifier = Modifier
                        .offset {
                            IntOffset(
                                offsetX.roundToInt(),
                                offsetY.roundToInt()
                            )
                        }
                        .pointerInput(Unit) {
                            detectDragGestures(onDragEnd = {
                                item.coincidence()
                            }) { change, dragAmount ->
                                change.consume()
                                offsetX += dragAmount.x
                                offsetY += dragAmount.y
                            }

                        }
                        .onGloballyPositioned { coordinates ->
                            item.currentRect = coordinates.boundsInWindow()
                            //currentRect = coordinates.boundsInWindow()
                        }

                ) {
                    Text(text = item.translate,
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                        modifier = Modifier.padding(horizontal = 2.dp, vertical = 8.dp),)
                }
            }
        }
    }
}

@Preview(device = Devices.PHONE, showBackground = true, name = "Phone")
//@Preview(device = Devices.TABLET, showBackground = true, name = "TABLET")
//@Preview(device = Devices.DESKTOP, showBackground = true, name = "DESKTOP")
@Composable
fun FindPairPreview() {
    EnglishWordspetProjectTheme {
        BoxWithConstraints {
            FindPair()
        }
    }
}