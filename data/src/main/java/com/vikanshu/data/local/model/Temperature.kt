package com.vikanshu.data.local.model

import com.google.gson.Gson

data class Temperature(
    val tempC: Int,
    val tempF: Double,
    val feelsLikeC: Int,
    val feelsLikeF: Double
)  {
    fun toJson(): String {
        return Gson().toJson(this)
    }

    companion object {
        fun fromJson(json: String): Temperature {
            return Gson().fromJson(json, Temperature::class.java)
        }
    }
}
