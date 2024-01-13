package com.vikanshu.weather.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.vikanshu.core_ui.DeviceSizeType
import com.vikanshu.data.local.entity.CurrentWeather
import com.vikanshu.detail.DetailScreen
import com.vikanshu.home.HomeScreen
import com.vikanshu.search.SearchScreen

@Composable
fun WeatherNavigation(
    modifier: Modifier = Modifier,
    deviceSizeType: DeviceSizeType
) {

    val navController = rememberNavController()

    NavHost(modifier = modifier, navController = navController, startDestination = WeatherAppScreens.Home.route) {
        composable(route = WeatherAppScreens.Home.route) {
            HomeScreen(deviceSizeType = deviceSizeType, onSearch = {
                navController.navigate(WeatherAppScreens.Search.route)
            }, onWeatherDetail = {
                navController.navigate(
                    WeatherAppScreens.Forecast.route.replace(
                        "{name}",
                        it.location.name
                    )
                )
            })
        }
        composable(route = WeatherAppScreens.Search.route) {
            SearchScreen(deviceSizeType = deviceSizeType, onBack = navController::navigateUp)
        }
        composable(route = WeatherAppScreens.Forecast.route,
            arguments = listOf(
                navArgument(
                    name = "name"
                ) {
                    type = NavType.StringType
                }
            )
        ) {
            val name = it.arguments?.getString("name") ?: ""
            DetailScreen(
                name = name,
                deviceSizeType = deviceSizeType,
                onBack = navController::navigateUp
            )
        }
    }
}