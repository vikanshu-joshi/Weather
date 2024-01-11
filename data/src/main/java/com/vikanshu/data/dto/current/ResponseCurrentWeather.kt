package com.vikanshu.weather.dto.current

import com.google.gson.annotations.SerializedName
import com.vikanshu.data.dto.Current
import com.vikanshu.data.dto.LocationDto


data class ResponseCurrentWeather(

    @SerializedName("location") var locationDto: LocationDto? = LocationDto(),
    @SerializedName("current") var current: Current? = Current()

)