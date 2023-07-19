package com.example.englishwordspetproject.data.viewModels

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.ui.graphics.Color
import com.example.englishwordspetproject.R
import com.example.englishwordspetproject.ui.theme.in_progress_icon_color
import com.example.englishwordspetproject.ui.theme.learned_icon_color
import com.example.englishwordspetproject.ui.theme.new_word_icon_color
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class DictionaryViewModel: BaseViewModel() {
    private val _selectedItem = MutableStateFlow(Section(R.string.my_dictionary))
    public val selectedItem: StateFlow<Section> = _selectedItem

    private val _expanded= MutableStateFlow(false)
    public val expanded: StateFlow<Boolean> = _expanded

    @OptIn(ExperimentalMaterial3Api::class)
    public var sheetState: SheetState = SheetState(true)

    open public fun selectItem(item: Section) {
        _selectedItem.value = item
    }

    open public fun isExpand(isExpand: Boolean) {
        _expanded.value = isExpand
        val a = ""
    }

}

data class Section(@StringRes val sectionName: Int)
data class WordsStatistic(val count: Int)

data class WordsWithTranslate(val word: String, val translate: String, val status: WordStatus)

val sections = listOf(Section(R.string.my_dictionary), Section(R.string.my_sets))

val wordsMap = mapOf<String, List<String>>(
    "A" to listOf("Aaaaaaaa", "Aaaaaaaa", "Aaaaaaaa"),
    "B" to listOf("Bbbbbbbb", "Bbbbbbbb", "Bbbbbbbb"),
    "C" to listOf("Cccccccc", "Cccccccc", "Cccccccc")
)

val wordsStatisticInDictionary = mapOf<@receiver:StringRes Int, WordsStatistic>(
    R.string.new_word to WordsStatistic(120),
    R.string.in_progress_word to WordsStatistic(34),
    R.string.learned_word to WordsStatistic(394)
)

val words = listOf(WordsWithTranslate("cat", "кошка", WordStatus.New),
    WordsWithTranslate("dog", "собака", WordStatus.New),
    WordsWithTranslate("fish", "рыба", WordStatus.InProgress),
    WordsWithTranslate("illusion", "иллюзия", WordStatus.Learned))

enum class WordStatus {
    New,
    InProgress,
    Learned
}

