package com.vikanshu.data.dto

import androidx.annotation.Keep
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Keep
class LocationDto public constructor() {

    @Expose
    @SerializedName("name") var name: String? = null
    @Expose
    @SerializedName("region") var region: String? = null
    @Expose
    @SerializedName("country") var country: String? = null
    @Expose
    @SerializedName("lat") var lat: Double? = null
    @Expose
    @SerializedName("lon") var lon: Double? = null
    @Expose
    @SerializedName("tz_id") var tzId: String? = null
    @Expose
    @SerializedName("localtime_epoch") var localtimeEpoch: Int? = null
    @Expose
    @SerializedName("localtime") var localtime: String? = null

}