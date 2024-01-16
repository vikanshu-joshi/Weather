package com.vikanshu.weather.notifications

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.vikanshu.data.local.entity.CurrentWeather
import com.vikanshu.weather.ui.activity.MainActivity

class MorningAQINotification : AbstractNotifier<CurrentWeather>() {

    override val CHANNEL_ID: String
        get() = MorningAQINotification::class.java.simpleName

    override val CHANNEL_NAME: String
        get() = "AQI Notification"

    override val CHANNEL_DESCRIPTION: String
        get() = "Get everyday AQI updates"

    override val NOTIFICATION_ID: Int
        get() = 12345

    override fun getPendingIntent(context: Context): PendingIntent {
        val intent = Intent(context, MainActivity::class.java)
        return PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
    }

    override fun getNotificationTitle(data: CurrentWeather): String {
        return "Today's temperature in ${data.location.name} is ${data.temp.tempC}°C"
    }

    override fun getNotificationContent(data: CurrentWeather): String {
        return "Air quality is ${data.airQuality.description}, Visibility upto ${data.visibility.visibilityKm} kms"
    }

}