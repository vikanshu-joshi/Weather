package com.vikanshu.data.repository

import com.vikanshu.data.local.entity.Forecast
import com.vikanshu.data.resource.CommunicationResult

interface ForecastRepository {
    suspend fun getCurrentForecast(
        name: String
    ): CommunicationResult<Forecast>
}