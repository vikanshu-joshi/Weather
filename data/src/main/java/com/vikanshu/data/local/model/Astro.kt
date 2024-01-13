package com.vikanshu.data.local.model

import com.vikanshu.data.dto.AstroDto
import com.vikanshu.data.dto.astro.ResponseAstro

class Astro {

    var sunrise: String = ""
    var sunset: String = ""

    companion object {
        fun fromAstroDto(dto: AstroDto): Astro {
            return Astro().apply {
                sunrise = dto.sunrise ?: ""
                sunset = dto.sunset ?: ""
            }
        }
        fun fromResponseAstroDto(dto: ResponseAstro): Astro {
            return Astro().apply {
                sunrise = dto.astronomy?.astroDto?.sunrise ?: ""
                sunset = dto.astronomy?.astroDto?.sunset ?: ""
            }
        }
    }

}