package com.vikanshu.data.local.model

import com.vikanshu.data.dto.Current
import java.text.SimpleDateFormat
import com.vikanshu.data.dto.forecast.Forecastday as Day
import java.util.Date

data class ForecastDay(
    val date: Date,
    val weatherDescription: String,
    val weatherIcon: String,
    val tempHighC: Double,
    val tempHighF: Double,
    val tempLowC: Double,
    val tempLowF: Double,
    val hourly: List<ForecastHour>
) {


    companion object {
        fun fromForecastDayDto(day: Day?): ForecastDay {
            return ForecastDay(
                date = Date(SimpleDateFormat("yyyy-mm-dd HH:mm").parse(day?.date ?: "1907-01-01 00:00").time),
                weatherDescription = day?.day?.condition?.text ?: "",
                weatherIcon = if (day?.day?.condition?.icon == null) "" else "https:" + day?.day?.condition?.icon ?: "",
                tempHighC = day?.day?.maxtempC ?: 0.0,
                tempHighF = day?.day?.maxtempF ?: 0.0,
                tempLowC = day?.day?.mintempC ?: 0.0,
                tempLowF = day?.day?.mintempF ?: 0.0,
                hourly = day?.hour?.map {
                    ForecastHour.fromForecastHourDto(it)
                } ?: emptyList()
            )
        }
    }

}
