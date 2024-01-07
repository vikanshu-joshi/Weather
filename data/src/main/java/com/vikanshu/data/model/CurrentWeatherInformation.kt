package com.vikanshu.data.model

import java.util.Calendar

data class CurrentWeatherInformation(
    val timestamp: Calendar,
    var name: String,
    val iconUrl: String,
    val description: String,
    val temp: Double,
    val sunrise: Calendar,
    val sunset: Calendar,
    val windSpeed: Double,
    val windDeg: Int,
    val humidity: Int,
    val pressure: Int,
    val feelsLike: Double,
    val visibility: Int
)
