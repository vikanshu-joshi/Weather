package com.vikanshu.data.local.model

import com.google.gson.Gson

data class Wind(
    val windMph: Double,
    val windKph: Double,
    val windDeg: Int,
    val windDir: String
) {
    fun toJson(): String {
        return Gson().toJson(this)
    }

    companion object {
        fun fromJson(json: String): Wind {
            return Gson().fromJson(json, Wind::class.java)
        }
    }
}
