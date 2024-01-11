package com.vikanshu.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.vikanshu.data.local.model.AirQuality

@Entity
data class Location(
    @PrimaryKey var name: String,
    var region: String,
    var country: String,
    var lat: Double,
    var lon: Double
) {
    fun toJson(): String {
        return Gson().toJson(this)
    }

    companion object {
        fun fromJson(json: String): Location {
            return Gson().fromJson(json, Location::class.java)
        }
    }
}
