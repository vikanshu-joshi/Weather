package com.vikanshu.data.dto.astro

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.vikanshu.data.dto.Astro

@Keep
class Astronomy {

    @SerializedName("astro")
    var astro: Astro? = Astro()

}