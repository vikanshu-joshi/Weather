package com.vikanshu.weather.utility

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

object DateUtility {

    private const val FORMAT_HH_MM_AA = "hh:mm aa"

    fun getCurrentTime(): Date {
        return Date(System.currentTimeMillis())
    }

    fun getTimeInHHMMAA(timeInMillis: Long): String {
        val sdf = SimpleDateFormat(FORMAT_HH_MM_AA, Locale.US)
        return sdf.format(Date(timeInMillis))
    }

    fun getDifferenceInHoursFromNow(timeInMillis: Long): Long {
        val currentTime = System.currentTimeMillis()
        return (currentTime - timeInMillis) / (1000 * 60 * 60)
    }

    fun getDifferenceInDaysFromNow(timeInMillis: Long): Long {
        val currentTime = System.currentTimeMillis()
        return (currentTime - timeInMillis) / (1000 * 60 * 60 * 24)
    }

    fun getMillisForNextHour(hourOfDay: Int): Long {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)

        if (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) >= hourOfDay) {
            calendar.add(Calendar.DAY_OF_MONTH, 1)
        }

        return calendar.timeInMillis
    }
}