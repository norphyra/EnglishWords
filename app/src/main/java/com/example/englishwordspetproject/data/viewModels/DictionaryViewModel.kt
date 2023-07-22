package com.example.englishwordspetproject.data.viewModels

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.ui.graphics.Color
import com.example.englishwordspetproject.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class DictionaryViewModel: BaseViewModel() {
    private val _selectedTitle = MutableStateFlow("")
    public val selectedTitle: StateFlow<String> = _selectedTitle

    private val _expanded= MutableStateFlow(false)
    public val expanded: StateFlow<Boolean> = _expanded

    @OptIn(ExperimentalMaterial3Api::class)
    public var sheetState: SheetState = SheetState(true)

    open public fun selectedTitle(title: String) {
        _selectedTitle.value = title
    }

    open public fun isExpand(isExpand: Boolean) {
        _expanded.value = isExpand
        val a = ""
    }

}

data class Section(@StringRes val sectionName: Int)

data class WordsWithTranslate(val word: String, val translate: String, val status: WordStatus)

val sections = listOf(Section(R.string.my_dictionary), Section(R.string.my_sets))

val wordsMap = mapOf<String, List<String>>(
    "A" to listOf("Aaaaaaaa", "Aaaaaaaa", "Aaaaaaaa"),
    "B" to listOf("Bbbbbbbb", "Bbbbbbbb", "Bbbbbbbb"),
    "C" to listOf("Cccccccc", "Cccccccc", "Cccccccc")
)

val wordsStatisticInDictionary = mapOf<@receiver:StringRes Int, Int>(
    R.string.new_word to 120,
    R.string.in_progress_word to 34,
    R.string.learned_word to 394,
    R.string.all_words to 548
)

val words = listOf(WordsWithTranslate("cat", "кошка", WordStatus.New),
    WordsWithTranslate("dog", "собака", WordStatus.New),
    WordsWithTranslate("fish", "рыба", WordStatus.InProgress),
    WordsWithTranslate("illusion", "иллюзия", WordStatus.Learned))

val cardTitles = listOf(R.string.all_words_title, R.string.nouns_title, R.string.verbs_title,
R.string.adjectives_title, R.string.adverb_title, R.string.numerals_title)

enum class WordStatus {
    New,
    InProgress,
    Learned
}

