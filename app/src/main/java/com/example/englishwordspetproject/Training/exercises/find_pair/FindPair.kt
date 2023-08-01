package com.example.englishwordspetproject.Training.exercises.find_pair

import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.englishwordspetproject.Training.TrainingViewModel
import com.example.englishwordspetproject.Training.TrainingWordsInfo
import com.example.englishwordspetproject.ui.theme.DraggableItemColor
import com.example.englishwordspetproject.ui.theme.EnglishWordspetProjectTheme
import kotlin.math.roundToInt

@Composable
fun FindPair() {

    val trainingViewModel: TrainingViewModel = viewModel()

    val coroutineScope = rememberCoroutineScope()

    Column (modifier = Modifier
        .fillMaxSize(),
        horizontalAlignment = CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        trainingViewModel.trainingWords.keys.forEachIndexed {index, word ->

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
                    AutoResizeText(text = word,
                        fontSizeRange = FontSizeRange(
                            min = 10.sp,
                            max = 16.sp,
                        ),
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Start)
                }

                Box(modifier = Modifier
                    .weight(1f)
                    .border(
                        BorderStroke(
                            2.dp,
                            color = if (trainingViewModel.trainingWords[word]?.state?.isCoincidence?.value ?: false == true) Color.Green else Color.DarkGray
                        )
                    )
                    .fillMaxHeight()
                    .onGloballyPositioned { coordinates ->

                        trainingViewModel.trainingWords[word]?.state?.targetRect =
                            coordinates.boundsInWindow()
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

        ListDraggableWords(items = trainingViewModel.trainingWords.values.toList(),
            modifier = Modifier.padding(top = 20.dp),
            fontSize = 16.sp,
            horizontalTextPadding = 12.dp,
            verticalTextPadding = 4.dp,
            verticalSpace = 10.dp,
            horizontalSpace = 10.dp,
            itemHeight = 40.dp)

    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ListDraggableWords(items: List<TrainingWordsInfo>,
                       modifier: Modifier = Modifier,
                       horizontalTextPadding: Dp,
                       verticalTextPadding: Dp,
                       fontSize: TextUnit,
                       verticalSpace: Dp,
                       horizontalSpace: Dp,
                       itemHeight: Dp
) {

    FlowRow(modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(horizontalSpace)) {
        
        items.forEach {item ->

            val surfaceTransition = updateTransition(item.state.surfaceState, label = "surface state")

            val elevationTransition = updateTransition(item.state.isDragging, label = "drag state")

            val roundedCornerRadius by surfaceTransition.animateDp(label = "rounded corner radius")
                {state ->
                    when (state.value) {
                        SurfaceState.Normal -> 20.dp
                        SurfaceState.Expanded -> 0.dp
                    } }

            val elevation by elevationTransition.animateDp(label = "elevation")
            { isDragging ->
                if (isDragging.value) 8.dp else 0.dp
            }

            Surface(shape = RoundedCornerShape(roundedCornerRadius),
                    color = DraggableItemColor,
                    shadowElevation = elevation,
                    modifier = Modifier
                        .offset {
                            IntOffset(
                                item.state.offsetX.value.roundToInt(),
                                item.state.offsetY.value.roundToInt()
                            )
                        }
                        .zIndex(item.state.zIndex)
                        .padding(top = verticalSpace)
                        .height(itemHeight)
                        .pointerInput(Unit) {

                            detectDragGestures(
                                onDragStart = {
                                    item.state.isDragging.value = true
                                },
                                onDragEnd = {
                                    item.state.coincidence()
                                    if (item.state.isCoincidence.value) {
                                        item.state.surfaceState.value = SurfaceState.Expanded
                                        item.state.isDragging.value = false

                                        item.state.offsetX.value += item.state.targetRect.center.x - item.state.currentRect.center.x
                                        item.state.offsetY.value += item.state.targetRect.center.y - item.state.currentRect.center.y
                                    }
                                }) { change, dragAmount ->
                                if (item.state.isCoincidence.value) {
                                    item.state.surfaceState.value = SurfaceState.Expanded
                                    return@detectDragGestures
                                }
                                change.consume()
                                item.state.offsetX.value += dragAmount.x
                                item.state.offsetY.value += dragAmount.y
                            }
                        }
                        .onGloballyPositioned { coordinates ->
                            item.state.currentRect = coordinates.boundsInWindow()
                        }
                ) {
                    Column(
                        horizontalAlignment = CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(text = item.translate,
                            textAlign = TextAlign.Center,
                            fontSize = fontSize,
                            maxLines = 1,
                            modifier = Modifier
                                .padding(horizontal = horizontalTextPadding, vertical = verticalTextPadding))
                    }
                }
        }
    }


}

@Composable
fun AutoResizeText(
    text: String,
    fontSizeRange: FontSizeRange,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    textAlign: TextAlign? = null,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
) {
    var fontSizeValue by remember { mutableStateOf(fontSizeRange.max.value) }
    var readyToDraw by remember { mutableStateOf(false) }

    Text(
        text = text,
        color = color,
        maxLines = maxLines,
        textAlign = textAlign,
        softWrap = softWrap,
        fontSize = fontSizeValue.sp,
        onTextLayout = {
            if (it.didOverflowHeight && !readyToDraw) {
                val nextFontSizeValue = fontSizeValue - fontSizeRange.step.value
                if (nextFontSizeValue <= fontSizeRange.min.value) {
                    // Reached minimum, set minimum font size and it's readToDraw
                    fontSizeValue = fontSizeRange.min.value
                    readyToDraw = true
                } else {
                    // Text doesn't fit yet and haven't reached minimum text range, keep decreasing
                    fontSizeValue = nextFontSizeValue
                }
            } else {
                // Text fits before reaching the minimum, it's readyToDraw
                readyToDraw = true
            }
        },
        modifier = modifier.drawWithContent { if (readyToDraw) drawContent() }
    )
}

data class FontSizeRange(
    val min: TextUnit,
    val max: TextUnit,
    val step: TextUnit = DEFAULT_TEXT_STEP,
) {
    init {
        require(min < max) { "min should be less than max, $this" }
        require(step.value > 0) { "step should be greater than 0, $this" }
    }

    companion object {
        private val DEFAULT_TEXT_STEP = 1.sp
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