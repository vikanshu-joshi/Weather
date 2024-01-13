package com.vikanshu.detail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vikanshu.core_ui.R
import com.vikanshu.core_ui.ui.SfDisplayProFontFamily
import com.vikanshu.core_ui.ui.color0076FF
import com.vikanshu.data.local.entity.CurrentWeather
import com.vikanshu.data.local.model.Astro
import kotlin.math.roundToInt

@Composable
fun ForecastDetailMoreInfo(
    currentWeather: CurrentWeather,
    astro: Astro?
) {
    Column(
        modifier = Modifier
            .padding(top = 40.dp, start = 35.dp, end = 35.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = "More Info",
            fontFamily = SfDisplayProFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(32.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (astro != null) {
                    ForecastDetailInfoCardItem(
                        title = "Sunrise",
                        icon = painterResource(id = R.drawable.ic_weather_sunrise),
                        value = astro.sunrise,
                        valueColor = color0076FF
                    )
                    Spacer(modifier = Modifier.height(36.dp))
                }
                ForecastDetailInfoCardItem(
                    title = "Humidity",
                    icon = painterResource(id = R.drawable.ic_weather_drop),
                    value = "${currentWeather.humidity.toInt()}%",
                    valueColor = color0076FF
                )
                Spacer(modifier = Modifier.height(36.dp))
                ForecastDetailInfoCardItem(
                    title = "Wind",
                    icon = null,
                    value = "${currentWeather.wind.windDir} ${currentWeather.wind.windMph.roundToInt()} mph",
                    valueColor = color0076FF
                )
                Spacer(modifier = Modifier.height(36.dp))
                ForecastDetailInfoCardItem(
                    title = "Air Quality Index",
                    icon = null,
                    value = "${currentWeather.airQuality.id}",
                    valueColor = Color(currentWeather.airQuality.color())
                )
                Spacer(modifier = Modifier.height(36.dp))
                ForecastDetailInfoCardItem(
                    title = "Precipitation",
                    icon = null,
                    value = "${currentWeather.precipitation.precipitationInInches.roundToInt()} in",
                    valueColor = color0076FF
                )
            }
            Spacer(modifier = Modifier.height(18.dp))
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (astro != null) {
                    ForecastDetailInfoCardItem(
                        title = "Sunset",
                        icon = painterResource(id = R.drawable.ic_weather_sunset),
                        value = astro.sunset,
                        valueColor = color0076FF
                    )
                    Spacer(modifier = Modifier.height(36.dp))
                }
                ForecastDetailInfoCardItem(
                    title = "Feels Like",
                    icon = painterResource(id = R.drawable.ic_weather_feels_like),
                    value = "${currentWeather.temp.feelsLikeC}°C",
                    valueColor = color0076FF
                )
                Spacer(modifier = Modifier.height(36.dp))
                ForecastDetailInfoCardItem(
                    title = "Visibility",
                    icon = null,
                    value = "${currentWeather.visibility.visibilityKm.roundToInt()} kms",
                    valueColor = color0076FF
                )
                Spacer(modifier = Modifier.height(36.dp))
                ForecastDetailInfoCardItem(
                    title = "Air Quality",
                    icon = null,
                    value = "${currentWeather.airQuality.description}",
                    valueColor = Color(currentWeather.airQuality.color())
                )
                Spacer(modifier = Modifier.height(36.dp))
                ForecastDetailInfoCardItem(
                    title = "Pressure",
                    icon = null,
                    value = "${currentWeather.pressure.pressureInInches.roundToInt()} in",
                    valueColor = color0076FF
                )
            }
        }
    }
}

@Composable
fun ForecastDetailInfoCardItem(
    title: String,
    icon: Painter?,
    value: String,
    valueColor: Color,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            fontFamily = SfDisplayProFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(10.dp))
        if (icon != null) {
            Image(
                modifier = Modifier.size(width = 40.dp, height = 36.dp),
                painter = icon,
                contentDescription = ""
            )
            Spacer(modifier = Modifier.height(10.dp))
        }
        Text(
            text = value,
            fontFamily = SfDisplayProFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = valueColor
        )
    }
}