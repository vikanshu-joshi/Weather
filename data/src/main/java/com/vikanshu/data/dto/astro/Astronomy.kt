package com.vikanshu.data.dto.astro

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.vikanshu.data.dto.AstroDto

@Keep
class Astronomy {

    @SerializedName("astro")
    var astroDto: AstroDto? = AstroDto()

}