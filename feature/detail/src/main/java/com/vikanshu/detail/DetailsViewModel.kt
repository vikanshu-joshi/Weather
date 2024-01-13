package com.vikanshu.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vikanshu.data.local.entity.CurrentWeather
import com.vikanshu.data.repository.ForecastRepository
import com.vikanshu.data.repository.WeatherRepository
import com.vikanshu.data.resource.CommunicationResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class DetailsViewModel @Inject constructor(
    @Named("io") private val ioDispatcher: CoroutineDispatcher,
    private val weatherRepository: WeatherRepository,
    private val forecastRepository: ForecastRepository
) : ViewModel() {

    var name = ""
        private set

    var uiState = MutableStateFlow(
        DetailScreenUiState(
            isLoading = false,
            message = "",
            forecast = null,
            currentWeather = null
        )
    )
        private set


    fun loadData(name: String) {
        viewModelScope.launch(ioDispatcher) {
            this@DetailsViewModel.name = name
            uiState.emit(
                DetailScreenUiState(
                    isLoading = true,
                    message = "",
                    forecast = null,
                    currentWeather = null
                )
            )
            val currentWeather = weatherRepository.getWeatherFromDB(name)
            val result = forecastRepository.getForecastFromDB(name)
            uiState.emit(
                DetailScreenUiState(
                    isLoading = false,
                    message = "",
                    forecast = result,
                    currentWeather = currentWeather
                )
            )
            fetchForecastData()
        }
    }

    fun fetchForecastData() {
        if (uiState.value.isLoading) return
        viewModelScope.launch(ioDispatcher) {
            uiState.emit(
                DetailScreenUiState(
                    isLoading = true,
                    message = "",
                    forecast = uiState.value.forecast,
                    currentWeather = uiState.value.currentWeather
                )
            )
            val result = forecastRepository.getCurrentForecast(name)
            if (result is CommunicationResult.Success) {
                uiState.emit(
                    DetailScreenUiState(
                        isLoading = false,
                        message = "",
                        forecast = result.data,
                        currentWeather = uiState.value.currentWeather
                    )
                )
            } else if (result is CommunicationResult.Error) {
                uiState.emit(
                    DetailScreenUiState(
                        isLoading = false,
                        message = result.error.errorMessage,
                        forecast = uiState.value.forecast,
                        currentWeather = uiState.value.currentWeather
                    )
                )
            }
        }
    }

}