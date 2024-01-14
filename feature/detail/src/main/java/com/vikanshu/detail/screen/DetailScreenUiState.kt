package com.vikanshu.detail.screen

import com.vikanshu.data.local.entity.CurrentWeather
import com.vikanshu.data.local.entity.Forecast
import com.vikanshu.data.local.model.Astro

data class DetailScreenUiState(
    val isLoading: Boolean,
    val message: String,
    val currentWeather: CurrentWeather?,
    val astro: Astro?,
    val forecast: Forecast?
)
