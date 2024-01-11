package com.vikanshu.data.local.model

import com.google.gson.Gson

data class Visibility(
    val visibilityKm: Int,
    val visibilityMiles: Int
) {
    fun toJson(): String {
        return Gson().toJson(this)
    }

    companion object {
        fun fromJson(json: String): Visibility {
            return Gson().fromJson(json, Visibility::class.java)
        }
    }
}
