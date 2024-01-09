package com.vikanshu.data.repository.impl

import com.vikanshu.data.api.WeatherApi
import com.vikanshu.data.model.CommunicationError
import com.vikanshu.data.model.CommunicationErrorType
import com.vikanshu.data.model.CommunicationResult
import com.vikanshu.data.model.CurrentWeatherInformation
import com.vikanshu.data.repository.WeatherRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.io.IOException
import java.util.Calendar

class WeatherRepositoryImpl(
    private val ioDispatcher: CoroutineDispatcher,
    private val weatherApi: WeatherApi
) : WeatherRepository {

    override suspend fun getWeatherInformation(
        lat: Double,
        lon: Double,
        units: String
    ): CommunicationResult<CurrentWeatherInformation> {
        return withContext(ioDispatcher) {
            try {
                val data = weatherApi.getWeatherInformationByLatLong(lat, lon, units)
                CommunicationResult.Success(data.toCurrentWeatherInformation())
            } catch (e: IOException) {
                CommunicationResult.Error(
                    CommunicationError(
                        errorType = CommunicationErrorType.NO_INTERNET,
                        errorMessage = "No Internet"
                    )
                )

            } catch (e: Exception) {
                CommunicationResult.Error(
                    CommunicationError(
                        errorType = CommunicationErrorType.UNKNOWN,
                        errorMessage = e.localizedMessage
                            ?: "Unable to fetch weather information"
                    )
                )

            }
        }
    }

    override suspend fun getWeatherInformation(
        q: String,
        units: String
    ): CommunicationResult<CurrentWeatherInformation> {
        return withContext(ioDispatcher) {
            try {
                val data = weatherApi.getWeatherInformationByName(q, units)
                CommunicationResult.Success(data.toCurrentWeatherInformation())
            } catch (e: IOException) {
                CommunicationResult.Error(
                    CommunicationError(
                        errorType = CommunicationErrorType.NO_INTERNET,
                        errorMessage = "No Internet"
                    )
                )

            } catch (e: Exception) {
                CommunicationResult.Error(
                    CommunicationError(
                        errorType = CommunicationErrorType.UNKNOWN,
                        errorMessage = e.localizedMessage
                            ?: "Unable to fetch weather information"
                    )
                )

            }
        }
    }

    override suspend fun getWeatherForecast(
        lat: Double,
        lon: Double,
        units: String
    ): CommunicationResult<Map<Calendar, CurrentWeatherInformation>> {
        return withContext(ioDispatcher) {
            try {
                val result = mutableMapOf<Calendar, CurrentWeatherInformation>()
                val data = weatherApi.getWeatherForecastInformationByLatLong(lat, lon, units)
                for (i in data.list.sortedBy { it.dt }) {
                    if (i.dt == null) continue
                    result[Calendar.getInstance().apply { timeInMillis = i.dt!!.toLong() }] =
                        i.toCurrentWeatherInformation().apply {
                            name = data.city?.name ?: ""
                            timezone = data.city!!.timezone!!
                        }
                }
                CommunicationResult.Success(result)
            } catch (e: IOException) {
                CommunicationResult.Error(
                    CommunicationError(
                        errorType = CommunicationErrorType.NO_INTERNET,
                        errorMessage = "No Internet"
                    )
                )
            } catch (e: Exception) {
                CommunicationResult.Error(
                    CommunicationError(
                        errorType = CommunicationErrorType.UNKNOWN,
                        errorMessage = e.localizedMessage
                            ?: "Unable to fetch weather information"
                    )
                )
            }
        }
    }

    override suspend fun getWeatherForecast(
        q: String,
        units: String
    ): CommunicationResult<Map<Calendar, CurrentWeatherInformation>> {
        return withContext(ioDispatcher) {
            try {
                val result = mutableMapOf<Calendar, CurrentWeatherInformation>()
                val data = weatherApi.getWeatherForecastInformationByName(q, units)
                for (i in data.list.sortedBy { it.dt }) {
                    if (i.dt == null) continue
                    result[Calendar.getInstance().apply { timeInMillis = i.dt!!.toLong() }] =
                        i.toCurrentWeatherInformation().apply {
                            name = data.city?.name ?: ""
                            timezone = data.city!!.timezone!!
                        }
                }
                CommunicationResult.Success(result)
            } catch (e: IOException) {
                CommunicationResult.Error(
                    CommunicationError(
                        errorType = CommunicationErrorType.NO_INTERNET,
                        errorMessage = "No Internet"
                    )
                )
            } catch (e: Exception) {
                CommunicationResult.Error(
                    CommunicationError(
                        errorType = CommunicationErrorType.UNKNOWN,
                        errorMessage = e.localizedMessage
                            ?: "Unable to fetch weather information"
                    )
                )
            }
        }
    }


}