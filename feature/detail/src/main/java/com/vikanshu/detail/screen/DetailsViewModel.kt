package com.vikanshu.detail.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vikanshu.data.repository.WeatherRepository
import com.vikanshu.data.resource.CommunicationResult
import com.vikanshu.detail.usecase.GetAstroDetailsUseCase
import com.vikanshu.detail.usecase.GetForecastDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class DetailsViewModel @Inject constructor(
    @Named("io") private val ioDispatcher: CoroutineDispatcher,
    private val weatherRepository: WeatherRepository,
    private val getAstroDetailsUseCase: GetAstroDetailsUseCase,
    private val getForecastDetailsUseCase: GetForecastDetailsUseCase
) : ViewModel() {

    var name = ""
        private set

    var uiState = MutableStateFlow(
        DetailScreenUiState(
            isLoading = false,
            message = "Loading...",
            forecast = null,
            astro = null,
            currentWeather = null
        )
    )
        private set


    fun loadData(name: String) {
        this.name = name
        fetchForecastData()
    }

    fun fetchForecastData() {
        if (uiState.value.isLoading) return
        viewModelScope.launch(ioDispatcher) {

            uiState.emit(
                DetailScreenUiState(
                    isLoading = true,
                    message = "Loading...",
                    forecast = uiState.value.forecast,
                    astro = uiState.value.astro,
                    currentWeather = uiState.value.currentWeather
                )
            )

            val currentWeather = weatherRepository.getWeatherFromDB(name)
            val astroDetails = getAstroDetailsUseCase.invoke(name)

            getForecastDetailsUseCase.invoke(name).map {
                DetailScreenUiState(
                    isLoading = it is CommunicationResult.Loading || it is CommunicationResult.LocalData,
                    message = if (it is CommunicationResult.Error) it.error.errorMessage else "",
                    astro = if (astroDetails is CommunicationResult.Success) astroDetails.data else null,
                    currentWeather = currentWeather,
                    forecast = when (it) {
                        is CommunicationResult.LocalData -> {
                            it.data
                        }

                        is CommunicationResult.Success -> {
                            it.data
                        }

                        else -> uiState.value.forecast
                    }
                )
            }.collectLatest {
                uiState.emit(it)
            }
        }
    }

}