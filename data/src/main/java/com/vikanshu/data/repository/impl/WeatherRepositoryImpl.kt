package com.vikanshu.data.repository.impl

import com.vikanshu.data.api.WeatherApi
import com.vikanshu.data.local.dao.CurrentWeatherDao
import com.vikanshu.data.local.entity.CurrentWeather
import com.vikanshu.data.repository.WeatherRepository
import com.vikanshu.data.resource.CommunicationResult
import com.vikanshu.data.resource.apiCall
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

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

}