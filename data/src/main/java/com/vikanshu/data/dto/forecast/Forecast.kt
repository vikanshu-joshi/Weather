package com.vikanshu.data.dto.forecast

import androidx.annotation.Keep
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Keep
class Forecast {

    @Expose
    @SerializedName("forecastday")
    var forecastday: ArrayList<Forecastday> = arrayListOf()

}