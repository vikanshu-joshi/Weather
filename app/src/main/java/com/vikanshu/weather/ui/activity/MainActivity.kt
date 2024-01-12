package com.vikanshu.weather.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.vikanshu.core_ui.ConnectivityObserver
import com.vikanshu.core_ui.DeviceSizeType
import com.vikanshu.core_ui.components.NetworkNotAvailableTile
import com.vikanshu.core_ui.ui.WeatherTheme
import com.vikanshu.home.HomeScreen
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var connectivityObserver: ConnectivityObserver

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            WeatherTheme {

                val connectivityState = connectivityObserver.observe()
                    .collectAsState(initial = ConnectivityObserver.Status.NetworkAvailable)

                val deviceType =
                    DeviceSizeType.calculateFromWindowSizeClass(calculateWindowSizeClass(activity = this))

                Column {
                    HomeScreen(
                        modifier = Modifier.weight(1f),
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

