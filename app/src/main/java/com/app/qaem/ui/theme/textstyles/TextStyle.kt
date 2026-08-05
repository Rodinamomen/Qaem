package com.app.qaem.ui.theme.textstyles

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

data class QaemFitTextStyle(
    val headline: TextStyleSizes,
    val title: TextStyleSizes,
    val body: TextStyleSizes,
    val label: TextStyleSizes,
)

@Composable
fun TextStyle(): QaemFitTextStyle {
    val fontFamily = QaemFontFamily()
    return QaemFitTextStyle(
        headline = TextStyleSizes(
            extraLarge = TextStyle(fontSize = 30.sp, fontWeight = FontWeight.ExtraBold),
            large = TextStyle(fontSize = 25.sp, fontWeight = FontWeight.Medium),
            medium = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.Bold),
            small = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold),
        ),


        title = TextStyleSizes(
            extraLarge = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Medium),
            large = TextStyle(fontSize = 22.sp, fontWeight = FontWeight.Medium),
            medium = TextStyle(fontSize = 21.sp, fontWeight = FontWeight.Medium),
            small = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Medium),
        ),

        body = TextStyleSizes(
            extraLarge = TextStyle(fontSize = 28.sp, fontWeight = FontWeight.Normal),
            large = TextStyle(fontSize = 25.sp, fontWeight = FontWeight.Normal),
            medium = TextStyle(fontSize = 21.sp, fontWeight = FontWeight.Normal),
            small = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Medium),
        ),

        label = TextStyleSizes(
            extraLarge = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Normal),
            large = TextStyle(fontSize = 19.sp, fontWeight = FontWeight.Normal),
            medium = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Normal),
            small = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.Normal),
        )
    )
}

val localTextStyle = staticCompositionLocalOf<QaemFitTextStyle> { error("Cannot provide text style") }