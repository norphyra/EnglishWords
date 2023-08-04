package com.example.englishwordspetproject.Training

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import com.example.englishwordspetproject.Training.exercises.find_pair.DraggableItemState
import com.example.englishwordspetproject.data.viewModel.BaseViewModel
import kotlinx.coroutines.launch

class TrainingViewModel: BaseViewModel() {

    public val trainingWords = mapOf(
        "cat" to TrainingWordsInfo("кошка", DraggableItemState()),
        "dog" to TrainingWordsInfo("собака", DraggableItemState()),
        "frog" to TrainingWordsInfo("лягушка", DraggableItemState()),
        "train" to TrainingWordsInfo("поезд", DraggableItemState()),
        "umbrella" to TrainingWordsInfo("зонт", DraggableItemState()),
        "aaaaaaaaaaaaaaaa" to TrainingWordsInfo("aaaaaaaaaaaaaaaa", DraggableItemState()),
    )

    public fun zIndex() = trainingWords.values.maxBy { it.state.zIndex }.state.zIndex + 1

}

class TrainingWordsInfo(
    val translate: String,
    val state: DraggableItemState
)

val exercises = listOf("Слово-перевод", "Найди пару", "Спринт", "Собери слово")
