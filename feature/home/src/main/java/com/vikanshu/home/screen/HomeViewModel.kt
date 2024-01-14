package com.vikanshu.home.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vikanshu.data.local.entity.Location
import com.vikanshu.data.repository.LocationRepository
import com.vikanshu.data.resource.CommunicationResult
import com.vikanshu.home.usecase.GetCurrentWeatherInformationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named
import android.location.Location as AndroidLocation

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val locationRepository: LocationRepository,
    private val getCurrentWeatherInformationUseCase: GetCurrentWeatherInformationUseCase,
    @Named("io") private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private var savedLocations: List<Location> = emptyList()

    var uiState = MutableStateFlow(
        HomeUiState(
            isLoading = false,
            message = "Loading...",
            weather = emptyList()
        )
    )
        private set


    fun init() {
        loadData()
    }


    private fun loadData() {
        if (uiState.value.isLoading) return
        viewModelScope.launch(ioDispatcher) {
            uiState.emit(
                HomeUiState(
                    isLoading = true,
                    message = "Loading...",
                    weather = uiState.value.weather
                )
            )
            savedLocations = locationRepository.getSavedLocations()
            getCurrentWeatherInformationUseCase.invoke(savedLocations).map {
                HomeUiState(
                    isLoading = it is CommunicationResult.Loading || it is CommunicationResult.LocalData,
                    message = if (it is CommunicationResult.Error) it.error.errorMessage else "",
                    weather = when (it) {
                        is CommunicationResult.LocalData -> it.data.map { w ->
                            HomeUiState.WeatherCardState(
                                location = w.key,
                                weather = w.value
                            )
                        }

                        is CommunicationResult.Success -> it.data.map { w ->
                            HomeUiState.WeatherCardState(
                                location = w.key,
                                weather = w.value
                            )
                        }

                        else -> uiState.value.weather
                    }
                )
            }.collectLatest {
                uiState.emit(it)
            }
        }
    }


    fun onUserLocationUpdated(location: AndroidLocation) {

    }

    fun onUserLocationPermissionDenied() {

    }

    fun onUserGpsDisabled() {

    }

}