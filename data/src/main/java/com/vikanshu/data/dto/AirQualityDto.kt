package com.vikanshu.data.dto

import androidx.annotation.Keep
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Keep
class AirQualityDto public constructor() {

    @Expose
    @SerializedName("co") var co: Double? = null

    @Expose
    @SerializedName("no2") var no2: Double? = null

    @Expose
    @SerializedName("o3") var o3: Double? = null

    @Expose
    @SerializedName("so2") var so2: Double? = null

    @Expose
    @SerializedName("pm2_5") var pm25: Double? = null

    @Expose
    @SerializedName("pm10") var pm10: Double? = null

    @Expose
    @SerializedName("us-epa-index") var usEpaIndex: Int? = null

    @Expose
    @SerializedName("gb-defra-index") var gbDefraIndex: Int? = null
}