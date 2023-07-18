package com.example.englishwordspetproject.DI

import android.content.Context
import com.example.englishwordspetproject.App
import com.example.englishwordspetproject.MainActivity
import dagger.Component

@Component
interface AppComponent {

    fun injectMainActivity(mainActivity: MainActivity)

}

val Context.appComponent: AppComponent
    get() = when(this) {
        is App -> appComponent
        else -> this.applicationContext.appComponent
    }