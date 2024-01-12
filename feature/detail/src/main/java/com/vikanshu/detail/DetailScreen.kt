package com.vikanshu.detail

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vikanshu.core_ui.ui.SfDisplayProFontFamily
import com.vikanshu.core_ui.ui.color0076FF

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true)
@Composable
fun DetailScreen(

) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            TopAppBar(title = {
                Text(
                    text = "Gurgaon",
                    fontFamily = SfDisplayProFontFamily,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
            }, navigationIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "",
                        tint = Color.Black
                    )
                }
            }, actions = {
                Text(
                    "--:-- --",
                    modifier = Modifier.padding(start = 32.dp),
                    textAlign = TextAlign.End,
                    fontFamily = SfDisplayProFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp,
                    color = color0076FF
                )
                Spacer(modifier = Modifier.width(16.dp))
            })
        }
        item {
            Spacer(modifier = Modifier.height(12.dp))
        }
        item {
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
                        "--",
                        fontFamily = SfDisplayProFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 17.sp,
                        color = Color.Black.copy(alpha = 0.5f)
                    )
                    Spacer(modifier = Modifier.width(8.dp))

                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        "--:-- --",
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
                    "--°C",
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
                        "Today, Mostly Sunny currently. It’s 56° and the high will be 59°.",
                        fontFamily = SfDisplayProFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 17.sp,
                        color = Color.Black.copy(alpha = 0.5f)
                    )
                }
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
        item {
            Spacer(modifier = Modifier.height(12.dp))
        }
        items(count = 7) {
            Row {
                Text(
                    "Monday",
                    fontFamily = SfDisplayProFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp,
                    color = Color.Black.copy(alpha = 0.5f)
                )
            }
        }
    }
}