package com.vikanshu.weather.ui.screens.home

enum class HomeUiEvents {
    NONE,
    NO_INTERNET,
    LOCATION_PERMISSION_DENIED,
    GPS_DISABLED
}

data class HomeLocationCardUiState(
    var isLoading: Boolean,
    var isVisibleToUser: Boolean,
    var name: String,
    var description: String,
    var time: String,
    var temp: String
)