package com.vikanshu.home

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.vikanshu.home.components.HomeScreenNoDataView
import com.vikanshu.home.components.HomeScreenTopBar
import com.vikanshu.home.components.HomeScreenVerticalWeatherList


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    deviceSizeType: DeviceSizeType,
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    homeViewModel: HomeViewModel = viewModel()
) {

    LaunchedEffect(key1 = Unit) {
        homeViewModel.initializeData()
    }

    Scaffold {
        when (deviceSizeType) {
            DeviceSizeType.PORTRAIT -> HomeScreenPortrait(homeViewModel)
            DeviceSizeType.LANDSCAPE -> {}
            DeviceSizeType.TABLET -> {}
        }
    }
}

@Composable
fun HomeScreenPortrait(
    homeViewModel: HomeViewModel
) {

    val state by homeViewModel.uiState.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        HomeScreenTopBar(isLoading = state.isLoading) {
            // TODO navigation search
        }
        if (state.isLoading && state.weather.isEmpty()) UiLoader()
        if (state.message.isNotBlank()) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                text = state.message,
                textAlign = TextAlign.Center,
                fontFamily = SfDisplayProFontFamily,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )
        }
        if (!state.isLoading && state.weather.isEmpty()) HomeScreenNoDataView()
        if (state.weather.isNotEmpty()) HomeScreenVerticalWeatherList(data = state.weather)
    }
}


