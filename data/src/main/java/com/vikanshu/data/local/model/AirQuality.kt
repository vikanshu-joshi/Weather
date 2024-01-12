package com.vikanshu.data.local.model

import android.graphics.Color
import androidx.annotation.ColorInt
import com.vikanshu.data.dto.Current

enum class AirQuality(val id: Int, val description: String) {

    Good(1, "Good") {
        override fun color(): Int {
            return Color.parseColor("#00E400")
        }
    },
    Moderate(2, "Moderate") {
        override fun color(): Int {
            return Color.parseColor("#FFB100")
        }
    },
    Unhealthy_for_sensitive_group(3, "Unhealthy for sensitive group") {
        override fun color(): Int {
            return Color.parseColor("#FF7E00")
        }
    },
    Unhealthy(4, "Unhealthy") {
        override fun color(): Int {
            return Color.parseColor("#FF0000")
        }
    },
    Very_Unhealthy(5, "Very Unhealthy") {
        override fun color(): Int {
            return Color.parseColor("#99004C")
        }
    },
    Hazardous(6, "Hazardous") {
        override fun color(): Int {
            return Color.parseColor("#7E0023")
        }
    };

    @ColorInt
    abstract fun color(): Int

    companion object {
        fun fromId(id: Int): AirQuality {
            for (i in AirQuality.entries) {
                if (i.id == id) return i
            }
            return Moderate
        }

        fun fromCurrentDto(current: Current?): AirQuality {
            return fromId(current?.airQualityDto?.usEpaIndex ?: Moderate.id)
        }
    }


}