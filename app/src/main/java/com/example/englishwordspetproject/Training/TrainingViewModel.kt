package com.example.englishwordspetproject.Training

import androidx.annotation.FloatRange
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.text.AnnotatedString
import com.example.englishwordspetproject.data.viewModel.BaseViewModel

class TrainingViewModel: BaseViewModel() {
}

val exercises = listOf("Слово-перевод", "Найди пару", "Спринт", "Собери слово")

data class TrainingWordInformation(
    val translate: String,
    var targetRect: Rect = Rect(0f, 0f, 0f, 0f),
    var currentRect: Rect = Rect(0f, 0f, 0f, 0f)
) {

    public fun coincidence(): Boolean {
        return targetRect.overlaps(currentRect)
    }
}



val trainingWords = mapOf(
    "cat" to TrainingWordInformation("кошка"),
    "dog" to TrainingWordInformation("собака"),
    "frog" to TrainingWordInformation("лягушка"),
    "train" to TrainingWordInformation("поезд"),
    "umbrella" to TrainingWordInformation("зонт")
)