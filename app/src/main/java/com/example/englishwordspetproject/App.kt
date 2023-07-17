package com.example.englishwordspetproject

import android.app.Application
import com.example.englishwordspetproject.DI.AppComponent
import com.example.englishwordspetproject.DI.DaggerAppComponent

class App: Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }
}