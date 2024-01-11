package com.vikanshu.core_ui.ui

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.vikanshu.core_ui.R

val SfDisplayProFontFamily = FontFamily(
    Font(R.font.sf_pro_display_thin, weight = FontWeight.Thin),
    Font(R.font.sf_pro_display_light, weight = FontWeight.Light),
    Font(R.font.sf_pro_display_regular, weight = FontWeight.Normal),
    Font(R.font.sf_pro_display_medium, weight = FontWeight.Medium),
    Font(R.font.sf_pro_display_semibold, weight = FontWeight.SemiBold),
    Font(R.font.sf_pro_display_bold, weight = FontWeight.Bold),
    Font(R.font.sf_pro_display_black, weight = FontWeight.Black)
)

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)