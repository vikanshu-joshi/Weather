package com.vikanshu.weather.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import com.google.gson.Gson
import com.vikanshu.core_ui.ConnectivityObserver
import com.vikanshu.core_ui.DeviceSizeType
import com.vikanshu.core_ui.components.NetworkNotAvailableTile
import com.vikanshu.core_ui.ui.WeatherTheme
import com.vikanshu.data.local.entity.CurrentWeather
import com.vikanshu.data.repository.WeatherRepository
import com.vikanshu.detail.DetailScreen
import com.vikanshu.home.HomeScreen
import com.vikanshu.search.SearchScreen
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var connectivityObserver: ConnectivityObserver


    @Inject
    lateinit var weatherRepository: WeatherRepository

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            WeatherTheme {

                val connectivityState = connectivityObserver.observe()
                    .collectAsState(initial = ConnectivityObserver.Status.NetworkAvailable)

                val deviceType =
                    DeviceSizeType.calculateFromWindowSizeClass(calculateWindowSizeClass(activity = this))

                var state by remember {
                    mutableStateOf<CurrentWeather?>(null)
                }

                LaunchedEffect(key1 = true) {
                    state = weatherRepository.getWeatherFromDB("London")
                }

                Column {
                    if (state != null) DetailScreen(
                        modifier = Modifier.weight(1f),
                        currentWeather = state!!,
                        deviceSizeType = deviceType
                    )
                    AnimatedVisibility(visible = connectivityState.value == ConnectivityObserver.Status.NetworkUnavailable) {
                        NetworkNotAvailableTile(modifier = Modifier.animateContentSize())
                    }
                }


            }
        }
    }
}