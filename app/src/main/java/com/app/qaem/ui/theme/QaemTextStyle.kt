package com.app.qaem.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.app.qaem.R

val localQaemTypography =
    staticCompositionLocalOf<QaemFitTextStyle> { error("Cannot provide text style") }
val thmanyah = FontFamily(
    Font(R.font.thmanyah_sans_bold, FontWeight.Bold),
    Font(R.font.thmanyah_sans_medium, FontWeight.Medium),
    Font(R.font.thmanyah_sans_regular, FontWeight.Normal),
    Font(R.font.thmanyah_sans_black, FontWeight.ExtraBold),
)

data class SizedTypography(
    val extraLarge: TextStyle,
    val large: TextStyle,
    val medium: TextStyle,
    val small: TextStyle,
)

data class QaemFitTextStyle(
    val headline: SizedTypography,
    val title: SizedTypography,
    val body: SizedTypography,
    val label: SizedTypography,
)

private val headline = SizedTypography(
    extraLarge = TextStyle(
        fontSize = 30.sp,
        fontWeight = FontWeight.ExtraBold,
        lineHeight = 42.sp,
        fontFamily = thmanyah

    ),
    large = TextStyle(
        fontSize = 15.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 42.sp,
        fontFamily = thmanyah
    ),
    medium = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = thmanyah,
        lineHeight = 24.sp
    ),
    small = TextStyle(
        fontSize = 25.sp,
        fontWeight = FontWeight.Medium,
        fontFamily = thmanyah,
        lineHeight = 42.sp
    ),
)

private val title = SizedTypography(
    extraLarge = TextStyle(
        fontSize = 24.sp,
        fontWeight = FontWeight.Medium,
        fontFamily = thmanyah,
        lineHeight = 36.sp
    ),
    large = TextStyle(
        fontSize = 22.sp,
        fontWeight = FontWeight.Medium,
        fontFamily = thmanyah,
        lineHeight = 30.sp
    ),
    medium = TextStyle(
        fontSize = 21.sp,
        fontWeight = FontWeight.Medium,
        fontFamily = thmanyah,
        lineHeight = 30.sp
    ),
    small = TextStyle(
        fontSize = 20.sp,
        fontWeight = FontWeight.Medium,
        fontFamily = thmanyah,
        lineHeight = 28.sp
    ),
)

private val body = SizedTypography(
    extraLarge = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.Medium,
        fontFamily = thmanyah,
        lineHeight = 24.sp
    ),
    large = TextStyle(
        fontSize = 28.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = thmanyah,
        lineHeight = 22.sp
    ),
    medium = TextStyle(
        fontSize = 25.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = thmanyah,
        lineHeight = 16.sp
    ),
    small = TextStyle(
        fontSize = 21.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = thmanyah,
        lineHeight = 42.sp
    ),
)

private val label = SizedTypography(
    extraLarge = TextStyle(
        fontSize = 20.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = thmanyah,
        lineHeight = 42.sp
    ),
    large = TextStyle(
        fontSize = 19.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = thmanyah,
        lineHeight = 28.sp
    ),
    medium = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = thmanyah,
        lineHeight = 24.sp
    ),
    small = TextStyle(
        fontSize = 15.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = thmanyah,
        lineHeight = 22.sp
    ),
)

val thmanyahTypography = QaemFitTextStyle(
    headline = headline,
    title = title,
    body = body,
    label = label
)