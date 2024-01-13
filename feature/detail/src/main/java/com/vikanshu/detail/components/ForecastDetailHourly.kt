package com.vikanshu.detail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.vikanshu.data.local.model.ForecastHour
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun ForecastDetailHourly(
    hour: ForecastHour
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = SimpleDateFormat("haa", Locale.getDefault()).format(hour.time),
            modifier = Modifier.padding(horizontal = 12.dp),
            fontFamily = SfDisplayProFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
        )
        Spacer(modifier = Modifier.height(4.dp))
        AsyncImage(
            modifier = Modifier.size(35.dp),
            model = hour.weatherIcon,
            contentDescription = ""
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "${hour.tempC}°",
            fontFamily = SfDisplayProFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(20.dp))
    }
}