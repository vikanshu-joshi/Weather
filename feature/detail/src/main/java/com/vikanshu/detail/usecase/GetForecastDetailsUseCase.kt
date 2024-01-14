package com.vikanshu.detail.usecase

import com.vikanshu.data.local.entity.Forecast
import com.vikanshu.data.resource.CommunicationResult
import kotlinx.coroutines.flow.Flow

interface GetForecastDetailsUseCase {

    suspend operator fun invoke(name: String): Flow<CommunicationResult<Forecast>>

}