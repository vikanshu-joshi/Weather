package com.vikanshu.detail

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vikanshu.core_ui.DeviceSizeType
import com.vikanshu.data.local.entity.CurrentWeather
import com.vikanshu.detail.components.ForecastDetailDailyTile
import com.vikanshu.detail.components.ForecastDetailMoreInfo
import com.vikanshu.detail.components.ForecastDetailScreenWeatherCard
import com.vikanshu.detail.components.ForecastDetailsScreenTopBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    currentWeather: CurrentWeather,
    detailsViewModel: DetailsViewModel = viewModel(),
    deviceSizeType: DeviceSizeType,
) {

    val state by detailsViewModel.uiState.collectAsState()

    LaunchedEffect(key1 = Unit) {
        detailsViewModel.currentWeather = currentWeather
        detailsViewModel.loadData()
    }

    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        containerColor = Color.White
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            ForecastDetailsScreenTopBar(
                isLoading = state.isLoading,
                forecast = state.forecast,
                currentWeather = currentWeather
            )
            LazyColumn {
                item {
                    Spacer(modifier = Modifier.height(12.dp))
                }
                item {
                    ForecastDetailScreenWeatherCard(
                        forecast = state.forecast,
                        currentWeather = currentWeather
                    )
                }
                item { Spacer(modifier = Modifier.height(22.dp)) }
                if (!state.forecast?.forecastDays.isNullOrEmpty()) {
                    items(count = state.forecast?.forecastDays!!.size) {
                        ForecastDetailDailyTile(state.forecast?.forecastDays!![it])
                    }
                }
                item {
                    ForecastDetailMoreInfo(currentWeather = currentWeather)
                }
                item {
                    Spacer(modifier = Modifier.height(150.dp))
                }
            }
        }
    }
}

