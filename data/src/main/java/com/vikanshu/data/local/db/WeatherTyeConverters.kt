package com.vikanshu.data.local.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.vikanshu.data.local.entity.Location
import com.vikanshu.data.local.model.AirQuality
import com.vikanshu.data.local.model.ForecastDay
import com.vikanshu.data.local.model.Precipitation
import com.vikanshu.data.local.model.Pressure
import com.vikanshu.data.local.model.Temperature
import com.vikanshu.data.local.model.Visibility
import com.vikanshu.data.local.model.Wind
import java.util.Date

class WeatherTyeConverters {

    @TypeConverter
    fun fromDate(date: Date) = date.time

    @TypeConverter
    fun toDate(time: Long) = Date(time)

    @TypeConverter
    fun fromAirQuality(airQuality: AirQuality) = airQuality.id

    @TypeConverter
    fun toAirQuality(id: Int) = AirQuality.fromId(id)

    @TypeConverter
    fun fromWind(wind: Wind): String = wind.toJson()

    @TypeConverter
    fun toWind(json: String) = Wind.fromJson(json)

    @TypeConverter
    fun fromPressure(pressure: Pressure) = pressure.toJson()

    @TypeConverter
    fun toPressure(json: String) = Pressure.fromJson(json)

    @TypeConverter
    fun fromTemperature(temperature: Temperature) = temperature.toJson()

    @TypeConverter
    fun toTemperature(json: String) = Temperature.fromJson(json)

    @TypeConverter
    fun fromPrecipitation(precipitation: Precipitation) = precipitation.toJson()

    @TypeConverter
    fun toPrecipitation(json: String) = Precipitation.fromJson(json)

    @TypeConverter
    fun fromVisibility(visibility: Visibility) = visibility.toJson()

    @TypeConverter
    fun toVisibility(json: String) = Visibility.fromJson(json)

    @TypeConverter
    fun fromLocation(location: Location) = location.toJson()

    @TypeConverter
    fun toLocation(json: String) = Location.fromJson(json)

    @TypeConverter
    fun fromForecastDays(days: List<ForecastDay>) = Gson().toJson(days)

    @TypeConverter
    fun toForecastDays(json: String): List<ForecastDay> {
        val type = object : TypeToken<List<ForecastDay>>() {}.type
        return Gson().fromJson(json, type)
    }


}