package com.vikanshu.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vikanshu.data.local.entity.Location
import com.vikanshu.data.repository.LocationRepository
import com.vikanshu.data.resource.CommunicationResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val locationRepository: LocationRepository,
    @Named("io") private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private var searchJob: Job? = null
    private var savedLocations: List<Location> = emptyList()

    var uiState =
        MutableStateFlow(SearchUiState(isLoading = false, message = "", locations = emptyList()))
        private set

    var searchQuery = mutableStateOf("")
        private set

    init {
        viewModelScope.launch(ioDispatcher) {
            uiState.emit(
                SearchUiState(
                    isLoading = true,
                    message = "Loading...",
                    locations = emptyList()
                )
            )
            savedLocations = locationRepository.getSavedLocations()
            uiState.emit(SearchUiState(isLoading = false, message = "", locations = emptyList()))
        }
    }

    fun onSearchQueryChanged(query: String) {
        searchQuery.value = query
        if (query.length >= 3) {
            searchLocations()
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun onLocationAdd(location: Location) {
        GlobalScope.launch(ioDispatcher) {
            locationRepository.saveLocation(location)
        }
    }

    private fun searchLocations() {
        searchJob?.cancel()
        searchJob = viewModelScope.launch(ioDispatcher) {
            delay(1500L)
            if (searchQuery.value.length >= 3) locationRepository.searchLocation(searchQuery.value)
                .collectLatest {
                    when (it) {
                        is CommunicationResult.Loading -> {
                            uiState.emit(
                                SearchUiState(
                                    isLoading = true,
                                    message = "Searching....",
                                    locations = emptyList()
                                )
                            )
                        }

                        is CommunicationResult.Success -> {
                            uiState.emit(SearchUiState(
                                isLoading = false,
                                message = if (it.data.isEmpty()) "No results \uD83D\uDE41" else "",
                                locations = it.data.filter { l -> !savedLocations.contains(l) }
                            ))
                        }

                        is CommunicationResult.Error -> {
                            uiState.emit(
                                SearchUiState(
                                    isLoading = false,
                                    message = it.error.errorMessage,
                                    locations = emptyList()
                                )
                            )
                        }

                        else -> {

                        }
                    }
                }
        }
    }

}