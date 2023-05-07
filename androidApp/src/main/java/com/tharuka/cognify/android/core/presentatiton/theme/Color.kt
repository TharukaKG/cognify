package com.tharuka.cognify.android.core.presentatiton.theme

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color
import com.tharuka.cognify.core.presentation.Colors

val DarkBlue = Color(Colors.DarkBlue)
val DarkBlueText = Color(Colors.DarkBlueText)
val Whitish = Color(Colors.Whitish)
val Grayish = Color(0x75EBF0FF)
val LightBlue = Color(Colors.LightBlue)
val LightBlueText = Color(Colors.LightBlueText)


val lightColors = lightColors(
    primary = DarkBlue,
    primaryVariant = DarkBlueText,
    background = Whitish,
    onPrimary = Color.White,
    onBackground = LightBlue,
    surface = Color.White,
    secondary = LightBlue,
    onSecondary = LightBlueText
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