package com.vikanshu.home.screen

import com.vikanshu.data.local.entity.CurrentWeather
import com.vikanshu.data.local.entity.Location

data class HomeUiState(
    val isLoading: Boolean,
    val showGpsDialog: Boolean,
    val message: String,
    val weather: List<WeatherCardState>
) {
    data class WeatherCardState(
        val location: Location,
        val weather: CurrentWeather?
    )
}