package com.vikanshu.home.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vikanshu.data.local.entity.Location
import com.vikanshu.data.repository.LocationRepository
import com.vikanshu.data.resource.CommunicationResult
import com.vikanshu.home.usecase.GetCurrentWeatherInformationUseCase
import com.vikanshu.home.usecase.GetCurrentWeatherLocationByUserLocationUseCase
import com.vikanshu.utility.LocationErrorCode
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
    private val getCurrentWeatherLocationByUserLocationUseCase: GetCurrentWeatherLocationByUserLocationUseCase,
    @Named("io") private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private var userCurrentLocationInfo: Location? = null
    private var userLocation: AndroidLocation? = null
    private var savedLocations: List<Location> = emptyList()
    private var userDisabledGps = false

    var uiState = MutableStateFlow(
        HomeUiState(
            isLoading = false,
            message = "Loading...",
            showGpsDialog = false,
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
                    message = if (uiState.value.weather.isEmpty()) "Loading..." else "",
                    showGpsDialog = uiState.value.showGpsDialog,
                    weather = uiState.value.weather.distinctBy { it.location.name }
                        .sortedBy { it.location.name }
                )
            )

            if (userLocation != null) {
                val result = getCurrentWeatherLocationByUserLocationUseCase.invoke(userLocation!!)
                if (result is CommunicationResult.Success) {

                    userCurrentLocationInfo = result.data.location

                    val newList = listOf(
                        HomeUiState.WeatherCardState(
                            location = result.data.location.copy().apply { isUserCurrentLocation = true },
                            weather = result.data
                        )
                    ) + uiState.value.weather

                    uiState.emit(
                        HomeUiState(
                            isLoading = true,
                            message = "",
                            showGpsDialog = uiState.value.showGpsDialog,
                            weather = newList.distinctBy { it.location.name }
                                .sortedBy { it.location.name }
                        )
                    )
                }
            }
            savedLocations = locationRepository.getSavedLocations().toMutableList().apply {
                if (userCurrentLocationInfo != null) {
                    removeIf { loc ->
                        userCurrentLocationInfo!!.name == loc.name
                    }
                }
            }
            getCurrentWeatherInformationUseCase.invoke(savedLocations).map { res ->
                HomeUiState(
                    isLoading = res is CommunicationResult.Loading || res is CommunicationResult.LocalData,
                    message = if (res is CommunicationResult.Error) res.error.errorMessage else "",
                    showGpsDialog = uiState.value.showGpsDialog,
                    weather = when (res) {
                        is CommunicationResult.LocalData -> (res.data.map { w ->
                            HomeUiState.WeatherCardState(
                                location = w.key,
                                weather = w.value
                            )
                        } + uiState.value.weather).distinctBy { it.location.name }
                            .sortedBy { it.location.name }

                        is CommunicationResult.Success -> (res.data.map { w ->
                            HomeUiState.WeatherCardState(
                                location = w.key,
                                weather = w.value
                            )
                        } + uiState.value.weather).distinctBy { it.location.name }
                            .sortedBy { it.location.name }

                        else -> uiState.value.weather
                    }
                )
            }.collectLatest {
                uiState.emit(it)
            }
        }
    }


    fun onUserLocationUpdated(location: AndroidLocation) {
        userLocation = location
        init()
    }

    fun onUserLocationError(error: LocationErrorCode) {
        viewModelScope.launch(ioDispatcher) {
            if (error == LocationErrorCode.GPS_DISABLED) {
                uiState.emit(
                    uiState.value.copy(
                        showGpsDialog = !userDisabledGps
                    )
                )
                if (userDisabledGps) init()
            } else {
                init()
            }
        }
    }

    fun onOpenGps() {
        viewModelScope.launch(ioDispatcher) {
            uiState.emit(
                uiState.value.copy(
                    showGpsDialog = false
                )
            )
        }
    }

    fun onUserDeniedGps() {
        userDisabledGps = true
        viewModelScope.launch(ioDispatcher) {
            uiState.emit(
                uiState.value.copy(
                    showGpsDialog = false
                )
            )
            init()
        }
    }

}