package com.vikanshu.data.repository

import com.vikanshu.data.model.CommunicationResult
import com.vikanshu.data.model.CurrentWeatherInformation
import com.vikanshu.data.resource.Constants
import kotlinx.coroutines.flow.Flow
import java.util.Calendar

interface WeatherRepository {

    suspend fun getWeatherInformation(
        lat: Double,
        lon: Double,
        units: String = Constants.DEFAULT_UNIT
    ): CommunicationResult<CurrentWeatherInformation>

    suspend fun getWeatherInformation(
        q: String,
        units: String = Constants.DEFAULT_UNIT
    ): CommunicationResult<CurrentWeatherInformation>


    suspend fun getWeatherForecast(
        lat: Double,
        lon: Double,
        units: String = Constants.DEFAULT_UNIT
    ): CommunicationResult<Map<Calendar, CurrentWeatherInformation>>

    suspend fun getWeatherForecast(
        q: String,
        units: String = Constants.DEFAULT_UNIT
    ): CommunicationResult<Map<Calendar, CurrentWeatherInformation>>

}