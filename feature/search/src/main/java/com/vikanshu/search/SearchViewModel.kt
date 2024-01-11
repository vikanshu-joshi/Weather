package com.vikanshu.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.vikanshu.data.repository.LocationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    val locationRepository: LocationRepository
): ViewModel() {

    private val _uiState = mutableStateOf<SearchUiState?>(value = null)
    val uiState: State<SearchUiState?> = _uiState

}