package com.vikanshu.data.di

import android.content.Context
import androidx.room.Room
import com.vikanshu.data.local.dao.CurrentWeatherDao
import com.vikanshu.data.local.dao.ForecastDao
import com.vikanshu.data.local.dao.LocationDao
import com.vikanshu.data.local.db.WeatherDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun providesLocationRepository(
        @ApplicationContext context: Context
    ): WeatherDatabase {
        return Room.databaseBuilder(
            context = context,
            WeatherDatabase::class.java,
            "WeatherDB"
        ).build()
    }

    @Provides
    @Singleton
    fun providesLocationDao(weatherDatabase: WeatherDatabase): LocationDao {
        return weatherDatabase.locationDao()
    }

    @Provides
    @Singleton
    fun providesCurrentWeatherDao(weatherDatabase: WeatherDatabase): CurrentWeatherDao {
        return weatherDatabase.currentWeatherDao()
    }

    @Provides
    @Singleton
    fun providesForecastDao(weatherDatabase: WeatherDatabase): ForecastDao {
        return weatherDatabase.forecastDao()
    }

}