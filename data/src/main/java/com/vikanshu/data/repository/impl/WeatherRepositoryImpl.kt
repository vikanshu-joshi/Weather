package com.vikanshu.data.repository.impl

import com.vikanshu.data.api.WeatherApi
import com.vikanshu.data.model.CommunicationError
import com.vikanshu.data.model.CommunicationErrorType
import com.vikanshu.data.model.CommunicationResult
import com.vikanshu.data.model.CurrentWeatherInformation
import com.vikanshu.data.repository.WeatherRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
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
    ): Flow<CommunicationResult<CurrentWeatherInformation>> {
        return flow {
            emit(CommunicationResult.Loading)
            try {
                val data = weatherApi.getWeatherInformationByLatLong(lat, lon, units)
                emit(CommunicationResult.Success(data.toCurrentWeatherInformation()))
            } catch (e: IOException) {
                emit(
                    CommunicationResult.Error(
                        CommunicationError(
                            errorType = CommunicationErrorType.NO_INTERNET,
                            errorMessage = "No Internet"
                        )
                    )
                )
            } catch (e: Exception) {
                emit(
                    CommunicationResult.Error(
                        CommunicationError(
                            errorType = CommunicationErrorType.UNKNOWN,
                            errorMessage = e.localizedMessage
                                ?: "Unable to fetch weather information"
                        )
                    )
                )
            }
        }.flowOn(ioDispatcher)
    }

    override suspend fun getWeatherInformation(
        q: String,
        units: String
    ): Flow<CommunicationResult<CurrentWeatherInformation>> {
        return flow {
            emit(CommunicationResult.Loading)
            try {
                val data = weatherApi.getWeatherInformationByName(q, units)
                emit(CommunicationResult.Success(data.toCurrentWeatherInformation()))
            } catch (e: IOException) {
                emit(
                    CommunicationResult.Error(
                        CommunicationError(
                            errorType = CommunicationErrorType.NO_INTERNET,
                            errorMessage = "No Internet"
                        )
                    )
                )
            } catch (e: Exception) {
                emit(
                    CommunicationResult.Error(
                        CommunicationError(
                            errorType = CommunicationErrorType.UNKNOWN,
                            errorMessage = e.localizedMessage
                                ?: "Unable to fetch weather information"
                        )
                    )
                )
            }
        }.flowOn(ioDispatcher)
    }

    override suspend fun getWeatherForecast(
        lat: Double,
        lon: Double,
        units: String
    ): Flow<CommunicationResult<Map<Calendar, CurrentWeatherInformation>>> {
        return flow {
            emit(CommunicationResult.Loading)
            try {
                val result = mutableMapOf<Calendar, CurrentWeatherInformation>()
                val data = weatherApi.getWeatherForecastInformationByLatLong(lat, lon, units)
                for (i in data.list.sortedBy { it.dt }) {
                    if (i.dt == null) continue
                    result[Calendar.getInstance().apply { timeInMillis = i.dt!!.toLong() }] =
                        i.toCurrentWeatherInformation().apply { name = data.city?.name ?: "" }
                }
                emit(CommunicationResult.Success(result))
            } catch (e: IOException) {
                emit(
                    CommunicationResult.Error(
                        CommunicationError(
                            errorType = CommunicationErrorType.NO_INTERNET,
                            errorMessage = "No Internet"
                        )
                    )
                )
            } catch (e: Exception) {
                emit(
                    CommunicationResult.Error(
                        CommunicationError(
                            errorType = CommunicationErrorType.UNKNOWN,
                            errorMessage = e.localizedMessage
                                ?: "Unable to fetch weather information"
                        )
                    )
                )
            }
        }.flowOn(ioDispatcher)
    }

    override suspend fun getWeatherForecast(
        q: String,
        units: String
    ): Flow<CommunicationResult<Map<Calendar, CurrentWeatherInformation>>> {
        return flow {
            emit(CommunicationResult.Loading)
            try {
                val result = mutableMapOf<Calendar, CurrentWeatherInformation>()
                val data = weatherApi.getWeatherForecastInformationByName(q, units)
                for (i in data.list.sortedBy { it.dt }) {
                    if (i.dt == null) continue
                    result[Calendar.getInstance().apply { timeInMillis = i.dt!!.toLong() }] =
                        i.toCurrentWeatherInformation().apply { name = data.city?.name ?: "" }
                }
                emit(CommunicationResult.Success(result))
            } catch (e: IOException) {
                emit(
                    CommunicationResult.Error(
                        CommunicationError(
                            errorType = CommunicationErrorType.NO_INTERNET,
                            errorMessage = "No Internet"
                        )
                    )
                )
            } catch (e: Exception) {
                emit(
                    CommunicationResult.Error(
                        CommunicationError(
                            errorType = CommunicationErrorType.UNKNOWN,
                            errorMessage = e.localizedMessage
                                ?: "Unable to fetch weather information"
                        )
                    )
                )
            }
        }.flowOn(ioDispatcher)
    }


}