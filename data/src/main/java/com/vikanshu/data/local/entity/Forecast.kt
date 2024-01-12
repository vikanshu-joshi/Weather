package com.vikanshu.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.Gson
import com.vikanshu.data.dto.forecast.ResponseForecastWeather
import com.vikanshu.data.local.model.ForecastDay

@Entity
data class Forecast(
    val location: Location,
    val weatherDescription: String,
    val weatherIcon: String,
    val tempC: Double,
    val tempF: Double,
    val forecastDays: List<ForecastDay>
) {

    @PrimaryKey
    var id = location.name

    fun toJson() = Gson().toJson(this)

    companion object {

        fun fromJson(data: String) = Gson().fromJson(data, Forecast::class.java)

        fun fromForecastResponse(forecastDay: ResponseForecastWeather?): Forecast {
            return Forecast(
                location = Location.fromLocationDto(forecastDay?.locationDto),
                weatherDescription = forecastDay?.current?.condition?.text ?: "",
                weatherIcon = forecastDay?.current?.condition?.icon ?: "",
                tempC = forecastDay?.current?.tempC ?: 0.0,
                tempF = forecastDay?.current?.tempF ?: 0.0,
                forecastDays = forecastDay?.forecast?.forecastday?.map {
                    ForecastDay.fromForecastDayDto(it)
                } ?: emptyList()
            )
        }
    }
}
