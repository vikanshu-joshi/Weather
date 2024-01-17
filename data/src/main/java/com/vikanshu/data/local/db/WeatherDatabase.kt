package com.vikanshu.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.vikanshu.data.local.dao.CurrentWeatherDao
import com.vikanshu.data.local.dao.ForecastDao
import com.vikanshu.data.local.dao.LocationDao
import com.vikanshu.data.local.entity.CurrentWeather
import com.vikanshu.data.local.entity.Forecast
import com.vikanshu.data.local.entity.Location

@Database(
    entities = [Location::class, CurrentWeather::class, Forecast::class],
    version = 2
)
@TypeConverters(value = [WeatherTyeConverters::class])
abstract class WeatherDatabase: RoomDatabase() {
    abstract fun locationDao(): LocationDao
    abstract fun currentWeatherDao(): CurrentWeatherDao
    abstract fun forecastDao(): ForecastDao
}