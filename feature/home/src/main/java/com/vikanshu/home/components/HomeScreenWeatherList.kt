package com.vikanshu.home.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.vikanshu.core_ui.DeviceSizeType
import com.vikanshu.home.HomeUiState


@Composable
fun HomeScreenWeatherList(
    modifier: Modifier = Modifier,
    deviceSizeType: DeviceSizeType,
    data: List<HomeUiState.WeatherCardState>,
) {
    when (deviceSizeType) {
        DeviceSizeType.PORTRAIT -> HomeScreenVerticalWeatherList(modifier, data)
        DeviceSizeType.LANDSCAPE -> HomeScreenHorizontalWeatherList(modifier, data)
        DeviceSizeType.TABLET -> HomeScreenHorizontalWeatherList(modifier, data)
    }
}

@Composable
private fun HomeScreenVerticalWeatherList(
    modifier: Modifier = Modifier,
    data: List<HomeUiState.WeatherCardState>,
) {
    LazyColumn(modifier = modifier) {
        items(count = data.size, key = {
            data[it].location.name
        }) {
            HomeScreenWeatherCard(
                modifier = Modifier.padding(horizontal = 20.dp),
                isCurrentLocation = false,
                location = data[it].location,
                currentWeather = data[it].weather
            )
            Spacer(modifier = Modifier.height(18.dp))
        }
    }
}


@Composable
private fun HomeScreenHorizontalWeatherList(
    modifier: Modifier = Modifier,
    data: List<HomeUiState.WeatherCardState>,
) {

    val configuration = LocalConfiguration.current

    LazyRow(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(count = data.size, key = {
            data[it].location.name
        }) {
            Spacer(modifier = Modifier.width(12.dp))
            HomeScreenWeatherCard(
                modifier = Modifier
                    .padding(vertical = 8.dp),
                isCurrentLocation = false,
                location = data[it].location,
                currentWeather = data[it].weather
            )
            Spacer(modifier = Modifier.width(12.dp))
        }
    }
}