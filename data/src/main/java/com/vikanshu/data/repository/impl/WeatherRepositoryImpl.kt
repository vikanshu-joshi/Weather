package com.vikanshu.data.repository.impl

import com.vikanshu.data.api.WeatherApi
import com.vikanshu.data.local.dao.CurrentWeatherDao
import com.vikanshu.data.local.entity.CurrentWeather
import com.vikanshu.data.local.model.Astro
import com.vikanshu.data.repository.WeatherRepository
import com.vikanshu.data.resource.CommunicationResult
import com.vikanshu.data.resource.apiCall
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class WeatherRepositoryImpl(
    private val ioDispatcher: CoroutineDispatcher,
    private val weatherDao: CurrentWeatherDao,
    private val weatherApi: WeatherApi
) : WeatherRepository {
    override suspend fun getWeatherFromDB(names: List<String>): List<CurrentWeather> {
        return weatherDao.getSavedWeatherByNames(names)
    }

    override suspend fun getWeatherFromDB(name: String): CurrentWeather? {
        return weatherDao.getSavedWeatherByName(name)
    }

    override suspend fun getCurrentWeather(name: String): CommunicationResult<CurrentWeather> {
        return withContext(ioDispatcher) {
            val result = apiCall(operation = {
                weatherApi.getCurrentWeather(name)
            }, converter = {
                CurrentWeather.fromCurrentWeatherDto(it!!)
            }, isValidResponse = {
                it != null
            })
            if (result is CommunicationResult.Success) weatherDao.insertWeather(result.data)
            result
        }
    }

    override suspend fun getAstroDetails(name: String): CommunicationResult<Astro> {
        return withContext(ioDispatcher) {
            val result = apiCall(operation = {
                weatherApi.astronomyDetails(
                    q = name,
                    dt = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Calendar.getInstance().time)
                )
            }, converter = {
                Astro.fromResponseAstroDto(it!!)
            }, isValidResponse = {
                it != null
            })
            result
        }
    }

}