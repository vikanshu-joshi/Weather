package com.vikanshu.data.repository

import com.vikanshu.data.local.entity.CurrentWeather
import com.vikanshu.data.local.model.Astro
import com.vikanshu.data.resource.CommunicationResult

interface WeatherRepository {
    suspend fun getWeatherFromDB(name: List<String>): List<CurrentWeather>
    suspend fun getWeatherFromDB(name: String): CurrentWeather?
    suspend fun getCurrentWeather(
        name: String,
        shouldSaveLocally: Boolean = true
    ): CommunicationResult<CurrentWeather>
    suspend fun getAstroDetails(
        name: String
    ): CommunicationResult<Astro>
}