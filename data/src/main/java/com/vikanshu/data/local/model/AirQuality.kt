package com.vikanshu.data.local.model

import com.google.gson.Gson
import com.vikanshu.data.dto.Current

data class AirQuality(
    var co: Double,
    var no2: Double,
    var o3: Double,
    var so2: Double,
    var pm25: Double,
    var pm10: Double,
    var usEpaIndex: Int,
    var gbDefraIndex: Int
) {
    fun toJson(): String {
        return Gson().toJson(this)
    }

    companion object {
        fun fromJson(json: String): AirQuality {
            return Gson().fromJson(json, AirQuality::class.java)
        }

        fun fromCurrentDto(current: Current?): AirQuality {
            return AirQuality(
                co = current?.airQualityDto?.co ?: 0.0,
                no2 = current?.airQualityDto?.no2 ?: 0.0,
                o3 = current?.airQualityDto?.o3 ?: 0.0,
                so2 = current?.airQualityDto?.so2 ?: 0.0,
                pm25 = current?.airQualityDto?.pm25 ?: 0.0,
                pm10 = current?.airQualityDto?.pm10 ?: 0.0,
                usEpaIndex = current?.airQualityDto?.usEpaIndex ?: 0,
                gbDefraIndex = current?.airQualityDto?.gbDefraIndex ?: 0
            )
        }
    }
}