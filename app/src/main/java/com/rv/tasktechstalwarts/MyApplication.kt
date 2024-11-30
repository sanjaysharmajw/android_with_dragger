package com.rv.tasktechstalwarts

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.internal.lifecycle.HiltViewModelMap

@HiltAndroidApp
class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
      //  MyPreferences.init(this)
        instance = this
    }

    companion object {
        lateinit var instance: MyApplication
    }
}