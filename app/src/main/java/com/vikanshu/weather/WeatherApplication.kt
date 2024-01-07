package com.vikanshu.weather

import android.app.Application
import com.vikanshu.data.di.dataModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WeatherApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@WeatherApplication)
            modules(appModule, dataModule)
        }
    }

}