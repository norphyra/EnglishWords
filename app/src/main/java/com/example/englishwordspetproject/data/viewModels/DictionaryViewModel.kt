package com.example.englishwordspetproject.data.viewModels

import androidx.annotation.StringRes
import com.example.englishwordspetproject.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class DictionaryViewModel: BaseViewModel() {
    private val _selectedItem = MutableStateFlow(Section(R.string.my_dictionary))
    public val selectedItem: StateFlow<Section> = _selectedItem

    private val _expanded= MutableStateFlow(false)
    public val expanded: StateFlow<Boolean> = _expanded

    open public fun selectItem(item: Section) {
        _selectedItem.value = item
    }

    open public fun isExpand(isExpand: Boolean) {
        _expanded.value = isExpand
    }

}

data class Section(@StringRes val sectionName: Int)

val sections = listOf(Section(R.string.my_dictionary), Section(R.string.my_sets))