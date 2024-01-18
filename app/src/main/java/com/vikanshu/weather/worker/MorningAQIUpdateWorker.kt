package com.vikanshu.weather.worker

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkerParameters
import com.vikanshu.data.local.entity.CurrentWeather
import com.vikanshu.data.repository.WeatherRepository
import com.vikanshu.data.resource.CommunicationResult
import com.vikanshu.weather.notifications.MorningAQINotification
import com.vikanshu.weather.utility.DateUtility
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import java.util.concurrent.TimeUnit

@HiltWorker
class MorningAQIUpdateWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted private val workerParameters: WorkerParameters,
    private val weatherRepository: WeatherRepository
) : CoroutineWorker(context, workerParameters) {

    companion object {
        const val WORK_NAME = "MorningAQIUpdateWorker"
        const val EXTRA_LAT = "EXTRA_LAT"
        const val EXTRA_LON = "EXTRA_LON"

        fun createPeriodicWorkRequest(lat: Double, lon: Double): PeriodicWorkRequest {
            val constraints = Constraints.Builder()
                .setRequiresBatteryNotLow(true)
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()
            return PeriodicWorkRequestBuilder<MorningAQIUpdateWorker>(
                repeatInterval = 15,
                repeatIntervalTimeUnit = TimeUnit.MINUTES,
                flexTimeInterval = 5,
                flexTimeIntervalUnit = TimeUnit.MINUTES
            )
                .addTag(WORK_NAME)
                .setConstraints(constraints)
                .setNextScheduleTimeOverride(DateUtility.getMillisForNextHour(9))
                .setInputData(
                    Data.Builder()
                        .putDouble(EXTRA_LAT, lat)
                        .putDouble(EXTRA_LON, lon)
                        .build()
                )
                .build()
        }
    }

    override suspend fun doWork(): Result {
        val lat = inputData.getDouble(EXTRA_LAT, -1.0)
        val lon = inputData.getDouble(EXTRA_LON, -1.0)
        if (lat == -1.0 || lon == -1.0) {
            return Result.failure(
                Data.Builder()
                    .putDouble(EXTRA_LAT, lat)
                    .putDouble(EXTRA_LON, lon)
                    .putString("Error", "Lat Long Error")
                    .build()
            )
        }
        val result = weatherRepository.getCurrentWeather("$lat,$lon")
        if (result is CommunicationResult.Error) {
            return Result.retry()
        }
        sendNotification((result as CommunicationResult.Success).data)
        return Result.success()
    }

    private fun sendNotification(data: CurrentWeather) {
        MorningAQINotification().notify(context, data)
    }


}