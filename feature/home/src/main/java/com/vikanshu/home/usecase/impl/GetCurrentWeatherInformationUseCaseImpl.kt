package com.vikanshu.home.usecase.impl

import com.vikanshu.data.local.entity.CurrentWeather
import com.vikanshu.data.local.entity.Location
import com.vikanshu.data.repository.WeatherRepository
import com.vikanshu.data.resource.CommunicationResult
import com.vikanshu.home.usecase.GetCurrentWeatherInformationUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetCurrentWeatherInformationUseCaseImpl(
    private val ioDispatcher: CoroutineDispatcher,
    private val weatherRepository: WeatherRepository
) : GetCurrentWeatherInformationUseCase {

    override suspend fun invoke(locations: List<Location>): Flow<CommunicationResult<Map<Location, CurrentWeather?>>> {
        return flow {
            emit(CommunicationResult.Loading)

            /**
             * fetching local data from databse
             * */
            val localData = weatherRepository.getWeatherFromDB(locations.map { it.name })
            emit(
                CommunicationResult.LocalData(
                    locations.associateBy(
                        { it },
                        { loc -> localData.firstOrNull { it.location.name == loc.name } })
                )
            )

            /**
             * fetching data from remote
             * */
            val requests = mutableListOf<CommunicationResult<CurrentWeather>>()
            for (i in locations) {
                val result = weatherRepository.getCurrentWeather(i.name)
                requests.add(result)
            }

            requests.firstOrNull { it is CommunicationResult.Error }?.let {
                emit(it as CommunicationResult.Error)
                return@flow
            }

            emit(
                CommunicationResult.Success(
                    requests.associateBy(
                        {
                            (it as CommunicationResult.Success).data.location
                        },
                        {
                            (it as CommunicationResult.Success).data
                        }
                    )
                )
            )
        }.flowOn(ioDispatcher)
    }

}