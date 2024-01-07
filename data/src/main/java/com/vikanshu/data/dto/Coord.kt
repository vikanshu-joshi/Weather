package com.vikanshu.data.dto

import com.google.gson.annotations.SerializedName


data class Coord(

    @SerializedName("lon") var lon: Double? = null,
    @SerializedName("lat") var lat: Double? = null

)