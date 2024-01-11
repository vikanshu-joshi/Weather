package com.vikanshu.weather.dto.current

import com.google.gson.annotations.SerializedName
import com.vikanshu.data.dto.Current
import com.vikanshu.data.dto.Location


data class ResponseCurrentWeather(

    @SerializedName("location") var location: Location? = Location(),
    @SerializedName("current") var current: Current? = Current()

)