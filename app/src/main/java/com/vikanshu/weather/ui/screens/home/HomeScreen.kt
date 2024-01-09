package com.vikanshu.weather.ui.screens.home

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.vikanshu.core_ui.DeviceSizeType
import com.vikanshu.weather.ui.viewmodel.HomeViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = koinViewModel(),
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    deviceSizeType: DeviceSizeType
) {
    when(deviceSizeType) {
        DeviceSizeType.PORTRAIT -> {
            HomeScreenPortrait(viewModel = viewModel)
        }
        DeviceSizeType.LANDSCAPE -> {
            HomeScreenLandscape(viewModel = viewModel)
        }
        DeviceSizeType.TABLET -> {
            HomeScreenTablet(viewModel = viewModel)
        }
    }
}


@Composable
private fun HomeScreenPortrait(
    viewModel: HomeViewModel
) {

}

@Composable
private fun HomeScreenLandscape(
    viewModel: HomeViewModel
) {

}

@Composable
private fun HomeScreenTablet(
    viewModel: HomeViewModel
) {

}




