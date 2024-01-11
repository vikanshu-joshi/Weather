package com.vikanshu.weather.ui.screens.home

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vikanshu.core_ui.DeviceSizeType
import com.vikanshu.weather.ui.viewmodel.HomeViewModel


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(),
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
    Box(modifier = modifier)
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




