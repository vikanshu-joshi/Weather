package com.vikanshu.data.dto.forecast

import androidx.annotation.Keep
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.vikanshu.data.dto.Current
import com.vikanshu.data.dto.LocationDto

@Keep
class ResponseForecastWeather {

    @Expose
    @SerializedName("location")
    var locationDto: LocationDto? = LocationDto()
    @Expose
    @SerializedName("current")
    var current: Current? = Current()
    @Expose
    @SerializedName("forecast")
    var forecast: Forecast? = Forecast()
    @Expose
    @SerializedName("alerts")
    var alerts: Alerts? = Alerts()

}