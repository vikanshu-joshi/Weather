package com.vikanshu.weather.utility

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.provider.Settings
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

enum class LocationErrorCode {
    UNKNOWN,
    GPS_DISABLED,
    PERMISSION_DENIED
}

class LocationHandler(
    private val context: Context,
    private val onLocationUpdated: (Location) -> Unit,
    private val onError: (LocationErrorCode) -> Unit
) {

    private val permissions = arrayOf(
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION
    )

    fun fetchLocation() {
        if (!isPermissionGranted()) {
            onError(LocationErrorCode.PERMISSION_DENIED)
            return
        }
        if (!isGpsEnabled()) {
            onError(LocationErrorCode.GPS_DISABLED)
            return
        }
        getCurrentLocation()
    }


    @SuppressLint("MissingPermission")
    private fun getCurrentLocation() {
        if (!isPermissionGranted()) return
        val fusedLocationClient: FusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(context)
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location ->
                if (location != null) {
                    onLocationUpdated(location)
                } else {
                    onError(LocationErrorCode.UNKNOWN)
                }
            }
            .addOnFailureListener { e ->
                onError(LocationErrorCode.UNKNOWN)
            }
    }

    fun openGpsSettings() {
        val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
        context.startActivity(intent)
    }

    private fun isGpsEnabled(): Boolean {
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }

    private fun isPermissionGranted() = permissions.all {
        ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
    }

}