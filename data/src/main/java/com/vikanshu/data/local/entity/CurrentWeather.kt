package com.vikanshu.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.vikanshu.data.local.model.AirQuality
import com.vikanshu.data.local.model.Precipitation
import com.vikanshu.data.local.model.Pressure
import com.vikanshu.data.local.model.Temperature
import com.vikanshu.data.local.model.Visibility
import com.vikanshu.data.local.model.Wind
import java.util.Date

@Entity
data class CurrentWeather(
    val location: Location,
    val lastUpdated: Date,
    val temp: Temperature,
    val weatherDesc: String,
    val weatherIcon: String,
    val wind: Wind,
    val pressure: Pressure,
    val precipitation: Precipitation,
    val humidity: Int,
    val visibility: Visibility,
    val uvIndex: Int,
    val airQuality: AirQuality
) {
    @PrimaryKey
    var id = location.name
}