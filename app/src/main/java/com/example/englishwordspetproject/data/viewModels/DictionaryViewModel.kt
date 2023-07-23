package com.example.englishwordspetproject.data.viewModels

import androidx.annotation.StringRes
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.ui.graphics.Color
import com.example.englishwordspetproject.R
import com.example.englishwordspetproject.ui.theme.in_progress_words_progress_color
import com.example.englishwordspetproject.ui.theme.learned_words_progress_color
import com.example.englishwordspetproject.ui.theme.new_words_progress_color
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class DictionaryViewModel: BaseViewModel() {
    private val _selectedCardTitle = MutableStateFlow(CardTitle.AllWorlds)
    public val selectedTitle: StateFlow<CardTitle> = _selectedCardTitle

    private val _expanded= MutableStateFlow(false)
    public val expanded: StateFlow<Boolean> = _expanded

    @OptIn(ExperimentalMaterial3Api::class)
    public var sheetState: SheetState = SheetState(true)

    open public fun selectedTitle(cardTitle: CardTitle) {
        _selectedCardTitle.value = cardTitle
    }

    open public fun isExpand(isExpand: Boolean) {
        _expanded.value = isExpand
        val a = ""
    }

}

data class Section(@StringRes val sectionName: Int)

data class WordsWithTranslate(val original: String, val translate: String, val status: WordStatus)

val sections = listOf(Section(R.string.my_dictionary), Section(R.string.my_sets))

val wordsMap = mapOf<String, List<String>>(
    "A" to listOf("Aaaaaaaa", "Aaaaaaaa", "Aaaaaaaa"),
    "B" to listOf("Bbbbbbbb", "Bbbbbbbb", "Bbbbbbbb"),
    "C" to listOf("Cccccccc", "Cccccccc", "Cccccccc")
)

val wordsStatisticInDictionary = mapOf<@receiver:StringRes Int, Int>(
    R.string.new_words to 120,
    R.string.in_progress_word to 34,
    R.string.learned_word to 394,
    R.string.all_words to 548
)

val words = listOf(WordsWithTranslate("cat", "кошка", WordStatus.New),
    WordsWithTranslate("dog", "собака", WordStatus.New),
    WordsWithTranslate("fish", "рыба", WordStatus.InProgress),
    WordsWithTranslate("illusion", "иллюзия", WordStatus.Learned))

enum class WordStatus(val color: Color, @StringRes val stringRes: Int) {
    New(new_words_progress_color, R.string.new_word),
    InProgress(in_progress_words_progress_color, R.string.in_progress_word),
    Learned(learned_words_progress_color, R.string.learned_word)
}

enum class CardTitle(@StringRes val title: Int) {
    AllWorlds(R.string.all_words_title),
    Nouns(R.string.nouns_title),
    Verbs(R.string.verbs_title),
    Adjectives(R.string.adjectives_title),
    Adverb(R.string.adverb_title),
    Numerals(R.string.numerals_title)
}



