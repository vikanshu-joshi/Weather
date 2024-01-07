package com.vikanshu.data.resource

object Utility {

    fun getImageIconUrl(icon: String?): String {
        return if (icon != null) "https://openweathermap.org/img/wn/${icon}d@4x.png" else ""
    }

}