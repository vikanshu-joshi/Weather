package com.vikanshu.data.dto.current

import androidx.annotation.Keep
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.vikanshu.data.dto.Current
import com.vikanshu.data.dto.LocationDto

@Keep
class ResponseCurrentWeather {

    @Expose
    @SerializedName("location")
    var locationDto: LocationDto? = LocationDto()
    @Expose
    @SerializedName("current")
    var current: Current? = Current()

}