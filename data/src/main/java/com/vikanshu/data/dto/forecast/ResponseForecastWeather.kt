package com.vikanshu.data.dto.forecast

import com.google.gson.annotations.SerializedName
import com.vikanshu.data.dto.Current
import com.vikanshu.data.dto.LocationDto

data class ResponseForecastWeather(

    @SerializedName("location") var locationDto: LocationDto? = LocationDto(),
    @SerializedName("current") var current: Current? = Current(),
    @SerializedName("forecast") var forecast: Forecast? = Forecast(),
    @SerializedName("alerts") var alerts: Alerts? = Alerts()

)