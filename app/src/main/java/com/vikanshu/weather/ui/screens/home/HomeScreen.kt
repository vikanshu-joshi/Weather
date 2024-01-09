package com.vikanshu.weather.ui.screens.home

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.vikanshu.weather.ui.viewmodel.HomeViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel(),
    windowSizeClass: WindowSizeClass
) {
}




