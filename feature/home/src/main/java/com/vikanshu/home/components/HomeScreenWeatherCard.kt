package com.vikanshu.home.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.vikanshu.core_ui.ui.SfDisplayProFontFamily
import com.vikanshu.core_ui.ui.color0076FF
import com.vikanshu.data.local.entity.CurrentWeather
import com.vikanshu.data.local.entity.Location
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun HomeScreenWeatherCard(
    modifier: Modifier = Modifier,
    isCurrentLocation: Boolean,
    location: Location,
    currentWeather: CurrentWeather?
) {
    Card(
        modifier = modifier
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        Row(
            modifier = Modifier
                .padding(horizontal = 32.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                currentWeather?.weatherDesc ?: "--",
                fontFamily = SfDisplayProFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp,
                color = Color.Black.copy(alpha = 0.5f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            AsyncImage(
                modifier = Modifier.size(32.dp),
                model = currentWeather?.weatherIcon,
                contentDescription = ""
            )
            Spacer(modifier = Modifier.weight(1f),)
            Text(
                if (currentWeather?.timestamp != null) SimpleDateFormat(
                    "hh:mm aa",
                    Locale.US
                ).format(currentWeather.timestamp) else "--:-- --",
                modifier = Modifier.padding(start = 32.dp),
                textAlign = TextAlign.End,
                fontFamily = SfDisplayProFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp,
                color = color0076FF
            )
        }
        Spacer(modifier = Modifier.height(18.dp))
        Text(
            "${currentWeather?.temp?.tempC ?: "--"}°C",
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
                location.name,
                fontFamily = SfDisplayProFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp,
                color = Color.Black.copy(alpha = 0.5f)
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
    }
}