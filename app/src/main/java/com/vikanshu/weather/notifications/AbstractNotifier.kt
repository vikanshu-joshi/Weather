package com.vikanshu.weather.notifications

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.NotificationCompat
import com.vikanshu.weather.R

abstract class AbstractNotifier<T> {

    abstract val CHANNEL_ID: String

    abstract val CHANNEL_NAME: String

    abstract val CHANNEL_DESCRIPTION: String

    abstract val NOTIFICATION_ID: Int

    fun notify(context: Context, data: T) {
        if (context.checkCallingOrSelfPermission(Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            return
        }
        createNotificationChannel(context)
        val notificationBuilder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(getNotificationTitle(data))
            .setContentText(getNotificationContent(data))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
        notificationBuilder.setContentIntent(getPendingIntent(context))
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(
            NOTIFICATION_ID,
            notificationBuilder.build()
        )
    }

    fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = CHANNEL_ID
            val channelName = CHANNEL_NAME
            val channelDescription = CHANNEL_DESCRIPTION
            val importance = NotificationManager.IMPORTANCE_DEFAULT

            val channel = NotificationChannel(channelId, channelName, importance)
            channel.description = channelDescription

            val notificationManager = context.getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }

    abstract fun getNotificationTitle(data: T): String

    abstract fun getNotificationContent(data: T): String

    abstract fun getPendingIntent(context: Context): PendingIntent

}