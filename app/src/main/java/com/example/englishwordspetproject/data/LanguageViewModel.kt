package com.example.englishwordspetproject.data

import androidx.annotation.DrawableRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class LanguageViewModel: ViewModel() {
    private val _showBottomSheet = MutableStateFlow(false)

    public val showBottomSheet: StateFlow<Boolean> = _showBottomSheet

    public fun showBottomSheet() {
        _showBottomSheet.value = true
    }

    public fun hideBottomSheet() {
        _showBottomSheet.value = false
    }
}

class ViewModelFactory @Inject constructor(): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return super.create(modelClass)
    }

}

data class Language(val name: String, @DrawableRes val icon: Int)