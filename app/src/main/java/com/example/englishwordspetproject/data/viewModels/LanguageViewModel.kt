package com.example.englishwordspetproject.data.viewModels

import androidx.annotation.DrawableRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.englishwordspetproject.data.viewModels.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class LanguageViewModel: BaseViewModel() {

}

data class Language(val name: String, @DrawableRes val icon: Int)