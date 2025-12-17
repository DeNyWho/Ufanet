package com.example.ufanet.core.uikit.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val DarkColorScheme = darkColorScheme(
    background = Color.White,
    onBackground = Color.Black,
    primary = Primary,
    surface = Gray6,
    onSurface = Color.Black,
    onSurfaceVariant = HighEmphasis,
    outline = Outline,
)

val LightColorScheme = lightColorScheme(
    background = Color.White,
    onBackground = Color.Black,
    primary = Primary,
    surface = Gray6,
    onSurface = Color.Black,
    onSurfaceVariant = HighEmphasis,
    outline = Outline,
)

@Composable
fun UfanetTheme(
    content: @Composable () -> Unit,
) {
    val colorScheme = if (!isSystemInDarkTheme()) {
        LightColorScheme
    } else {
        DarkColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = ufanetTypography(),
        content = content,
    )
}