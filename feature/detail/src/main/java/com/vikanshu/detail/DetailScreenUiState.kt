package com.vikanshu.detail

import com.vikanshu.data.local.entity.CurrentWeather
import com.vikanshu.data.local.entity.Forecast

data class DetailScreenUiState(
    val isLoading: Boolean,
    val message: String,
    val currentWeather: CurrentWeather?,
    val forecast: Forecast?
)
