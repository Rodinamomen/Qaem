package com.app.qaem.ui.theme.textstyles

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.app.qaem.R

@Composable
fun QaemFontFamily(): FontFamily {
    return FontFamily(
        Font(R.font.thmanyah_sans_bold, FontWeight.Bold),
        Font(R.font.thmanyah_sans_medium, FontWeight.Medium),
        Font(R.font.thmanyah_sans_regular, FontWeight.Normal),
        Font(R.font.thmanyah_sans_black, FontWeight.ExtraBold),
        )
}