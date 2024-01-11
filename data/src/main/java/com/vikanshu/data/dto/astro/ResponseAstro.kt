package com.vikanshu.data.dto.astro

import com.google.gson.annotations.SerializedName
import com.vikanshu.data.dto.Location


data class ResponseAstro(

    @SerializedName("location") var location: Location? = Location(),
    @SerializedName("astronomy") var astronomy: Astronomy? = Astronomy()

)