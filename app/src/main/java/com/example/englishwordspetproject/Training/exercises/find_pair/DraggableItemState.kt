package com.example.englishwordspetproject.Training.exercises.find_pair

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.unit.Dp

@Composable
fun rememberDraggableItemState() = DraggableItemState()

class DraggableItemState {

    public var targetRect: Rect = Rect(0f, 0f, 0f, 0f)
    public var currentRect: Rect = Rect(0f, 0f, 0f, 0f)
    public var offsetX: MutableState<Float> = mutableStateOf(0f)
    public var offsetY: MutableState<Float> = mutableStateOf(0f)
    public val isCoincidence: MutableState<Boolean> = mutableStateOf(false)
    public var dragState: MutableState<DragState> = mutableStateOf(DragState.DRAG_CANCEL)
    public var surfaceState: MutableState<SurfaceState> = mutableStateOf(SurfaceState.Normal)
    public var zIndex: Float = if (dragState.value != DragState.DRAG_CANCEL) 1f else 0f
    public var isDragging: MutableState<Boolean> = mutableStateOf(false)


    public fun coincidence() {
        isCoincidence.value = (targetRect.top < currentRect.top) && (targetRect.bottom > currentRect.bottom)
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