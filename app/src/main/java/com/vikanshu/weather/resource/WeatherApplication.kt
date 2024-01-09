package com.vikanshu.weather.resource

import android.app.Application
import com.vikanshu.core_ui.di.commonModule
import com.vikanshu.data.di.dataModule
import com.vikanshu.weather.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WeatherApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@WeatherApplication)
            modules(appModule, dataModule, commonModule)
        }
    }

}