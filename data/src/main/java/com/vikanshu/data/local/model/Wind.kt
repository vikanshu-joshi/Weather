package com.vikanshu.data.local.model

import com.google.gson.Gson
import com.vikanshu.data.dto.Current
import com.vikanshu.data.dto.forecast.Day

data class Wind(
    val windMph: Double,
    val windKph: Double,
    val windDeg: Double,
    val windDir: String
) {
    fun toJson(): String {
        return Gson().toJson(this)
    }

    companion object {
        fun fromJson(json: String): Wind {
            return Gson().fromJson(json, Wind::class.java)
        }
        fun fromCurrentDto(current: Current?): Wind {
            return Wind(
                windDeg = current?.windDegree ?: 0.0,
                windDir = current?.windDir ?: "",
                windKph = current?.windKph ?: 0.0,
                windMph = current?.windMph ?: 0.0
            )
        }
    }
}
