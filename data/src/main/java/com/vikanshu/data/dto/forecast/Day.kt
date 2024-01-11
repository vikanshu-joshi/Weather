package com.vikanshu.data.dto.forecast

import com.google.gson.annotations.SerializedName
import com.vikanshu.data.dto.AirQuality
import com.vikanshu.data.dto.Condition


data class Day(

    @SerializedName("maxtemp_c") var maxtempC: Double? = null,
    @SerializedName("maxtemp_f") var maxtempF: Double? = null,
    @SerializedName("mintemp_c") var mintempC: Int? = null,
    @SerializedName("mintemp_f") var mintempF: Double? = null,
    @SerializedName("avgtemp_c") var avgtempC: Double? = null,
    @SerializedName("avgtemp_f") var avgtempF: Double? = null,
    @SerializedName("maxwind_mph") var maxwindMph: Double? = null,
    @SerializedName("maxwind_kph") var maxwindKph: Double? = null,
    @SerializedName("totalprecip_mm") var totalprecipMm: Int? = null,
    @SerializedName("totalprecip_in") var totalprecipIn: Int? = null,
    @SerializedName("totalsnow_cm") var totalsnowCm: Int? = null,
    @SerializedName("avgvis_km") var avgvisKm: Int? = null,
    @SerializedName("avgvis_miles") var avgvisMiles: Int? = null,
    @SerializedName("avghumidity") var avghumidity: Int? = null,
    @SerializedName("daily_will_it_rain") var dailyWillItRain: Int? = null,
    @SerializedName("daily_chance_of_rain") var dailyChanceOfRain: Int? = null,
    @SerializedName("daily_will_it_snow") var dailyWillItSnow: Int? = null,
    @SerializedName("daily_chance_of_snow") var dailyChanceOfSnow: Int? = null,
    @SerializedName("condition") var condition: Condition? = Condition(),
    @SerializedName("uv") var uv: Int? = null,
    @SerializedName("air_quality") var airQuality: AirQuality? = AirQuality()

)