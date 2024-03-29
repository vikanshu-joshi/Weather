package com.vikanshu.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vikanshu.core_ui.ui.SfDisplayProFontFamily
import com.vikanshu.core_ui.ui.colorA6A6A6
import com.vikanshu.core_ui.ui.colorE8E8E8
import com.vikanshu.data.local.entity.Location

@Composable
fun SearchListCard(
    modifier: Modifier = Modifier,
    location: Location,
    onAdd: () -> Unit
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp)
            ) {
                Text(
                    text = location.name,
                    fontFamily = SfDisplayProFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                    fontSize = 22.sp,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "${if (location.region.isNotBlank()) location.region + "," else ""} ${if (location.region.isNotBlank()) location.country else ""}",
                    fontFamily = SfDisplayProFontFamily,
                    fontWeight = FontWeight.Medium,
                    color = colorA6A6A6
                )
            }
            TextButton(onClick = onAdd) {
                Text(
                    text = "Add",
                    fontFamily = SfDisplayProFontFamily,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
        }
    }
}


@Preview
@Composable
fun SearchListCardPreview() {
    SearchListCard(
        modifier = Modifier.fillMaxWidth(),
        location = Location(
            name = "Gurgaon",
            region = "Haryana",
            country = "India",
            lat = 0.0,
            lon = 0.0
        )
    ) {

    }
}