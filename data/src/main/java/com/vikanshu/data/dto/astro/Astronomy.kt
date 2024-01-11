package com.vikanshu.data.dto.astro

import com.google.gson.annotations.SerializedName
import com.vikanshu.data.dto.Astro


data class Astronomy(

    @SerializedName("astro") var astro: Astro? = Astro()

)