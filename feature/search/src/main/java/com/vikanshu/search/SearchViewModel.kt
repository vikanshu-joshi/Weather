package com.vikanshu.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vikanshu.data.repository.LocationRepository
import com.vikanshu.data.resource.CommunicationResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class SearchViewModel @Inject constructor(
    val locationRepository: LocationRepository,
    @Named("io") val ioDispatcher: CoroutineDispatcher
): ViewModel() {

    private var searchJob: Job? = null

    var uiState = mutableStateOf(SearchUiState(isLoading = false, dataLoaded = false, error = "", locations = emptyList()))
    private set

    var searchQuery = mutableStateOf("")
    private set

    fun onSearchQueryChanged(query: String) {
        searchQuery.value = query
        if (query.length >= 3) {
            searchLocations()
        }
    }

    private fun searchLocations() {
        searchJob?.cancel()
        searchJob = viewModelScope.launch(ioDispatcher) {
            delay(1500L)
            if (searchQuery.value.length >= 3) locationRepository.searchLocation(searchQuery.value).collectLatest {
                when(it) {
                    is CommunicationResult.Loading -> {
                        uiState.value = SearchUiState(isLoading = true, dataLoaded = false, error = "", locations = emptyList())
                    }
                    is CommunicationResult.Success -> {
                        uiState.value = SearchUiState(isLoading = false, dataLoaded = true, error = "", locations = it.data)
                    }
                    is CommunicationResult.Error -> {
                        uiState.value = SearchUiState(isLoading = false, dataLoaded = true, error = it.error.errorMessage, locations = emptyList())
                    }
                    else -> {

                    }
                }
            }
        }
    }

}