package com.vikanshu.data.dto.forecast

import com.google.gson.annotations.SerializedName
import com.vikanshu.data.dto.AirQuality
import com.vikanshu.data.dto.Condition


data class Hour(

    @SerializedName("time_epoch") var timeEpoch: Int? = null,
    @SerializedName("time") var time: String? = null,
    @SerializedName("temp_c") var tempC: Double? = null,
    @SerializedName("temp_f") var tempF: Double? = null,
    @SerializedName("is_day") var isDay: Int? = null,
    @SerializedName("condition") var condition: Condition? = Condition(),
    @SerializedName("wind_mph") var windMph: Double? = null,
    @SerializedName("wind_kph") var windKph: Double? = null,
    @SerializedName("wind_degree") var windDegree: Int? = null,
    @SerializedName("wind_dir") var windDir: String? = null,
    @SerializedName("pressure_mb") var pressureMb: Int? = null,
    @SerializedName("pressure_in") var pressureIn: Double? = null,
    @SerializedName("precip_mm") var precipMm: Int? = null,
    @SerializedName("precip_in") var precipIn: Int? = null,
    @SerializedName("snow_cm") var snowCm: Int? = null,
    @SerializedName("humidity") var humidity: Int? = null,
    @SerializedName("cloud") var cloud: Int? = null,
    @SerializedName("feelslike_c") var feelslikeC: Double? = null,
    @SerializedName("feelslike_f") var feelslikeF: Double? = null,
    @SerializedName("windchill_c") var windchillC: Double? = null,
    @SerializedName("windchill_f") var windchillF: Double? = null,
    @SerializedName("heatindex_c") var heatindexC: Double? = null,
    @SerializedName("heatindex_f") var heatindexF: Double? = null,
    @SerializedName("dewpoint_c") var dewpointC: Int? = null,
    @SerializedName("dewpoint_f") var dewpointF: Double? = null,
    @SerializedName("will_it_rain") var willItRain: Int? = null,
    @SerializedName("chance_of_rain") var chanceOfRain: Int? = null,
    @SerializedName("will_it_snow") var willItSnow: Int? = null,
    @SerializedName("chance_of_snow") var chanceOfSnow: Int? = null,
    @SerializedName("vis_km") var visKm: Int? = null,
    @SerializedName("vis_miles") var visMiles: Int? = null,
    @SerializedName("gust_mph") var gustMph: Double? = null,
    @SerializedName("gust_kph") var gustKph: Double? = null,
    @SerializedName("uv") var uv: Int? = null,
    @SerializedName("air_quality") var airQuality: AirQuality? = AirQuality()

)