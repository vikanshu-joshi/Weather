package com.vikanshu.data.dto

import androidx.annotation.Keep
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Keep
class Astro public constructor() {

    @Expose
    @SerializedName("sunrise") var sunrise: String? = null
    @Expose
    @SerializedName("sunset") var sunset: String? = null
    @Expose
    @SerializedName("moonrise") var moonrise: String? = null
    @Expose
    @SerializedName("moonset") var moonset: String? = null
    @Expose
    @SerializedName("moon_phase") var moonPhase: String? = null
    @Expose
    @SerializedName("moon_illumination") var moonIllumination: Int? = null
    @Expose
    @SerializedName("is_moon_up") var isMoonUp: Int? = null
    @Expose
    @SerializedName("is_sun_up") var isSunUp: Int? = null
}