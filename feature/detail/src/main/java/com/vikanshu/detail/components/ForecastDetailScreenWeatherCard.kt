package com.vikanshu.detail.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.vikanshu.core_ui.ui.SfDisplayProFontFamily
import com.vikanshu.data.local.entity.CurrentWeather
import com.vikanshu.data.local.entity.Forecast

@Composable
fun ForecastDetailScreenWeatherCard(
    forecast: Forecast?,
    currentWeather: CurrentWeather
) {
    Card(
        modifier = Modifier.padding(horizontal = 32.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier
                .padding(horizontal = 32.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                forecast?.weatherDescription ?: currentWeather.weatherDesc,
                fontFamily = SfDisplayProFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp,
                color = Color.Black.copy(alpha = 0.5f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            AsyncImage(
                modifier = Modifier.size(32.dp),
                model = forecast?.weatherIcon ?: currentWeather.weatherIcon,
                contentDescription = ""
            )
        }
        Spacer(modifier = Modifier.height(18.dp))
        Text(
            "${forecast?.tempC ?: currentWeather.temp.tempC}°C",
            modifier = Modifier
                .padding(horizontal = 32.dp),
            fontFamily = SfDisplayProFontFamily,
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(18.dp))
        Row(
            modifier = Modifier
                .padding(horizontal = 32.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "AQI ${forecast?.airQuality?.description ?: currentWeather.airQuality.description}",
                fontFamily = SfDisplayProFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp,
                color = Color(
                    forecast?.airQuality?.color() ?: currentWeather.airQuality.color()
                )
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
    }
}