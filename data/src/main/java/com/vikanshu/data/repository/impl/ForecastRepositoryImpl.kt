package com.vikanshu.data.repository.impl

import com.vikanshu.data.api.WeatherApi
import com.vikanshu.data.local.dao.ForecastDao
import com.vikanshu.data.local.entity.Forecast
import com.vikanshu.data.repository.ForecastRepository
import com.vikanshu.data.resource.CommunicationResult
import com.vikanshu.data.resource.apiCall
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Named

class ForecastRepositoryImpl(
    @Named("io") private val ioDispatcher: CoroutineDispatcher,
    private val forecastDao: ForecastDao,
    private val weatherApi: WeatherApi
) : ForecastRepository {

    override suspend fun getForecastFromDB(name: String): Forecast? {
        return forecastDao.getSavedForecastByName(name)
    }

    override suspend fun getCurrentForecast(name: String): CommunicationResult<Forecast> {
        return withContext(ioDispatcher) {
            val result = apiCall(operation = {
                weatherApi.getWeatherForecast(
                    q = name,
                    days = 7
                )
            }, converter = {
                Forecast.fromForecastResponse(it!!)
            }, isValidResponse = {
                it != null
            })
            if (result is CommunicationResult.Success) forecastDao.insertForecast(result.data)
            result
        }
    }

}