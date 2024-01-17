package com.vikanshu.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.Gson
import com.vikanshu.data.local.model.AirQuality
import com.vikanshu.data.local.model.Precipitation
import com.vikanshu.data.local.model.Pressure
import com.vikanshu.data.local.model.Temperature
import com.vikanshu.data.local.model.Visibility
import com.vikanshu.data.local.model.Wind
import com.vikanshu.data.dto.current.ResponseCurrentWeather
import java.text.SimpleDateFormat
import java.util.Date

@Entity
data class CurrentWeather(
    val location: Location,
    val lastUpdated: Date,
    val timestamp: Date,
    val temp: Temperature,
    val weatherDesc: String,
    val weatherIcon: String,
    val wind: Wind,
    val pressure: Pressure,
    val precipitation: Precipitation,
    val humidity: Double,
    val visibility: Visibility,
    val uvIndex: Double,
    val airQuality: AirQuality,
    val lastCacheUpdateTime: Date
) {
    @PrimaryKey
    var id = location.name

    fun toJson() = Gson().toJson(this)

    fun isValidInCache(): Boolean {
        return System.currentTimeMillis() - lastCacheUpdateTime.time < 3600000
    }

    companion object {
        fun fromJson(json: String) = Gson().fromJson(json, CurrentWeather::class.java)
        fun fromCurrentWeatherDto(current: ResponseCurrentWeather): CurrentWeather {
            return CurrentWeather(
                location = Location.fromLocationDto(current.locationDto),
                lastUpdated = Date(current.current?.lastUpdatedEpoch?.toLong() ?: 0L),
                timestamp = Date(SimpleDateFormat("yyyy-mm-dd HH:mm").parse(current?.locationDto?.localtime ?: "1907-01-01 00:00").time),
                temp = Temperature.fromCurrentDto(current.current),
                weatherDesc = current.current?.condition?.text ?: "",
                weatherIcon = if (current.current?.condition?.icon == null) "" else "https:" + current.current?.condition?.icon,
                wind = Wind.fromCurrentDto(current.current),
                pressure = Pressure.fromCurrentDto(current.current),
                precipitation = Precipitation.fromCurrentDto(current.current),
                humidity = current.current?.humidity ?: 0.0,
                visibility = Visibility.fromCurrentDto(current.current),
                uvIndex = current.current?.uv ?: 0.0,
                airQuality = AirQuality.fromCurrentDto(current.current),
                lastCacheUpdateTime = Date(System.currentTimeMillis())
            )
        }
    }
}