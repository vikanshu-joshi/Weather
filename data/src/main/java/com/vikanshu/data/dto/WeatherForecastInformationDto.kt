package com.vikanshu.data.dto

import com.google.gson.annotations.SerializedName


data class WeatherForecastInformationDto(
    @SerializedName("cod") var cod: String? = null,
    @SerializedName("message") var message: Int? = null,
    @SerializedName("cnt") var cnt: Int? = null,
    @SerializedName("list") var list: ArrayList<ForecastWeather> = arrayListOf(),
    @SerializedName("city") var city: City? = City()
)