package com.vikanshu.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vikanshu.data.local.entity.CurrentWeather
import com.vikanshu.data.repository.ForecastRepository
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
    private val forecastRepository: ForecastRepository
) : ViewModel() {

    lateinit var currentWeather: CurrentWeather

    var uiState = MutableStateFlow(
        DetailScreenUiState(
            isLoading = false,
            message = "",
            forecast = null
        )
    )
        private set


    fun loadData() {
        viewModelScope.launch(ioDispatcher) {
            uiState.emit(
                DetailScreenUiState(
                    isLoading = true,
                    message = "Loading...",
                    forecast = null
                )
            )
            val result = forecastRepository.getForecastFromDB(currentWeather.location.name)
            uiState.emit(
                DetailScreenUiState(
                    isLoading = true,
                    message = "",
                    forecast = result
                )
            )
            fetchForecastData()
        }
    }

    private fun fetchForecastData() {
        viewModelScope.launch(ioDispatcher) {
            delay(5000L)
            val result = forecastRepository.getCurrentForecast(currentWeather.location.name)
            if (result is CommunicationResult.Success) {
                uiState.emit(
                    DetailScreenUiState(
                        isLoading = false,
                        message = "",
                        forecast = result.data
                    )
                )
            } else if (result is CommunicationResult.Error) {
                uiState.emit(
                    DetailScreenUiState(
                        isLoading = false,
                        message = result.error.errorMessage,
                        forecast = uiState.value.forecast
                    )
                )
            }
        }
    }

}