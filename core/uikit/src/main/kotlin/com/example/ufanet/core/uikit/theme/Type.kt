package com.example.ufanet.core.uikit.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.LineHeightStyle.Alignment
import androidx.compose.ui.text.style.LineHeightStyle.Trim
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.ufanet.core.uikit.R

private val RobotoFontFamily = FontFamily(
    Font(R.font.roboto_regular, FontWeight.Normal, FontStyle.Normal),
)

internal fun ufanetTypography(): Typography {
    return Typography(
        bodyLarge = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.15.sp,
            fontFamily = RobotoFontFamily,
        ),
        bodyMedium = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            lineHeight = 1.em,
            letterSpacing = 0.sp,
            fontFamily = RobotoFontFamily,
        ),
        labelMedium = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.4.sp,
            fontFamily = RobotoFontFamily,
        ),
    )
}