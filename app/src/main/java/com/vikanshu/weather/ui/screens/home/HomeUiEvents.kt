package com.vikanshu.weather.ui.screens.home

sealed class HomeUiEvents() {

    data object NoInternet: HomeUiEvents()
    data object LocationPermissionGranted: HomeUiEvents()
    data object LocationPermissionDenied: HomeUiEvents()
    data object GpsDisabled: HomeUiEvents()


}