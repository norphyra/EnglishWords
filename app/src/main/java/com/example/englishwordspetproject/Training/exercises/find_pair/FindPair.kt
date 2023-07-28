package com.example.englishwordspetproject.Training.exercises.find_pair

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.boundsInParent
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
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

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        var rect by remember {
            mutableStateOf(Rect(0f, 0f, 0f, 0f))
        }

        var offsetX by remember { mutableStateOf(0f) }
        var offsetY by remember { mutableStateOf(0f) }

        var currentRect by remember {
            mutableStateOf(Rect(0f, 0f, 0f, 0f))
        }

        Text(text = "currentTop: ${currentRect.top}")
        Text(text = "currentBottom: ${currentRect.bottom}")

        trainingWords.keys.forEachIndexed {index, word ->

            var rect by remember {
                mutableStateOf(Rect(0f, 0f, 0f, 0f))
            }

            Text(text = "targetTop: ${rect.top}")
            Text(text = "targetBottom: ${rect.bottom}")

            var string = "no coincidence"

            if (trainingWords[word]?.coincidence() == true) {
                string = "coincidence"
            }

            Text(text = string)

            Row(modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .onGloballyPositioned { coordinates ->
                    rect = coordinates.boundsInRoot()
                    trainingWords[word]?.targetRect = coordinates.boundsInWindow()
                },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(color = Color.LightGray) {
                    Text(text = word, modifier = Modifier.padding(10.dp))
                }
            }
        }


//        Surface(shape = RoundedCornerShape(20.dp),
//            color = Color.Cyan,
//            modifier = Modifier
//                .bringIntoViewRequester(BringIntoViewRequester())
//                .offset {
//                    IntOffset(
//                        offsetX.roundToInt(),
//                        offsetY.roundToInt()
//                    )
//                }
//                .pointerInput(Unit) {
//                    detectDragGestures { change, dragAmount ->
//                        change.consume()
//
//                        offsetX += dragAmount.x
//                        offsetY += dragAmount.y
//                    }
//                }
//                .onGloballyPositioned { coordinates ->
//                    trainingWords["cat"]?.currentRect = coordinates.boundsInWindow()
//                    currentRect = coordinates.boundsInWindow()
//                }
//        ) {
//            Text(text = trainingWords["cat"]!!.translate,
//                textAlign = TextAlign.Center)
//        }


        LazyVerticalGrid(columns = GridCells.Adaptive(100.dp),
        modifier = Modifier
            .heightIn(100.dp, 200.dp)
            .padding(top = 30.dp)
        ) {
            items(trainingWords.values.toList()
            ) {item ->

                var offsetX by remember { mutableStateOf(0f) }
                var offsetY by remember { mutableStateOf(0f) }

                Surface(shape = RoundedCornerShape(20.dp),
                    color = Color.Cyan,
                    modifier = Modifier
                        .offset {
                            IntOffset(
                                offsetX.roundToInt(),
                                offsetY.roundToInt()
                            )
                        }
                        .pointerInput(Unit) {
                            detectDragGestures { change, dragAmount ->
                                change.consume()
                                offsetX += dragAmount.x
                                offsetY += dragAmount.y
                            }
                        }
                        .onGloballyPositioned { coordinates ->
                            item.currentRect = coordinates.boundsInWindow()
                            currentRect = coordinates.boundsInWindow()
                        }
                ) {
                    Text(text = item.translate,
                        textAlign = TextAlign.Center)
                }
            }
        }


//        var offsetX by remember { mutableStateOf(0f) }
//        var offsetY by remember { mutableStateOf(0f) }
//
//        Box(
//            Modifier
//                .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
//                .background(Color.Blue)
//                .size(50.dp)
//                .pointerInput(Unit) {
//                    detectDragGestures { change, dragAmount ->
//                        change.consume()
//                        offsetX += dragAmount.x
//                        offsetY += dragAmount.y
//                    }
//                }
//                .onGloballyPositioned { coordinates ->
//                    Log.d("FindPair", "offsetX: ${coordinates.positionInWindow().x}")
//                    Log.d("FindPair", "offsetY: ${coordinates.positionInWindow().y}")
//                }
//        )
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