package com.vikanshu.data.local.model

import com.google.gson.Gson
import com.vikanshu.data.dto.Current

data class Pressure(
    val pressureInMillibars: Double,
    val pressureInInches: Double
)  {
    fun toJson(): String {
        return Gson().toJson(this)
    }

    companion object {
        fun fromJson(json: String): Pressure {
            return Gson().fromJson(json, Pressure::class.java)
        }

        fun fromCurrentDto(current: Current?): Pressure {
            return Pressure(
                pressureInInches = current?.pressureIn ?: 0.0,
                pressureInMillibars = current?.pressureMb ?: 0.0
            )
        }
    }
}
