package com.vikanshu.weather.ui.viewmodel

import android.location.Location
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vikanshu.data.model.CommunicationErrorType
import com.vikanshu.data.model.CommunicationResult
import com.vikanshu.data.repository.WeatherRepository
import com.vikanshu.weather.ui.screens.home.HomeLocationCardUiState
import com.vikanshu.weather.ui.screens.home.HomeUiEvents
import com.vikanshu.weather.utility.DateUtility
import com.vikanshu.weather.utility.LocationErrorCode
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val ioDispatcher: CoroutineDispatcher,
    private val weatherRepository: WeatherRepository
) : ViewModel() {


}