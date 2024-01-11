package com.vikanshu.data.dto.astro

import com.google.gson.annotations.SerializedName
import com.vikanshu.data.dto.LocationDto


data class ResponseAstro(

    @SerializedName("location") var locationDto: LocationDto? = LocationDto(),
    @SerializedName("astronomy") var astronomy: Astronomy? = Astronomy()

)