package com.vikanshu.home.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.vikanshu.core_ui.DeviceSizeType
import com.vikanshu.data.local.entity.CurrentWeather
import com.vikanshu.home.screen.HomeUiState


@Composable
fun HomeScreenWeatherList(
    modifier: Modifier = Modifier,
    deviceSizeType: DeviceSizeType,
    onWeatherDetail: (CurrentWeather) -> Unit,
    data: List<HomeUiState.WeatherCardState>,
) {
    when (deviceSizeType) {
        DeviceSizeType.PORTRAIT -> HomeScreenVerticalWeatherList(modifier, data = data, onWeatherDetail = onWeatherDetail)
        DeviceSizeType.LANDSCAPE -> HomeScreenHorizontalWeatherList(modifier, data = data, onWeatherDetail = onWeatherDetail)
        DeviceSizeType.TABLET -> HomeScreenGridWeatherList(modifier, data = data, onWeatherDetail = onWeatherDetail)
    }
}

@Composable
private fun HomeScreenVerticalWeatherList(
    modifier: Modifier = Modifier,
    onWeatherDetail: (CurrentWeather) -> Unit,
    data: List<HomeUiState.WeatherCardState>,
) {
    LazyColumn(modifier = modifier) {
        items(count = data.size, key = {
            data[it].location.name
        }) {
            Spacer(modifier = Modifier.height(4.dp))
            HomeScreenWeatherCard(
                modifier = Modifier.padding(horizontal = 20.dp),
                isCurrentLocation = false,
                location = data[it].location,
                currentWeather = data[it].weather,
                onWeatherDetail = onWeatherDetail
            )
            Spacer(modifier = Modifier.height(18.dp))
        }
    }
}


@Composable
private fun HomeScreenHorizontalWeatherList(
    modifier: Modifier = Modifier,
    onWeatherDetail: (CurrentWeather) -> Unit,
    data: List<HomeUiState.WeatherCardState>,
) {
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
                currentWeather = data[it].weather,
                onWeatherDetail = onWeatherDetail
            )
            Spacer(modifier = Modifier.width(12.dp))
        }
    }
}


@Composable
private fun HomeScreenGridWeatherList(
    modifier: Modifier = Modifier,
    onWeatherDetail: (CurrentWeather) -> Unit,
    data: List<HomeUiState.WeatherCardState>,
) {

    val configuration = LocalConfiguration.current

    LazyVerticalGrid(
        columns = GridCells.Adaptive((configuration.screenWidthDp / 3).dp)
    ) {
        items(count = data.size, key = {
            data[it].location.name
        }) {
            HomeScreenWeatherCard(
                modifier = Modifier.padding(horizontal = 18.dp, vertical = 12.dp),
                isCurrentLocation = false,
                location = data[it].location,
                currentWeather = data[it].weather,
                onWeatherDetail = onWeatherDetail
            )
        }
    }
}