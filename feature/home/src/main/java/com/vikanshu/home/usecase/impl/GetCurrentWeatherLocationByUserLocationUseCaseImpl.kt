package com.vikanshu.home.usecase.impl

import android.location.Location
import com.vikanshu.data.local.entity.CurrentWeather
import com.vikanshu.data.repository.WeatherRepository
import com.vikanshu.data.resource.CommunicationResult
import com.vikanshu.home.usecase.GetCurrentWeatherLocationByUserLocationUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class GetCurrentWeatherLocationByUserLocationUseCaseImpl(
    private val ioDispatcher: CoroutineDispatcher,
    private val weatherRepository: WeatherRepository
): GetCurrentWeatherLocationByUserLocationUseCase {

    override suspend fun invoke(location: Location): CommunicationResult<CurrentWeather> {
        return withContext(ioDispatcher) {
            weatherRepository.getCurrentWeather("${location.latitude},${location.longitude}")
        }
    }

}