package com.vikanshu.data.dto

import com.google.gson.annotations.SerializedName
import com.vikanshu.data.model.CurrentWeatherInformation
import com.vikanshu.data.resource.Utility
import java.util.Calendar


data class ForecastWeather(

    @SerializedName("dt") var dt: Int? = null,
    @SerializedName("main") var main: Main? = Main(),
    @SerializedName("weather") var weather: ArrayList<Weather> = arrayListOf(),
    @SerializedName("clouds") var clouds: Clouds? = Clouds(),
    @SerializedName("wind") var wind: Wind? = Wind(),
    @SerializedName("visibility") var visibility: Int? = null,
    @SerializedName("pop") var pop: Int? = null,
    @SerializedName("sys") var sys: Sys? = Sys(),
    @SerializedName("dt_txt") var dtTxt: String? = null

) {
    fun toCurrentWeatherInformation(): CurrentWeatherInformation {
        return CurrentWeatherInformation(
            timestamp = Calendar.getInstance().apply { dt?.let { timeInMillis = it.toLong() } },
            description = if (weather.isEmpty()) "" else weather.first().description ?: "",
            name = "",
            iconUrl = if (weather.isEmpty()) "" else Utility.getImageIconUrl(weather.first().icon),
            temp = main?.temp ?: 0.0,
            feelsLike = main?.feelsLike ?: 0.0,
            humidity = main?.humidity ?: 0,
            pressure = main?.pressure ?: 0,
            sunrise = Calendar.getInstance()
                .apply { sys?.sunrise?.let { timeInMillis = it.toLong() } },
            sunset = Calendar.getInstance()
                .apply { sys?.sunset?.let { timeInMillis = it.toLong() } },
            visibility = visibility ?: 0,
            windDeg = wind?.deg ?: 0,
            windSpeed = wind?.speed ?: 0.0,
            timezone = 0
        )
    }
}