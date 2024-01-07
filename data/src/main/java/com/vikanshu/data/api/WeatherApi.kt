package com.vikanshu.data.api

import com.vikanshu.data.dto.WeatherForecastInformationDto
import com.vikanshu.data.dto.WeatherInformationDto
import com.vikanshu.data.resource.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("weather")
    suspend fun getWeatherInformationByLatLong(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("units") units: String,
        @Query("appid") appId: String = Constants.WEATHER_APP_ID
    ): WeatherInformationDto

    @GET("weather")
    suspend fun getWeatherInformationByName(
        @Query("q") q: String,
        @Query("units") units: String,
        @Query("appid") appId: String = Constants.WEATHER_APP_ID
    ): WeatherInformationDto

    @GET("forecast")
    suspend fun getWeatherForecastInformationByLatLong(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("units") units: String,
        @Query("cnt") cnt: Int = 7,
        @Query("appid") appId: String = Constants.WEATHER_APP_ID
    ): WeatherForecastInformationDto

    @GET("forecast")
    suspend fun getWeatherForecastInformationByName(
        @Query("q") q: String,
        @Query("units") units: String,
        @Query("cnt") cnt: Int = 7,
        @Query("appid") appId: String = Constants.WEATHER_APP_ID
    ): WeatherForecastInformationDto

}