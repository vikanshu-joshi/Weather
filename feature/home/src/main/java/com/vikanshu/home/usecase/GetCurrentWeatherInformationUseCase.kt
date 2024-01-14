package com.vikanshu.home.usecase

import com.vikanshu.data.local.entity.CurrentWeather
import com.vikanshu.data.local.entity.Location
import com.vikanshu.data.resource.CommunicationResult
import kotlinx.coroutines.flow.Flow

interface GetCurrentWeatherInformationUseCase {
    suspend operator fun invoke(locations: List<Location>): Flow<CommunicationResult<Map<Location, CurrentWeather?>>>
}