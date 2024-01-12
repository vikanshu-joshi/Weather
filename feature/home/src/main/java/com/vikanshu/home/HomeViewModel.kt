package com.vikanshu.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vikanshu.data.local.entity.CurrentWeather
import com.vikanshu.data.local.entity.Location
import com.vikanshu.data.repository.LocationRepository
import com.vikanshu.data.repository.WeatherRepository
import com.vikanshu.data.resource.CommunicationResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named
import android.location.Location as AndroidLocation

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val locationRepository: LocationRepository,
    private val weatherRepository: WeatherRepository,
    @Named("io") private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private var savedLocations: List<Location> = emptyList()

    var uiState = MutableStateFlow(
        HomeUiState(
            isLoading = false,
            message = "",
            weather = emptyList()
        )
    )
        private set


    fun initializeData() {
        viewModelScope.launch(ioDispatcher) {
            uiState.emit(
                HomeUiState(
                    isLoading = true,
                    message = "Loading...",
                    weather = emptyList()
                )
            )
            savedLocations = locationRepository.getSavedLocations()
            val weatherState = savedLocations.map {
                HomeUiState.WeatherCardState(
                    location = it,
                    weather = weatherRepository.getWeatherFromDB(it.name)
                )
            }.sortedBy { it.location.name }
            uiState.emit(
                HomeUiState(
                    isLoading = true,
                    message = "",
                    weather = weatherState
                )
            )
            delay(2000L)
            fetchWeatherUpdates()
        }
    }

    private fun fetchWeatherUpdates() {
        viewModelScope.launch(ioDispatcher) {
            val weatherStateCalls = mutableListOf<Deferred<CommunicationResult<CurrentWeather>>>()
            for (i in savedLocations) {
                weatherStateCalls.add(async { weatherRepository.getCurrentWeather(i.name) })
            }
            val response = weatherStateCalls.awaitAll()

            val message = if (response.any { it is CommunicationResult.Error }) {
                (response.firstOrNull { it is CommunicationResult.Error } as CommunicationResult.Error).error.errorMessage
            } else {
                ""
            }

            uiState.emit(
                HomeUiState(
                    isLoading = false,
                    message = message,
                    weather = response.filter {
                        it is CommunicationResult.Success
                    }.map {
                        HomeUiState.WeatherCardState(
                            location = (it as CommunicationResult.Success).data.location,
                            weather = it.data
                        )
                    }.sortedBy { it.location.name }
                )
            )
        }
    }


    fun onUserLocationUpdated(location: AndroidLocation) {

    }

    fun onUserLocationPermissionDenied() {

    }

    fun onUserGpsDisabled() {

    }

}