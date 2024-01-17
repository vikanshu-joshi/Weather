package com.vikanshu.weather.resource

import android.Manifest
import android.app.Application
import android.content.pm.PackageManager
import android.location.Location
import android.util.Log
import androidx.core.app.NotificationManagerCompat
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.vikanshu.utility.LocationHandler
import com.vikanshu.weather.worker.MorningAQIUpdateWorker
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class WeatherApplication : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory : HiltWorkerFactory

    private val locationHandler by lazy {
        LocationHandler(this@WeatherApplication,
            onLocationUpdated = {
                scheduleMonthlyAQIUpdateWorker(it)
            }, onError = { })
    }

    override fun onCreate() {
        super.onCreate()
        if (isLocationPermissionGranted() && areNotificationsEnabled()) {
            locationHandler.fetchLocation()
        } else {
            WorkManager.getInstance(this).cancelAllWorkByTag(MorningAQIUpdateWorker.WORK_NAME)
        }
    }

    private fun areNotificationsEnabled(): Boolean {
        return NotificationManagerCompat.from(this).areNotificationsEnabled()
    }

    private fun isLocationPermissionGranted(): Boolean {
        return checkCallingOrSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
                checkCallingOrSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
    }

    private fun scheduleMonthlyAQIUpdateWorker(location: Location) {
        WorkManager.getInstance(this)
            .enqueueUniquePeriodicWork(
                MorningAQIUpdateWorker.WORK_NAME,
                ExistingPeriodicWorkPolicy.UPDATE,
                MorningAQIUpdateWorker.createPeriodicWorkRequest(
                    location.latitude,
                    location.longitude
                )
            )
    }

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()

}