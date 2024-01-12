package com.vikanshu.data.local.model

import com.google.gson.Gson
import com.vikanshu.data.dto.Current

data class Temperature(
    val tempC: Double,
    val tempF: Double,
    val feelsLikeC: Double,
    val feelsLikeF: Double
) {
    fun toJson(): String {
        return Gson().toJson(this)
    }

    companion object {
        fun fromJson(json: String): Temperature {
            return Gson().fromJson(json, Temperature::class.java)
        }

        fun fromCurrentDto(current: Current?): Temperature {
            return Temperature(
                tempC = current?.tempC ?: 0.0,
                tempF = current?.tempF ?: 0.0,
                feelsLikeC = current?.feelslikeC ?: 0.0,
                feelsLikeF = current?.feelslikeF ?: 0.0
            )
        }
    }
}
