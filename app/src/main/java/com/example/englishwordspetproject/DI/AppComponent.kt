package com.example.englishwordspetproject.DI

import android.content.Context
import com.example.englishwordspetproject.App
import com.example.englishwordspetproject.MainActivity
import com.example.englishwordspetproject.data.LanguageViewModel
import com.example.englishwordspetproject.data.ViewModelFactory
import dagger.Component
import dagger.Provides

@Component
interface AppComponent {

    fun injectMainActivity(mainActivity: MainActivity)

}

val Context.appComponent: AppComponent
    get() = when(this) {
        is App -> appComponent
        else -> this.applicationContext.appComponent
    }