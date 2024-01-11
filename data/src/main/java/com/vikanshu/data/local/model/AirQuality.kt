package com.vikanshu.data.local.model

import com.google.gson.Gson

data class AirQuality(
    var co: Double? = null,
    var no2: Double? = null,
    var o3: Double? = null,
    var so2: Double? = null,
    var pm25: Double? = null,
    var pm10: Double? = null,
    var usEpaIndex: Int? = null,
    var gbDefraIndex: Int? = null
) {
    fun toJson(): String {
        return Gson().toJson(this)
    }

    companion object {
        fun fromJson(json: String): AirQuality {
            return Gson().fromJson(json, AirQuality::class.java)
        }
    }
}