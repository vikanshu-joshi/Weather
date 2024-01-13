package com.vikanshu.data.dto.forecast

import androidx.annotation.Keep
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


@Keep
class Alerts {

    @Expose
    @SerializedName("alert")
    var alert: ArrayList<String> = arrayListOf()

}