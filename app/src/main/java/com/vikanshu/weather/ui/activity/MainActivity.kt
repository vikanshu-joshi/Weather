package com.vikanshu.weather.ui.activity

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import com.vikanshu.core_ui.ConnectivityObserver
import com.vikanshu.core_ui.DeviceSizeType
import com.vikanshu.core_ui.ui.WeatherTheme
import com.vikanshu.weather.WeatherApp
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var connectivityObserver: ConnectivityObserver

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requestPermissions(arrayOf(android.Manifest.permission.POST_NOTIFICATIONS), 1)
        }
        setContent {
            WeatherTheme {
                val deviceType =
                    DeviceSizeType.calculateFromWindowSizeClass(calculateWindowSizeClass(activity = this))
                WeatherApp(
                    connectivityObserver = connectivityObserver,
                    deviceSizeType = deviceType
                )
            }
        }
    }
}