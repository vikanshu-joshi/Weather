package com.vikanshu.weather.ui.navigation

import androidx.compose.runtime.Composable

sealed class WeatherAppScreens(val route: String) {

    data object Home: WeatherAppScreens("home")
    data object Search: WeatherAppScreens("search")
    data object Forecast: WeatherAppScreens("forecast/{name}")

}