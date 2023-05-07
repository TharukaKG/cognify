package com.tharuka.cognify.android.core.presentatiton.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tharuka.cognify.android.R

@Composable
fun CognifyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        darkColors
    } else {
        lightColors
    }

    val openSansText = FontFamily(
        Font(
            resId = R.font.open_sans_light,
            weight = FontWeight.Light
        ),
        Font(
            resId = R.font.open_sans_regular,
            weight = FontWeight.Normal
        )
    )

    val typography = Typography(
        body1 = TextStyle(
            fontFamily = openSansText,
            fontWeight = FontWeight.Normal,
            fontSize = 24.sp,
            color = colors.primaryVariant
        ),
        body2 = TextStyle(
            fontFamily = openSansText,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        ),
        button = TextStyle(
            fontFamily = openSansText,
            fontWeight = FontWeight.Normal,
            fontSize = 24.sp,
            color = colors.onPrimary
        )
    )
    val shapes = Shapes(
        small = RoundedCornerShape(4.dp),
        medium = RoundedCornerShape(8.dp),
        large = RoundedCornerShape(12.dp)
    )

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}
