package com.vikanshu.weather.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vikanshu.core_ui.ConnectivityObserver
import com.vikanshu.core_ui.DeviceSizeType
import com.vikanshu.weather.ui.components.NetworkNotAvailableTile
import com.vikanshu.weather.ui.screens.home.HomeScreen
import com.vikanshu.weather.ui.theme.SfDisplayProFontFamily
import com.vikanshu.weather.ui.theme.WeatherTheme
import org.koin.android.ext.android.get

class MainActivity : ComponentActivity() {

    private val connectivityObserver: ConnectivityObserver = get()

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

