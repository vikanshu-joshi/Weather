package com.vikanshu.data.dto.astro

import androidx.annotation.Keep
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.vikanshu.data.dto.LocationDto

@Keep
class ResponseAstro {

    @Expose
    @SerializedName("location")
    var locationDto: LocationDto? = LocationDto()
    @Expose
    @SerializedName("astronomy")
    var astronomy: Astronomy? = Astronomy()
}