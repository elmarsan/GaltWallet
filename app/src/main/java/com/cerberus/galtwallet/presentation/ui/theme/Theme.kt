package com.cerberus.galtwallet.presentation.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = BluePrimary,
    primaryVariant = BluePrimary,
    secondary = BlueSeconday
)

@Composable
fun GaltWalletTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = DarkColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}