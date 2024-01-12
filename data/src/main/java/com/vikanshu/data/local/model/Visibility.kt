package com.vikanshu.data.local.model

import com.google.gson.Gson
import com.vikanshu.data.dto.Current

data class Visibility(
    val visibilityKm: Double,
    val visibilityMiles: Double
) {
    fun toJson(): String {
        return Gson().toJson(this)
    }

    companion object {
        fun fromJson(json: String): Visibility {
            return Gson().fromJson(json, Visibility::class.java)
        }
        fun fromCurrentDto(current: Current?): Visibility {
            return Visibility(
                visibilityKm = current?.visKm ?: 0.0,
                visibilityMiles = current?.visMiles ?: 0.0
            )
        }
    }
}
