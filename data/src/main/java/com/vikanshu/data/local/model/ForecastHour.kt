package com.vikanshu.data.local.model

import com.google.gson.Gson
import com.vikanshu.data.dto.forecast.Hour
import java.text.SimpleDateFormat
import java.util.Date

data class ForecastHour(
    val time: Date,
    val tempC: Double,
    val tempF: Double,
    val weatherDescription: String,
    val weatherIcon: String,
) {

    fun toJson() = Gson().toJson(this)

    companion object {

        fun fromJson(data: String) = Gson().fromJson(data, ForecastHour::class.java)

        fun fromForecastHourDto(hour: Hour?): ForecastHour {
            return ForecastHour(
                time = Date(SimpleDateFormat("yyyy-mm-dd HH:mm").parse(hour?.time ?: "1907-01-01 00:00").time),
                tempC = hour?.tempC ?: 0.0,
                tempF = hour?.tempF ?: 0.0,
                weatherDescription = hour?.condition?.text ?: "",
                weatherIcon = if (hour?.condition?.icon == null) "" else "https:" + hour?.condition?.icon
            )
        }
    }
}
