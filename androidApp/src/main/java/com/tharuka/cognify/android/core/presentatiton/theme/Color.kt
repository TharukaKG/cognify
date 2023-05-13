package com.tharuka.cognify.android.core.presentatiton.theme

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color
import com.tharuka.cognify.core.presentation.Colors

val DarkBlue = Color(Colors.DarkBlue)
val DarkBlueText = Color(Colors.DarkBlueText)
val Whitish = Color(Colors.Whitish)
val Grayish = Color(Colors.Grayish).copy(alpha = 0.40f)
val LightBlue = Color(Colors.LightBlue).copy(alpha = 0.40f)
val LightBlueText = Color(Colors.LightBlueText)


val lightColors = lightColors(
    primary = DarkBlue,
    onPrimary = Color.White,
    primaryVariant = DarkBlueText,
    background = Whitish,
    onBackground = LightBlue,
    secondary = LightBlue,
    onSecondary = LightBlueText,
    surface = Color.White
)

val darkColors = darkColors(
    primary = DarkBlue,
    primaryVariant = DarkBlueText,
    background = Whitish,
    onPrimary = Color.White,
    onBackground = LightBlue,
    surface = Color.White,
    secondary = LightBlue,
    onSecondary = LightBlueText,
)