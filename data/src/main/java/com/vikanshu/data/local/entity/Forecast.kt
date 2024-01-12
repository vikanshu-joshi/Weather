package com.vikanshu.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vikanshu.data.dto.forecast.ResponseForecastWeather
import com.vikanshu.data.local.model.AirQuality
import com.vikanshu.data.local.model.ForecastDay
import java.text.SimpleDateFormat
import java.util.Date

@Entity
data class Forecast(
    val date: Date,
    val location: Location,
    val weatherDescription: String,
    val weatherIcon: String,
    val tempC: Double,
    val tempF: Double,
    val airQuality: AirQuality,
    val forecastDays: List<ForecastDay>
) {

    @PrimaryKey
    var id = location.name

    companion object {
        fun fromForecastResponse(forecastDay: ResponseForecastWeather?): Forecast {
            return Forecast(
                date = Date(
                    SimpleDateFormat("yyyy-mm-dd HH:mm").parse(
                        forecastDay?.locationDto?.localtime ?: "1907-01-01 00:00"
                    ).time
                ),
                location = Location.fromLocationDto(forecastDay?.locationDto),
                weatherDescription = forecastDay?.current?.condition?.text ?: "",
                weatherIcon = if (forecastDay?.current?.condition?.icon == null) "" else "https:" + forecastDay.current?.condition?.icon,
                tempC = forecastDay?.current?.tempC ?: 0.0,
                tempF = forecastDay?.current?.tempF ?: 0.0,
                airQuality = AirQuality.fromCurrentDto(forecastDay?.current),
                forecastDays = forecastDay?.forecast?.forecastday?.map {
                    ForecastDay.fromForecastDayDto(it)
                } ?: emptyList()
            )
        }
    }
}
