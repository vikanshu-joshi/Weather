package com.vikanshu.data.dto.forecast

import androidx.annotation.Keep
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.vikanshu.data.dto.AirQualityDto
import com.vikanshu.data.dto.Condition

@Keep
class Hour {

    @Expose
    @SerializedName("time_epoch")
    var timeEpoch: Int? = null

    @Expose
    @SerializedName("time")
    var time: String? = null

    @Expose
    @SerializedName("temp_c")
    var tempC: Double? = null

    @Expose
    @SerializedName("temp_f")
    var tempF: Double? = null

    @Expose
    @SerializedName("is_day")
    var isDay: Int? = null

    @Expose
    @SerializedName("condition")
    var condition: Condition? = Condition()

    @Expose
    @SerializedName("wind_mph")
    var windMph: Double? = null

    @Expose
    @SerializedName("wind_kph")
    var windKph: Double? = null

    @Expose
    @SerializedName("wind_degree")
    var windDegree: Double? = null

    @Expose
    @SerializedName("wind_dir")
    var windDir: String? = null

    @Expose
    @SerializedName("pressure_mb")
    var pressureMb: Double? = null

    @Expose
    @SerializedName("pressure_in")
    var pressureIn: Double? = null

    @Expose
    @SerializedName("precip_mm")
    var precipMm: Double? = null

    @Expose
    @SerializedName("precip_in")
    var precipIn: Double? = null

    @Expose
    @SerializedName("snow_cm")
    var snowCm: Double? = null

    @Expose
    @SerializedName("humidity")
    var humidity: Double? = null

    @Expose
    @SerializedName("cloud")
    var cloud: Double? = null

    @Expose
    @SerializedName("feelslike_c")
    var feelslikeC: Double? = null

    @Expose
    @SerializedName("feelslike_f")
    var feelslikeF: Double? = null

    @Expose
    @SerializedName("windchill_c")
    var windchillC: Double? = null

    @Expose
    @SerializedName("windchill_f")
    var windchillF: Double? = null

    @Expose
    @SerializedName("heatindex_c")
    var heatindexC: Double? = null

    @Expose
    @SerializedName("heatindex_f")
    var heatindexF: Double? = null

    @Expose
    @SerializedName("dewpoint_c")
    var dewpointC: Double? = null

    @Expose
    @SerializedName("dewpoint_f")
    var dewpointF: Double? = null

    @Expose
    @SerializedName("will_it_rain")
    var willItRain: Int? = null

    @Expose
    @SerializedName("chance_of_rain")
    var chanceOfRain: Int? = null

    @Expose
    @SerializedName("will_it_snow")
    var willItSnow: Int? = null

    @Expose
    @SerializedName("chance_of_snow")
    var chanceOfSnow: Int? = null

    @Expose
    @SerializedName("vis_km")
    var visKm: Double? = null

    @Expose
    @SerializedName("vis_miles")
    var visMiles: Double? = null

    @Expose
    @SerializedName("gust_mph")
    var gustMph: Double? = null

    @Expose
    @SerializedName("gust_kph")
    var gustKph: Double? = null

    @Expose
    @SerializedName("uv")
    var uv: Int? = null

    @Expose
    @SerializedName("air_quality")
    var airQualityDto: AirQualityDto? = AirQualityDto()

}