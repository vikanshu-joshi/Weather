package com.vikanshu.data.repository.impl

import com.vikanshu.data.api.WeatherApi
import com.vikanshu.data.repository.WeatherRepository
import kotlinx.coroutines.CoroutineDispatcher

class WeatherRepositoryImpl(
    private val ioDispatcher: CoroutineDispatcher,
    private val weatherApi: WeatherApi
): WeatherRepository {

}