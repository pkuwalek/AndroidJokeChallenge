package com.challenge.myapplication.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Core Colors
val Sage = Color(0xFF6B8E7A)
val Sand = Color(0xFFDDB892)
val Copper = Color(0xFFBC6C25)
val WarmBackground = Color(0xFFF9F5F0)
val WhiteSurface = Color(0xFFFFFFFF)

private val LightColorScheme = lightColorScheme(
    primary = Sage,
    onPrimary = Color.White,

    secondary = Sand,
    onSecondary = Color(0xFF3A2A1A),

    tertiary = Copper,
    onTertiary = Color.White,

    background = WarmBackground,
    onBackground = Color(0xFF1C1C1C),

    surface = WhiteSurface,
    onSurface = Color(0xFF1C1C1C),

    error = Color(0xFFB3261E),
    onError = Color.White
)

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF8FAF9C),
    onPrimary = Color(0xFF0F1412),

    secondary = Color(0xFFE6C9A8),
    onSecondary = Color(0xFF1A1410),

    tertiary = Color(0xFFD08C60),
    onTertiary = Color(0xFF1A0F08),

    background = Color(0xFF121715),
    onBackground = Color(0xFFE6EFEA),

    surface = Color(0xFF1B2220),
    onSurface = Color(0xFFE6EFEA),

    error = Color(0xFFF2B8B5),
    onError = Color(0xFF601410)
)

@Composable
fun AndroidChallengeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}