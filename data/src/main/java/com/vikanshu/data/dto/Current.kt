package com.vikanshu.data.dto

import androidx.annotation.Keep
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Keep
class Current public constructor() {

    @Expose
    @SerializedName("last_updated_epoch") var lastUpdatedEpoch: Int? = null
    @Expose
    @SerializedName("last_updated") var lastUpdated: String? = null
    @Expose
    @SerializedName("temp_c") var tempC: Double? = null
    @Expose
    @SerializedName("temp_f") var tempF: Double? = null
    @Expose
    @SerializedName("is_day") var isDay: Int? = null
    @Expose
    @SerializedName("condition") var condition: Condition? = Condition()
    @Expose
    @SerializedName("wind_mph") var windMph: Double? = null
    @Expose
    @SerializedName("wind_kph") var windKph: Double? = null
    @Expose
    @SerializedName("wind_degree") var windDegree: Double? = null
    @Expose
    @SerializedName("wind_dir") var windDir: String? = null
    @Expose
    @SerializedName("pressure_mb") var pressureMb: Double? = null
    @Expose
    @SerializedName("pressure_in") var pressureIn: Double? = null
    @Expose
    @SerializedName("precip_mm") var precipMm: Double? = null
    @Expose
    @SerializedName("precip_in") var precipIn: Double? = null
    @Expose
    @SerializedName("humidity") var humidity: Double? = null
    @Expose
    @SerializedName("cloud") var cloud: Double? = null
    @Expose
    @SerializedName("feelslike_c") var feelslikeC: Double? = null
    @Expose
    @SerializedName("feelslike_f") var feelslikeF: Double? = null
    @Expose
    @SerializedName("vis_km") var visKm: Double? = null
    @Expose
    @SerializedName("vis_miles") var visMiles: Double? = null
    @Expose
    @SerializedName("uv") var uv: Double? = null
    @Expose
    @SerializedName("gust_mph") var gustMph: Double? = null
    @Expose
    @SerializedName("gust_kph") var gustKph: Double? = null
    @Expose
    @SerializedName("air_quality") var airQualityDto: AirQualityDto? = AirQualityDto()

}