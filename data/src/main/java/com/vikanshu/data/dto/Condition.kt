package com.vikanshu.data.dto

import androidx.annotation.Keep
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Keep
class Condition public constructor() {

    @Expose
    @SerializedName("text") var text: String? = null
    @Expose
    @SerializedName("icon") var icon: String? = null
    @Expose
    @SerializedName("code") var code: Int? = null

}