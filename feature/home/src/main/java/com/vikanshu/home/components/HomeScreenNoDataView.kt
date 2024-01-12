package com.vikanshu.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vikanshu.core_ui.ui.SfDisplayProFontFamily
import com.vikanshu.core_ui.ui.colorA6A6A6

@Composable
fun HomeScreenNoDataView(
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier.size(80.dp),
            imageVector = Icons.Rounded.AddCircle,
            contentDescription = "",
            tint = colorA6A6A6
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = "Add locations to see their weather updates here.",
            textAlign = TextAlign.Center,
            fontFamily = SfDisplayProFontFamily,
            fontSize = 18.sp,
            color = colorA6A6A6
        )
    }
}