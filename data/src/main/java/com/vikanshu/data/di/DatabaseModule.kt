package com.vikanshu.data.di

import androidx.room.Room
import com.vikanshu.data.local.dao.LocationDao
import com.vikanshu.data.local.db.WeatherDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {

    single<WeatherDatabase> {
        Room.databaseBuilder(
            context = androidContext(),
            WeatherDatabase::class.java,
            "WeatherDB"
        ).build()
    }

    single<LocationDao> {
        get<WeatherDatabase>().locationDao()
    }
}