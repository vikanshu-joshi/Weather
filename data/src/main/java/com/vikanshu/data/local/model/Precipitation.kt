package com.vikanshu.data.local.model

import com.google.gson.Gson
import com.vikanshu.data.dto.Current
import com.vikanshu.data.dto.forecast.Day

data class Precipitation(
    val precipitationInMm: Double,
    val precipitationInInches: Double
) {
    fun toJson(): String {
        return Gson().toJson(this)
    }

    companion object {
        fun fromJson(json: String): Precipitation {
            return Gson().fromJson(json, Precipitation::class.java)
        }
        fun fromCurrentDto(current: Current?): Precipitation {
            return Precipitation(
                precipitationInMm = current?.precipMm ?: 0.0,
                precipitationInInches = current?.precipIn ?: 0.0
            )
        }
        fun fromForecastDayDto(current: Day?): Precipitation {
            return Precipitation(
                precipitationInMm = current?.totalprecipMm ?: 0.0,
                precipitationInInches = current?.totalprecipIn ?: 0.0
            )
        }
    }
}
