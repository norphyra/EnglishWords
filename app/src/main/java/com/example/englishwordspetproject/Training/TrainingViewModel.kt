package com.example.englishwordspetproject.Training

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.englishwordspetproject.Training.exercises.find_pair.DraggableItemState
import com.example.englishwordspetproject.Training.exercises.find_pair.SurfaceState
import com.example.englishwordspetproject.data.viewModel.BaseViewModel

class TrainingViewModel: BaseViewModel() {

    val trainingWords = mapOf(
        "cat" to TrainingWordsInfo("кошка", DraggableItemState()),
        "dog" to TrainingWordsInfo("собака", DraggableItemState()),
        "frog" to TrainingWordsInfo("лягушка", DraggableItemState()),
        "train" to TrainingWordsInfo("поезд", DraggableItemState()),
        "umbrella" to TrainingWordsInfo("зонт", DraggableItemState()),
        "aaaaaaaaaaaaaaaa" to TrainingWordsInfo("aaaaaaaaaaaaaaaa", DraggableItemState())
    )

}

class TrainingWordsInfo(
    val translate: String,
    val state: DraggableItemState
)

val exercises = listOf("Слово-перевод", "Найди пару", "Спринт", "Собери слово")
