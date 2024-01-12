package com.vikanshu.data.local.model

import com.google.gson.Gson
import com.vikanshu.data.dto.Current

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
    }
}
