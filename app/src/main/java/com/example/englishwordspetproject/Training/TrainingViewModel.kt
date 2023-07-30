package com.example.englishwordspetproject.Training

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.geometry.Rect
import com.example.englishwordspetproject.data.viewModel.BaseViewModel

class TrainingViewModel: BaseViewModel() {
}

val exercises = listOf("Слово-перевод", "Найди пару", "Спринт", "Собери слово")

data class TrainingWordInformation(
    val translate: String,
    var targetRect: Rect = Rect(0f, 0f, 0f, 0f),
    var currentRect: Rect = Rect(0f, 0f, 0f, 0f),
    var isCoincidence : MutableState<Boolean> = mutableStateOf(false)
) {

    public fun coincidence() {
        isCoincidence.value = (targetRect.top < currentRect.top) && (targetRect.bottom > currentRect.bottom)
    }
}



val trainingWords = mapOf(
    "cat" to TrainingWordInformation("кошка"),
    "dog" to TrainingWordInformation("собака"),
    "frog" to TrainingWordInformation("лягушка"),
    "train" to TrainingWordInformation("поезд"),
    "umbrella" to TrainingWordInformation("зонт")
)