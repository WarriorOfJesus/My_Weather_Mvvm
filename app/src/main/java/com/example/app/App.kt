package com.example.app

import android.app.Application
import timber.log.Timber

class App: Application() {

    private fun setTimber(){
        Timber.plant(Timber.DebugTree())
    }
}