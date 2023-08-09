package com.example.englishwordspetproject.Training

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import com.example.englishwordspetproject.Training.exercises.find_pair.DraggableItemState
import com.example.englishwordspetproject.data.viewModel.BaseViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class TrainingViewModel: BaseViewModel() {

    private val _isFinished = MutableStateFlow(false)
    public val isFinished = _isFinished.asStateFlow()

    public val trainingWords = mapOf(
        "cat" to TrainingWordsInfo("кошка", DraggableItemState()),
        "dog" to TrainingWordsInfo("собака", DraggableItemState()),
        "frog" to TrainingWordsInfo("лягушка", DraggableItemState()),
        "aaaaaaaaaa" to TrainingWordsInfo("aaaaaaaaaa", DraggableItemState()),
        "frog_" to TrainingWordsInfo("лягушка", DraggableItemState()),
        "aaaaaaaaaa_" to TrainingWordsInfo("aaaaaaaaaa", DraggableItemState())
    )

    public fun checkFinished() {
        viewModelScope.launch {
            _isFinished.value = trainingWords.values.all {item ->
                item.state.isCoincidence.value}
        }
    }

    public fun zIndex() = trainingWords.values.maxBy { it.state.zIndex }.state.zIndex + 1

}

class TrainingWordsInfo(
    val translate: String,
    val state: DraggableItemState
)

val exercises = listOf("Слово-перевод", "Найди пару", "Спринт", "Собери слово")
