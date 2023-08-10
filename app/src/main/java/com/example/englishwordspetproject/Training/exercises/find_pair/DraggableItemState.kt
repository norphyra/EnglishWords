package com.example.englishwordspetproject.Training.exercises.find_pair

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.englishwordspetproject.Training.TrainingViewModel

@Composable
fun rememberDraggableItemState() = DraggableItemState()

class DraggableItemState() {

    public var targetRect: Rect = Rect(0f, 0f, 0f, 0f)
    public var currentRect: Rect = Rect(0f, 0f, 0f, 0f)
    public var offsetX: MutableState<Float> = mutableStateOf(0f)
    public var offsetY: MutableState<Float> = mutableStateOf(0f)
    public val isCoincidence: MutableState<Boolean> = mutableStateOf(false)
    public var dragState: MutableState<DragState> = mutableStateOf(DragState.DRAG_CANCEL)
    public var surfaceState: MutableState<SurfaceState> = mutableStateOf(SurfaceState.Normal)
    public var zIndex: Float = 0f
    public var isDragging: MutableState<Boolean> = mutableStateOf(false)


    public fun coincidence() {
        isCoincidence.value = (targetRect.center.y - currentRect.center.y < 0.5f) || (targetRect.center.x - currentRect.center.x < 0.5f)
//        isCoincidence.value = (targetRect.top < currentRect.top) && (targetRect.bottom > currentRect.bottom)
    }

    public fun calculateOffset(rectAnimation: Rect) = Offset(- (rectAnimation.width - currentRect.width) / 2,
        - (rectAnimation.height - currentRect.height) / 2)

    public fun shiftToTargetRectCenter() {
        offsetX.value += targetRect.center.x - currentRect.center.x
        offsetY.value += targetRect.center.y - currentRect.center.y
    }

    public fun startDrag(viewModel: TrainingViewModel) {
        zIndex = viewModel.zIndex()
        isDragging.value = true
    }

    public fun cancelDrag() {
        dragState.value = DragState.DRAG_CANCEL
        isDragging.value = false
    }
}

enum class DragState() {
    DRAG_START(),
    ON_DRAG(),
    DRAG_END(),
    DRAG_CANCEL()
}

enum class SurfaceState {
    Normal,
    Expanded
}