package com.example

import android.app.Application
import com.example.common.di.NetworkModule
import com.example.myweather.di.MainPageModule
import com.example.weekly_weather.di.FiveDaysModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import timber.log.Timber

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        setTimber()
        setupKoin()
    }
    private fun setTimber(){
        Timber.plant(Timber.DebugTree())
    }

    private fun setupKoin(){
        stopKoin()
        startKoin{
            androidContext(this@App)
            modules(
                listOf(
                    NetworkModule().create(),
                    MainPageModule.create(),
                    FiveDaysModule.create()
                )
            )
        }
    }
}