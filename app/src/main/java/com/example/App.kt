package com.example

import android.app.Application
import timber.log.Timber

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        setTimber()
    }
    private fun setTimber(){
        Timber.plant(Timber.DebugTree())
    }
}