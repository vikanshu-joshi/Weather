package com.vikanshu.data.dto.forecast

import androidx.annotation.Keep
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.vikanshu.data.dto.Astro

@Keep
class Forecastday {

    @Expose
    @SerializedName("date")
    var date: String? = null
    @Expose
    @SerializedName("date_epoch")
    var dateEpoch: Int? = null
    @Expose
    @SerializedName("day")
    var day: Day? = Day()
    @Expose
    @SerializedName("astro")
    var astro: Astro? = Astro()
    @Expose
    @SerializedName("hour")
    var hour: ArrayList<Hour> = arrayListOf()

}