package com.vikanshu.data.dto

import com.google.gson.annotations.SerializedName


data class LocationDto(

    @SerializedName("name") var name: String? = null,
    @SerializedName("region") var region: String? = null,
    @SerializedName("country") var country: String? = null,
    @SerializedName("lat") var lat: Double? = null,
    @SerializedName("lon") var lon: Double? = null,
    @SerializedName("tz_id") var tzId: String? = null,
    @SerializedName("localtime_epoch") var localtimeEpoch: Int? = null,
    @SerializedName("localtime") var localtime: String? = null

) {
    fun isValid() =
        name != null && region != null && country != null && lat != null && lon != null && localtimeEpoch != null && localtime != null
}