package com.example.englishwordspetproject.Training.exercises.find_pair

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateRect
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
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
import com.example.englishwordspetproject.R
import com.example.englishwordspetproject.Training.TrainingViewModel
import com.example.englishwordspetproject.Training.TrainingWordsInfo
import com.example.englishwordspetproject.ui.theme.DraggableItemColor
import com.example.englishwordspetproject.ui.theme.EnglishWordspetProjectTheme
import kotlin.math.roundToInt
import kotlin.random.Random
import kotlin.random.nextInt

@OptIn(ExperimentalFoundationApi::class, ExperimentalLayoutApi::class)
@Composable
fun FindPair() {

    val trainingViewModel: TrainingViewModel = viewModel()

    val coroutineScope = rememberCoroutineScope()

    val isFinished by trainingViewModel.isFinished.collectAsState(false)

    var maxWidthSurface by remember {
        mutableStateOf(0.dp)
    }

    val words = trainingViewModel.trainingWords

    Column (modifier = Modifier
        .fillMaxSize(),
        horizontalAlignment = CenterHorizontally
    ) {

        val height = listOf(95.dp, 135.dp, 155.dp, 90.dp, 115.dp, 90.dp)

        LazyVerticalStaggeredGrid(columns = StaggeredGridCells.Adaptive(150.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalItemSpacing = 20.dp,
            contentPadding = PaddingValues(vertical = 10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
        ) {
            itemsIndexed(words.entries.toList()
            ) {index, item ->

//                val height = Random.nextInt(IntRange(80, 120)).dp

                Surface(shape = RoundedCornerShape(10.dp),
                    shadowElevation = 8.dp,
                    modifier = Modifier.height(height[index]),
                    color = Color(0xFFFFF8E8)) {
                    Column(horizontalAlignment = CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(),
                    ) {

                        val surfaceTransition = updateTransition(item.value.state.surfaceState, label = "surface state")

                        val colorAnimation by surfaceTransition.animateColor(label = "color animation",
                            transitionSpec = { tween(300) }
                        ) { state ->
                            when (state.value) {
                                SurfaceState.Normal -> Color.White
                                SurfaceState.Expanded -> Color(0xFFFFF8E8)
                            }
                        }

                        Text(
                            text = item.key,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = height[index] * 0.1f)
                        )

                        Canvas(modifier = Modifier
                            .fillMaxWidth(0.7f)
                            .height(height[index] * 0.3f)
                            .onGloballyPositioned {coordinates ->
                                item.value.state.targetRect = coordinates.boundsInWindow()
                            }
                            .border(1.dp, colorAnimation, RoundedCornerShape(20.dp))
                        ) {
                            drawRoundRect(
                                color = Color.White,
                                size = Size(size.width, size.height),
                                cornerRadius = CornerRadius(
                                    x = 40f,
                                    y = 40f
                                )
                            )
                        }
                    }
                }
            }
        }

        ListDraggableWords(words = words.values.toList(),
            trainingViewModel = trainingViewModel,
            horizontalTextPadding = 10.dp,
            verticalTextPadding = 4.dp,
            verticalSpace = 10.dp,
            horizontalSpace = 10.dp,
            itemHeight = height.min() * 0.3f,
            maxWidthSurface = maxWidthSurface)

        Row(horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
                .height(90.dp)
        ) {
            AnimatedVisibility(visible = isFinished
            ) {
                Button(onClick = { /*TODO*/ },
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier.fillMaxWidth(0.6f),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primaryContainer,
                        contentColor = MaterialTheme.colorScheme.onPrimaryContainer)
                ) {
                    Text(text = stringResource(id = R.string.continue_button),
                        fontSize = 28.sp,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center)
                }
            }
        }

    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ListDraggableWords(words: List<TrainingWordsInfo>,
                       trainingViewModel: TrainingViewModel,
                       horizontalTextPadding: Dp,
                       verticalTextPadding: Dp,
                       verticalSpace: Dp,
                       horizontalSpace: Dp,
                       itemHeight: Dp,
                       maxWidthSurface: Dp
) {
    FlowRow(modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 20.dp, top = 10.dp, start = 10.dp, end = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(horizontalSpace),
        verticalAlignment = CenterVertically
    ) {

        words.forEachIndexed { index, item ->

            DraggableItem(
                word = item,
                trainingViewModel = trainingViewModel,
                horizontalTextPadding = horizontalTextPadding,
                verticalTextPadding = verticalTextPadding,
                verticalSpace = verticalSpace,
                itemHeight = itemHeight,
                maxWidthSurface = maxWidthSurface
            )
        }
    }
}

@Composable
fun DraggableItem(word: TrainingWordsInfo,
                  trainingViewModel: TrainingViewModel,
                  horizontalTextPadding: Dp,
                  verticalTextPadding: Dp,
                  verticalSpace: Dp,
                  itemHeight: Dp,
                  maxWidthSurface: Dp
){

    val surfaceTransition = updateTransition(word.state.surfaceState, label = "surface state")

    val elevationTransition = updateTransition(word.state.isDragging, label = "drag state")

    val rectAnimation by surfaceTransition.animateRect(label = "animate rect",
        transitionSpec = { tween(500) }) { state ->
        when(state.value) {
            SurfaceState.Normal -> Rect(0f, 0f, 0f, 0f)
            SurfaceState.Expanded -> Rect(Offset(word.state.targetRect.topLeft.x, word.state.targetRect.topLeft.y),
                Size(word.state.targetRect.width, word.state.targetRect.height)
            )
        }
    }

    val roundedCornerRadiusAnimation by surfaceTransition.animateDp(label = "rounded corner radius",
        transitionSpec = { tween(100, 500) })
    {state ->
        when (state.value) {
            SurfaceState.Normal -> 40.dp
            SurfaceState.Expanded -> 0.dp
        } }

    val elevation by elevationTransition.animateDp(label = "elevation")
    { isDragging ->
        if (isDragging.value) 8.dp else 0.dp
    }

    val colorAnimation by surfaceTransition.animateColor(label = "color animation",
        transitionSpec = { tween(100, 500) }
    ) { state ->
        when (state.value) {
            SurfaceState.Normal -> DraggableItemColor
            SurfaceState.Expanded -> MaterialTheme.colorScheme.surface
        }
    }

    Surface(shape = RoundedCornerShape(40.dp),
        color = Color(0xFFFFF8E8),
        shadowElevation = elevation,
        modifier = Modifier
            .offset {
                IntOffset(
                    word.state.offsetX.value.roundToInt(),
                    word.state.offsetY.value.roundToInt()
                )
            }
            .zIndex(word.state.zIndex)
            .padding(top = verticalSpace)
            .height(itemHeight)
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = {
                        if (word.state.isCoincidence.value) {
                            return@detectDragGestures
                        }

                        word.state.startDrag(trainingViewModel)

                    },
                    onDragEnd = {
                        word.state.coincidence()
                        if (word.state.isCoincidence.value) {

                            trainingViewModel.checkFinished()
                            word.state.surfaceState.value = SurfaceState.Expanded

                            word.state.cancelDrag()

                            word.state.shiftToTargetRectCenter()
                        }
                    }) { change, dragAmount ->
                    if (word.state.isCoincidence.value) {
                        word.state.zIndex = 0f
                        word.state.surfaceState.value = SurfaceState.Expanded
                        return@detectDragGestures
                    }
                    change.consume()
                    word.state.offsetX.value += dragAmount.x
                    word.state.offsetY.value += dragAmount.y
                }
            }
            .onGloballyPositioned { coordinates ->
                word.state.currentRect = coordinates.boundsInWindow()
            }
            .drawWithCache {
                onDrawBehind {
                    drawRoundRect(
                        color = Color(0xFFFFF8E8),
                        size = rectAnimation.size,
                        topLeft = word.state.calculateOffset(rectAnimation = rectAnimation),
                        cornerRadius = CornerRadius(
                            x = roundedCornerRadiusAnimation.value * 1.5f,
                            y = roundedCornerRadiusAnimation.value * 1.5f
                        )
                    )
                }
            }
    ) {
        Column(
            horizontalAlignment = CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            AutoResizeText(text = word.translate,
                textAlign = TextAlign.Center,
                fontSizeRange = FontSizeRange(
                    min = 5.sp,
                    max = 20.sp
                ),
                maxLines = 1,
                modifier = Modifier
                    .padding(horizontal = horizontalTextPadding, vertical = verticalTextPadding))

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

@Preview(device = Devices.PHONE, showBackground = true, name = "Phone", showSystemUi = true)
//@Preview(device = Devices.TABLET, showBackground = true, name = "TABLET")
//@Preview(device = Devices.DESKTOP, showBackground = true, name = "DESKTOP")
@Composable
fun FindPairPreview() {
    EnglishWordspetProjectTheme {
        BoxWithConstraints(modifier = Modifier.background(color = MaterialTheme.colorScheme.surface)) {
            FindPair()
        }
    }
}