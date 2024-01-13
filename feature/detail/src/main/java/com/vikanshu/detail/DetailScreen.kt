package com.vikanshu.detail

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vikanshu.core_ui.DeviceSizeType
import com.vikanshu.core_ui.components.UiLoader
import com.vikanshu.core_ui.ui.SfDisplayProFontFamily
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

    LaunchedEffect(key1 = Unit) {
        detailsViewModel.currentWeather = currentWeather
        detailsViewModel.loadData()
    }

    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        containerColor = Color.White
    ) {
        when (deviceSizeType) {
            DeviceSizeType.PORTRAIT -> DetailScreenPortrait(
                currentWeather = currentWeather,
                detailsViewModel = detailsViewModel
            )

            DeviceSizeType.LANDSCAPE -> DetailScreenLandscape(
                currentWeather = currentWeather,
                detailsViewModel = detailsViewModel
            )

            DeviceSizeType.TABLET -> DetailScreenLandscape(
                currentWeather = currentWeather,
                detailsViewModel = detailsViewModel
            )
        }
    }
}


@Composable
fun DetailScreenPortrait(
    currentWeather: CurrentWeather,
    detailsViewModel: DetailsViewModel = viewModel()
) {

    val state by detailsViewModel.uiState.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        ForecastDetailsScreenTopBar(
            isLoading = state.isLoading,
            forecast = state.forecast,
            currentWeather = currentWeather,
            onRefresh = detailsViewModel::fetchForecastData
        )
        if (state.message.isNotBlank()) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                text = state.message,
                textAlign = TextAlign.Center,
                fontFamily = SfDisplayProFontFamily,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )
        }
        LazyColumn {
            item {
                Spacer(modifier = Modifier.height(12.dp))
            }
            item {
                ForecastDetailScreenWeatherCard(
                    modifier = Modifier.padding(horizontal = 35.dp),
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

@Composable
fun DetailScreenLandscape(
    currentWeather: CurrentWeather,
    detailsViewModel: DetailsViewModel = viewModel()
) {

    val state by detailsViewModel.uiState.collectAsState()

    Row {
        Column(
            modifier = Modifier
                .weight(0.8f)
                .fillMaxHeight()
                .verticalScroll(state = rememberScrollState())
        ) {
            ForecastDetailsScreenTopBar(
                isLoading = state.isLoading,
                forecast = state.forecast,
                currentWeather = currentWeather,
                onRefresh = detailsViewModel::fetchForecastData
            )
            Spacer(modifier = Modifier.height(12.dp))
            ForecastDetailScreenWeatherCard(
                modifier = Modifier.padding(start = 28.dp),
                forecast = state.forecast,
                currentWeather = currentWeather
            )
            ForecastDetailMoreInfo(currentWeather = currentWeather)
            Spacer(modifier = Modifier.height(50.dp))
        }
        Spacer(modifier = Modifier.width(12.dp))
        LazyColumn(modifier = Modifier.weight(1f).fillMaxHeight(), verticalArrangement = Arrangement.Center) {
            if (state.isLoading && state.forecast?.forecastDays.isNullOrEmpty()) {
                item { UiLoader() }
            }
            if (!state.forecast?.forecastDays.isNullOrEmpty()) {
                items(count = state.forecast?.forecastDays!!.size) {
                    ForecastDetailDailyTile(state.forecast?.forecastDays!![it])
                }
            }
            item {
                Spacer(modifier = Modifier.height(50.dp))
            }
        }
    }
}

