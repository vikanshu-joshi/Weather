package com.vikanshu.home.screen

import android.Manifest
import android.annotation.SuppressLint
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.vikanshu.core_ui.ConnectivityObserver
import com.vikanshu.core_ui.DeviceSizeType
import com.vikanshu.core_ui.components.UiLoader
import com.vikanshu.core_ui.ui.SfDisplayProFontFamily
import com.vikanshu.data.local.entity.CurrentWeather
import com.vikanshu.home.components.HomeScreenGpsDisabledAlert
import com.vikanshu.home.components.HomeScreenNoDataView
import com.vikanshu.home.components.HomeScreenTopBar
import com.vikanshu.home.components.HomeScreenWeatherCard
import com.vikanshu.home.components.HomeScreenWeatherList
import com.vikanshu.utility.LocationHandler


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    connectivityState: ConnectivityObserver.Status,
    deviceSizeType: DeviceSizeType,
    onSearch: () -> Unit,
    onWeatherDetail: (CurrentWeather) -> Unit,
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    homeViewModel: HomeViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val state by homeViewModel.uiState.collectAsState()

    val lifecycleOwner = rememberUpdatedState(LocalLifecycleOwner.current)

    val locationHandler = LocationHandler(
        context,
        onLocationUpdated = homeViewModel::onUserLocationUpdated,
        onError = homeViewModel::onUserLocationError
    )

    val locationPermissionRequest = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = {
            if (it.containsValue(true)) {
                locationHandler.fetchLocation()
            } else {
                homeViewModel.init()
            }
        }
    )

    LaunchedEffect(key1 = true, key2 = connectivityState) {
        if (locationHandler.isPermissionGranted()) {
            locationHandler.fetchLocation()
        } else {
            locationPermissionRequest.launch(
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
            )
        }
    }

    DisposableEffect(lifecycleOwner.value) {
        val lifecycle = lifecycleOwner.value.lifecycle
        val observer = LifecycleEventObserver { owner, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                if (locationHandler.isPermissionGranted()) {
                    locationHandler.fetchLocation()
                } else {
                    locationPermissionRequest.launch(
                        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
                    )
                }
            }
        }

        lifecycle.addObserver(observer)
        onDispose {
            lifecycle.removeObserver(observer)
        }
    }

    Scaffold(
        modifier = modifier,
        containerColor = Color.White,
    ) {
        when (deviceSizeType) {
            DeviceSizeType.PORTRAIT -> HomeScreenPortrait(
                state,
                onSearch = onSearch,
                onGpsDenied = homeViewModel::onUserDeniedGps,
                onOpenGps = {
                    homeViewModel.onOpenGps()
                    locationHandler.openGpsSettings()
                },
                onWeatherDetail = onWeatherDetail,
            )

            DeviceSizeType.LANDSCAPE -> HomeScreenLandscape(
                state,
                onSearch = onSearch,
                onGpsDenied = homeViewModel::onUserDeniedGps,
                onOpenGps = {
                    homeViewModel.onOpenGps()
                    locationHandler.openGpsSettings()
                },
                onWeatherDetail = onWeatherDetail,
            )

            DeviceSizeType.TABLET -> HomeScreenTablet(
                state,
                onSearch = onSearch,
                onGpsDenied = homeViewModel::onUserDeniedGps,
                onOpenGps = {
                    homeViewModel.onOpenGps()
                    locationHandler.openGpsSettings()
                },
                onWeatherDetail = onWeatherDetail,
            )
        }
    }
}

@Composable
fun HomeScreenPortrait(
    state: HomeUiState,
    onOpenGps: () -> Unit,
    onGpsDenied: () -> Unit,
    onSearch: () -> Unit,
    onWeatherDetail: (CurrentWeather) -> Unit,
) {
    HomeScreenGpsDisabledAlert(show = state.showGpsDialog, onPositive = onOpenGps, onDismiss = onGpsDenied)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        HomeScreenTopBar(isLoading = state.isLoading, onSearch = onSearch)
        if (state.isLoading && state.weather.isEmpty()) UiLoader(modifier = Modifier.padding(bottom = 18.dp))
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
        if (!state.isLoading && state.weather.isEmpty()) HomeScreenNoDataView()
        if (state.weather.isNotEmpty()) {
            HomeScreenWeatherList(
                modifier = Modifier.weight(1f),
                deviceSizeType = DeviceSizeType.PORTRAIT,
                data = state.weather,
                onWeatherDetail = onWeatherDetail
            )
        }
    }
}

@Composable
fun HomeScreenLandscape(
    state: HomeUiState,
    onOpenGps: () -> Unit,
    onGpsDenied: () -> Unit,
    onSearch: () -> Unit,
    onWeatherDetail: (CurrentWeather) -> Unit,
) {
    HomeScreenGpsDisabledAlert(show = state.showGpsDialog, onPositive = onOpenGps, onDismiss = onGpsDenied)
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        HomeScreenTopBar(isLoading = state.isLoading, onSearch = onSearch)
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
        if (state.weather.isNotEmpty()) HomeScreenWeatherList(
            modifier = Modifier.weight(1f),
            deviceSizeType = DeviceSizeType.LANDSCAPE,
            data = state.weather,
            onWeatherDetail = onWeatherDetail
        )
    }
}

@Composable
fun HomeScreenTablet(
    state: HomeUiState,
    onOpenGps: () -> Unit,
    onGpsDenied: () -> Unit,
    onSearch: () -> Unit,
    onWeatherDetail: (CurrentWeather) -> Unit,
) {
    HomeScreenGpsDisabledAlert(show = state.showGpsDialog, onPositive = onOpenGps, onDismiss = onGpsDenied)
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        HomeScreenTopBar(isLoading = state.isLoading, onSearch = onSearch)
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
        if (state.weather.isNotEmpty()) HomeScreenWeatherList(
            modifier = Modifier.weight(1f),
            deviceSizeType = DeviceSizeType.TABLET,
            data = state.weather,
            onWeatherDetail = onWeatherDetail
        )
    }
}


