package com.app.qaem.ui.theme.textstyles

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
data class HeadlineTextStyle(
    val extraBold30: TextStyle,
    val largeBold15: TextStyle,
    val largeBold14: TextStyle,
    val mediumMedium25: TextStyle,
)
data class TitleTextStyle(
    val mediumMedium24: TextStyle,
    val mediumMedium22: TextStyle,
    val mediumMedium21: TextStyle,
    val mediumMedium20: TextStyle,
)
data class BodyTextStyle(
    val mediumMedium18: TextStyle,
    val smallRegular28: TextStyle,
    val smallRegular25: TextStyle,
    val smallRegular21: TextStyle,
)
data class LabelTextStyle(
    val smallRegular20: TextStyle,
    val smallRegular19: TextStyle,
    val smallRegular16: TextStyle,
    val smallRegular15: TextStyle,
)
data class QaemFitTextStyle(
    val headline: HeadlineTextStyle,
    val title: TitleTextStyle,
    val label: LabelTextStyle,
    val body: BodyTextStyle,
)

@Composable
fun TextStyle(): QaemFitTextStyle {
    val fontFamily = QaemFontFamily()
    return QaemFitTextStyle(
        headline = HeadlineTextStyle(
            extraBold30 = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 30.sp
            ),
            largeBold15 = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp
            ),
            largeBold14 = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            ),
            mediumMedium25 = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 25.sp
            )
        ),

        title = TitleTextStyle(
            mediumMedium24 = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 24.sp
            ),
            mediumMedium22 = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 22.sp
            ),
            mediumMedium21 = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 21.sp
            ),
            mediumMedium20 = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp
            )
        ),

        body = BodyTextStyle(
            mediumMedium18 = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp
            ),
            smallRegular28 = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 28.sp
            ),
            smallRegular25 = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 25.sp
            ),
            smallRegular21 = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 21.sp
            )
        ),

        label = LabelTextStyle(
            smallRegular20 = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 20.sp
            ),
            smallRegular19 = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 19.sp
            ),
            smallRegular16 = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp
            ),
            smallRegular15 = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 15.sp
            )
        )
    )
}
val localTextStyle = staticCompositionLocalOf<QaemFitTextStyle> { error("Cannot provide text style") }