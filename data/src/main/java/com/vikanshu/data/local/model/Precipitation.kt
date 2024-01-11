package com.vikanshu.data.local.model

import com.google.gson.Gson

data class Precipitation(
    val precipitationInMm: Int,
    val precipitationInInches: Int
) {
    fun toJson(): String {
        return Gson().toJson(this)
    }

    companion object {
        fun fromJson(json: String): Precipitation {
            return Gson().fromJson(json, Precipitation::class.java)
        }
    }
}
