package com.vikanshu.data.dto.forecast

import androidx.annotation.Keep
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.vikanshu.data.dto.AirQualityDto
import com.vikanshu.data.dto.Condition

@Keep
class Day {

    @Expose
    @SerializedName("maxtemp_c")
    var maxtempC: Double? = null

    @Expose
    @SerializedName("maxtemp_f")
    var maxtempF: Double? = null

    @Expose
    @SerializedName("mintemp_c")
    var mintempC: Double? = null

    @Expose
    @SerializedName("mintemp_f")
    var mintempF: Double? = null

    @Expose
    @SerializedName("avgtemp_c")
    var avgtempC: Double? = null

    @Expose
    @SerializedName("avgtemp_f")
    var avgtempF: Double? = null

    @Expose
    @SerializedName("maxwind_mph")
    var maxwindMph: Double? = null

    @Expose
    @SerializedName("maxwind_kph")
    var maxwindKph: Double? = null

    @Expose
    @SerializedName("totalprecip_mm")
    var totalprecipMm: Double? = null

    @Expose
    @SerializedName("totalprecip_in")
    var totalprecipIn: Double? = null

    @Expose
    @SerializedName("totalsnow_cm")
    var totalsnowCm: Double? = null

    @Expose
    @SerializedName("avgvis_km")
    var avgvisKm: Double? = null

    @Expose
    @SerializedName("avgvis_miles")
    var avgvisMiles: Double? = null

    @Expose
    @SerializedName("avghumidity")
    var avghumidity: Double? = null

    @Expose
    @SerializedName("daily_will_it_rain")
    var dailyWillItRain: Double? = null

    @Expose
    @SerializedName("daily_chance_of_rain")
    var dailyChanceOfRain: Double? = null

    @Expose
    @SerializedName("daily_will_it_snow")
    var dailyWillItSnow: Double? = null

    @Expose
    @SerializedName("daily_chance_of_snow")
    var dailyChanceOfSnow: Double? = null

    @Expose
    @SerializedName("condition")
    var condition: Condition? = Condition()

    @SerializedName("uv")
    var uv: Double? = null

    @Expose
    @SerializedName("air_quality")
    var airQualityDto: AirQualityDto? = AirQualityDto()

}