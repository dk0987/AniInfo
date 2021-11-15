package com.devdk.aniinfo.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(

    background = background,
    onBackground = textWhite
    /* Other default colors to override
      background = Color.White,
      surface = Color.White,
      onPrimary = Color.White,
      onSecondary = Color.Black,
      onBackground = Color.Black,
      onSurface = Color.Black,
      */
)

@Composable
fun AniInfoTheme( content: @Composable () -> Unit) {
    val colors = DarkColorPalette
    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}