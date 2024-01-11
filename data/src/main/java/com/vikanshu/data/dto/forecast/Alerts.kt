package com.vikanshu.data.dto.forecast

import com.google.gson.annotations.SerializedName


data class Alerts(

    @SerializedName("alert") var alert: ArrayList<String> = arrayListOf()

)