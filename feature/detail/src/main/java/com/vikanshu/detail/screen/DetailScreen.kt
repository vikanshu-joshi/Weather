package com.vikanshu.detail.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vikanshu.core_ui.ConnectivityObserver
import com.vikanshu.core_ui.DeviceSizeType
import com.vikanshu.core_ui.components.UiLoader
import com.vikanshu.core_ui.ui.SfDisplayProFontFamily
import com.vikanshu.detail.components.ForecastDetailDailyTile
import com.vikanshu.detail.components.ForecastDetailMoreInfo
import com.vikanshu.detail.components.ForecastDetailScreenWeatherCard
import com.vikanshu.detail.components.ForecastDetailsScreenTopBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    name: String,
    connectivityState: ConnectivityObserver.Status,
    onBack: () -> Unit,
    detailsViewModel: DetailsViewModel = hiltViewModel(),
    deviceSizeType: DeviceSizeType,
) {

    LaunchedEffect(key1 = name, key2 = connectivityState) {
        detailsViewModel.loadData(name)
    }

    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        containerColor = Color.White
    ) {
        when (deviceSizeType) {
            DeviceSizeType.PORTRAIT -> DetailScreenPortrait(
                detailsViewModel = detailsViewModel,
                onBack = onBack
            )

            DeviceSizeType.LANDSCAPE -> DetailScreenLandscape(
                detailsViewModel = detailsViewModel,
                onBack = onBack
            )

            DeviceSizeType.TABLET -> DetailScreenLandscape(
                detailsViewModel = detailsViewModel,
                onBack = onBack
            )
        }
    }
}


@Composable
fun DetailScreenPortrait(
    onBack: () -> Unit,
    detailsViewModel: DetailsViewModel = viewModel()
) {

    val state by detailsViewModel.uiState.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        ForecastDetailsScreenTopBar(
            isLoading = state.isLoading,
            forecast = state.forecast,
            name = detailsViewModel.name,
            onRefresh = detailsViewModel::fetchForecastData,
            onBack = onBack
        )

        if (state.isLoading && state.currentWeather == null) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                UiLoader()
            }
        }

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
            if (state.currentWeather != null) {
                item {
                    ForecastDetailScreenWeatherCard(
                        modifier = Modifier.padding(horizontal = 35.dp),
                        forecast = state.forecast,
                        currentWeather = state.currentWeather!!
                    )
                }
            }
            item { Spacer(modifier = Modifier.height(22.dp)) }
            if (!state.forecast?.forecastDays.isNullOrEmpty()) {
                items(count = state.forecast?.forecastDays!!.size) {
                    ForecastDetailDailyTile(state.forecast?.forecastDays!![it])
                }
            }
            if (state.currentWeather != null) {
                item {
                    ForecastDetailMoreInfo(currentWeather = state.currentWeather!!, astro = state.astro)
                }
            }
            item {
                Spacer(modifier = Modifier.height(150.dp))
            }
        }
    }
}

@Composable
fun DetailScreenLandscape(
    onBack: () -> Unit,
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
                name = detailsViewModel.name,
                onRefresh = detailsViewModel::fetchForecastData,
                onBack = onBack
            )
            Spacer(modifier = Modifier.height(12.dp))
            if (state.currentWeather != null) {
                ForecastDetailScreenWeatherCard(
                    modifier = Modifier.padding(start = 28.dp),
                    forecast = state.forecast,
                    currentWeather = state.currentWeather!!
                )
                ForecastDetailMoreInfo(currentWeather = state.currentWeather!!, astro = state.astro)
            }
            Spacer(modifier = Modifier.height(50.dp))
        }
        Spacer(modifier = Modifier.width(12.dp))
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(), verticalArrangement = Arrangement.Center
        ) {
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

