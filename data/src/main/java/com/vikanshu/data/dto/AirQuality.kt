package com.vikanshu.data.dto

import com.google.gson.annotations.SerializedName


data class AirQuality(

    @SerializedName("co") var co: Double? = null,
    @SerializedName("no2") var no2: Double? = null,
    @SerializedName("o3") var o3: Double? = null,
    @SerializedName("so2") var so2: Double? = null,
    @SerializedName("pm2_5") var pm25: Double? = null,
    @SerializedName("pm10") var pm10: Double? = null,
    @SerializedName("us-epa-index") var usEpaIndex: Int? = null,
    @SerializedName("gb-defra-index") var gbDefraIndex: Int? = null

)