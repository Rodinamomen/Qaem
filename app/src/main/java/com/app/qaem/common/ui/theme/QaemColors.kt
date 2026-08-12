package com.app.qaem.common.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val LocalQaemColors = staticCompositionLocalOf<QaemColors> { error("Cannot provide colors") }
data class QaemColors(
    val primary: Color,
    val secondary : Color,
    val primaryVariant: Color,
    val text: TextColors,
    val status: StatusColors,
    val background: Color,
)

data class TextColors(
    val text: Color,
    val hint: Color,
)

data class StatusColors(
    val redAccent: Color,
    val yellowAccent: Color,
    val greenAccent: Color,
    val blueAccent: Color,
    val grayAccent: Color,
    val darkGreen: Color,
    val orangeAccent: Color,
    val accentColor: Color,
    val darkBlueAccent: Color,
)

val textLight = TextColors(
    text = Color(0xFF292828),
    hint = Color(0x804E4E4E),
)

val statusLightColors = StatusColors(
    redAccent = Color(0XFFFF4949),
    yellowAccent = Color(0XFFFFCC3D),
    greenAccent = Color(0XFF00E264),
    blueAccent = Color(0XFFC2FFD2),
    grayAccent = Color(0x1A0973BA),
    orangeAccent = Color(0XFFFFF5BC),
    accentColor = Color(0xFFFEB249),
    darkGreen = Color(0xFF309449),
    darkBlueAccent = Color(0xFF0095DF)
)
val textDark = TextColors(
    text = Color(0xFFE0DFDF),
    hint = Color(0x80B0B0B0),
)

val statusDarkColors = StatusColors(
    redAccent = Color(0XFFFF5252),
    yellowAccent = Color(0XFFFFD54F),
    greenAccent = Color(0XFF00E676),
    blueAccent = Color(0XFF69F0AE),
    grayAccent = Color(0XFF42A5F5),
    orangeAccent = Color(0XFFFFD088),
    accentColor = Color(0xFFFEB249),
    darkGreen = Color(0xFF309449),
    darkBlueAccent = Color(0xFF0095DF)
)

val QaemLightColors = QaemColors(
    secondary = Color(0xFF592F16),
    primary = Color(0xFF7C6CFF),
    primaryVariant = Color(0xFFFFCB81),
    background = Color(0xFF01000F),
    text = textLight,
    status = statusLightColors,
)

val QaemDarkColors = QaemColors(
    secondary = Color(0xFF592F16),
    primary = Color(0xFF7C6CFF),
    primaryVariant = Color(0xFFFFCB81),
    background = Color(0xFF01000F),
    text = textDark,
    status = statusDarkColors
)