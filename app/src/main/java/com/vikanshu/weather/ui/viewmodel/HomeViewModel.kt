package com.vikanshu.weather.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.vikanshu.data.repository.WeatherRepository
import kotlinx.coroutines.CoroutineDispatcher

class HomeViewModel(
    private val ioDispatcher: CoroutineDispatcher,
    private val weatherRepository: WeatherRepository
) : ViewModel() {


}