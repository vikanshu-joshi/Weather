package com.vikanshu.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.vikanshu.data.local.model.AirQuality

@Entity
data class Location(
    @SerializedName("name") @PrimaryKey var name: String,
    @SerializedName("region") var region: String,
    @SerializedName("country") var country: String,
    @SerializedName("lat") var lat: Double,
    @SerializedName("lon") var lon: Double,
    @SerializedName("timezoneId") var timezoneId: String,
    @SerializedName("localtimeEpoch") var localtimeEpoch: Int,
    @SerializedName("localtime") var localtime: String
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
