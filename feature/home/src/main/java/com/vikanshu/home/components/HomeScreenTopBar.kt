package com.vikanshu.home.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vikanshu.core_ui.ui.SfDisplayProFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenTopBar(
    isLoading: Boolean,
    onSearch: () -> Unit
) {
    TopAppBar(title = {
        Text(
            text = "Today",
            fontFamily = SfDisplayProFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            color = Color.Black
        )
    }, actions = {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.size(20.dp),
                strokeWidth = 2.dp,
                strokeCap = StrokeCap.Round,
                color = Color.Black
            )
        }
        IconButton(onClick = onSearch) {
            Icon(imageVector = Icons.Outlined.AddCircle, contentDescription = "", tint = Color.Black)
        }
    }, colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White))
}