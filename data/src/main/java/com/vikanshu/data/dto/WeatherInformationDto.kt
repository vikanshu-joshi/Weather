package com.vikanshu.data.dto

import com.google.gson.annotations.SerializedName
import com.vikanshu.data.model.CurrentWeatherInformation
import com.vikanshu.data.resource.Utility
import java.util.Calendar
import java.util.Date
import java.util.TimeZone


data class WeatherInformationDto(
    @SerializedName("coord") var coord: Coord? = Coord(),
    @SerializedName("weather") var weather: ArrayList<Weather> = arrayListOf(),
    @SerializedName("base") var base: String? = null,
    @SerializedName("main") var main: Main? = Main(),
    @SerializedName("visibility") var visibility: Int? = null,
    @SerializedName("wind") var wind: Wind? = Wind(),
    @SerializedName("clouds") var clouds: Clouds? = Clouds(),
    @SerializedName("dt") var dt: Int? = null,
    @SerializedName("sys") var sys: Sys? = Sys(),
    @SerializedName("timezone") var timezone: Int? = null,
    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("cod") var cod: Int? = null
) {
    fun toCurrentWeatherInformation(): CurrentWeatherInformation {
        return CurrentWeatherInformation(
            timestamp = Calendar.getInstance().apply { timeInMillis = System.currentTimeMillis() + (timezone!! * 1000) },
            description = if (weather.isEmpty()) "" else weather.first().main ?: "",
            name = name ?: "",
            iconUrl = if (weather.isEmpty()) "" else Utility.getImageIconUrl(weather.first().icon),
            temp = main?.temp ?: 0.0,
            feelsLike = main?.feelsLike ?: 0.0,
            humidity = main?.humidity ?: 0,
            pressure = main?.pressure ?: 0,
            sunrise = Calendar.getInstance().apply { timeInMillis = sys!!.sunrise!! * 1000L },
            sunset = Calendar.getInstance().apply { timeInMillis = sys!!.sunset!! * 1000L },
            visibility = visibility ?: 0,
            windDeg = wind?.deg ?: 0,
            windSpeed = wind?.speed ?: 0.0,
            timezone = timezone!!
        )
    }
}