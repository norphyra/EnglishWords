package com.example.englishwordspetproject.data.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.englishwordspetproject.Dictionary.DictionaryViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

open class BaseViewModel: ViewModel() {
    private val _showBottomSheet = MutableStateFlow(false)
    public val showBottomSheet: StateFlow<Boolean> = _showBottomSheet

    open public fun showBottomSheet() {
        _showBottomSheet.value = true
    }

    open public fun hideBottomSheet() {
        _showBottomSheet.value = false
    }

}

class ViewModelFactory @Inject constructor(): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return when(modelClass) {
            DictionaryViewModel::class.java -> DictionaryViewModel()
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        } as T
    }

}