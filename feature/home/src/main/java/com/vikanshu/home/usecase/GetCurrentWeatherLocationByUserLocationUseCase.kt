package com.vikanshu.home.usecase

import android.location.Location
import com.vikanshu.data.local.entity.CurrentWeather
import com.vikanshu.data.resource.CommunicationResult

interface GetCurrentWeatherLocationByUserLocationUseCase {

    suspend operator fun invoke(location: Location): CommunicationResult<CurrentWeather>

}