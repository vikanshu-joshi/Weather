package com.vikanshu.weather.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.vikanshu.data.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class HomeViewModel @Inject constructor(
    @Named("io") val ioDispatcher: CoroutineDispatcher
) : ViewModel() {


}