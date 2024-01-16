package com.vikanshu.data.api

import com.vikanshu.data.dto.astro.ResponseAstro
import com.vikanshu.data.dto.forecast.ResponseForecastWeather
import com.vikanshu.data.dto.search.ResponseSearchLocation
import com.vikanshu.data.resource.Constants
import com.vikanshu.data.dto.current.ResponseCurrentWeather
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface WeatherApi {

    @Headers("Cache-Control: public, max-age=3600")
    @GET("current.json")
    suspend fun getCurrentWeather(
        @Query("q") q: String,
        @Query("aqi") aqi: String = "yes",
        @Query("key") key: String = Constants.WEATHER_API_KEY
    ): ResponseCurrentWeather?

    @Headers("Cache-Control: public, max-age=86400")
    @GET("forecast.json")
    suspend fun getWeatherForecast(
        @Query("q") q: String,
        @Query("days") days: Int,
        @Query("aqi") aqi: String = "yes",
        @Query("alerts") alerts: String = "yes",
        @Query("key") key: String = Constants.WEATHER_API_KEY
    ): ResponseForecastWeather?


    @GET("search.json")
    suspend fun searchLocations(
        @Query("q") q: String,
        @Query("key") key: String = Constants.WEATHER_API_KEY
    ): List<ResponseSearchLocation>?

    @Headers("Cache-Control: public, max-age=86400")
    @GET("astronomy.json")
    suspend fun astronomyDetails(
        @Query("q") q: String,
        @Query("dt") dt: String, // yyyy-mm-dd
        @Query("key") key: String = Constants.WEATHER_API_KEY
    ): ResponseAstro?

}