package com.vikanshu.data.local.entity

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.Gson
import com.vikanshu.data.dto.LocationDto

@Entity
data class Location(
    @PrimaryKey var name: String,
    var region: String,
    var country: String,
    var lat: Double,
    var lon: Double,
) {
    @Ignore
    var isUserCurrentLocation: Boolean = false

    fun toJson(): String {
        return Gson().toJson(this)
    }

    companion object {
        fun fromJson(json: String): Location {
            return Gson().fromJson(json, Location::class.java)
        }
        fun fromLocationDto(dto: LocationDto?): Location {
            return Location(
                name = dto?.name ?: "",
                region = dto?.region ?: "",
                country = dto?.country ?: "",
                lat = dto?.lat ?: 0.0,
                lon = dto?.lon ?: 0.0
            )
        }
    }
}
