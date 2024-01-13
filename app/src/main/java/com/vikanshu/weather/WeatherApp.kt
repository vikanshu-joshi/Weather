package com.vikanshu.weather

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.vikanshu.core_ui.ConnectivityObserver
import com.vikanshu.core_ui.DeviceSizeType
import com.vikanshu.core_ui.components.NetworkNotAvailableTile
import com.vikanshu.weather.ui.navigation.WeatherNavigation

@Composable
fun WeatherApp(
    connectivityObserver: ConnectivityObserver,
    deviceSizeType: DeviceSizeType
) {
    val connectivityState = connectivityObserver.observe()
        .collectAsState(initial = ConnectivityObserver.Status.NetworkAvailable)

    Column {
        WeatherNavigation(modifier = Modifier.weight(1f), deviceSizeType = deviceSizeType)
        AnimatedVisibility(visible = connectivityState.value == ConnectivityObserver.Status.NetworkUnavailable) {
            NetworkNotAvailableTile(modifier = Modifier.animateContentSize())
        }
    }
}