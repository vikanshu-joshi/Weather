package com.vikanshu.home.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vikanshu.home.HomeUiState

@Composable
fun HomeScreenVerticalWeatherList(
    data: List<HomeUiState.WeatherCardState>,
) {
    LazyColumn(modifier = Modifier) {
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