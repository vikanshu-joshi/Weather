package com.vikanshu.data.local.model

import com.google.gson.Gson

data class Pressure(
    val pressureInMillibars: Int,
    val pressureInInches: Int
)  {
    fun toJson(): String {
        return Gson().toJson(this)
    }

    companion object {
        fun fromJson(json: String): Pressure {
            return Gson().fromJson(json, Pressure::class.java)
        }
    }
}
