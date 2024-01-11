package com.vikanshu.weather.utility

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateUtility {

    private const val FORMAT_HH_MM_AA = "hh:mm aa"

    fun getTimeInHHMMAA(timeInMillis: Long): String {
        val sdf = SimpleDateFormat(FORMAT_HH_MM_AA, Locale.US)
        return sdf.format(Date(timeInMillis))
    }

}