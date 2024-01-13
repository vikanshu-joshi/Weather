package com.vikanshu.data.dto.search

import androidx.annotation.Keep
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Keep
class ResponseSearchLocation public constructor() {

    @Expose
    @SerializedName("id")
    var id: Int? = null
    @Expose
    @SerializedName("name")
    var name: String? = null
    @Expose
    @SerializedName("region")
    var region: String? = null
    @Expose
    @SerializedName("country")
    var country: String? = null
    @Expose
    @SerializedName("lat")
    var lat: Double? = null
    @Expose
    @SerializedName("lon")
    var lon: Double? = null
    @Expose
    @SerializedName("url")
    var url: String? = null

}