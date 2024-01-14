package com.vikanshu.detail.usecase.impl

import com.vikanshu.data.local.entity.Forecast
import com.vikanshu.data.repository.ForecastRepository
import com.vikanshu.data.resource.CommunicationResult
import com.vikanshu.detail.usecase.GetForecastDetailsUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetForecastDetailsUseCaseImpl(
    private val ioDispatcher: CoroutineDispatcher,
    private val forecastRepository: ForecastRepository
): GetForecastDetailsUseCase {

    override suspend fun invoke(name: String): Flow<CommunicationResult<Forecast>> {
        return flow {
            emit(CommunicationResult.Loading)

            val localData = forecastRepository.getForecastFromDB(name)
            if (localData != null) emit(CommunicationResult.LocalData(localData))

            val result = forecastRepository.getCurrentForecast(name)
            emit(result)
            return@flow
        }.flowOn(ioDispatcher)
    }

}