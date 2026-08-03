package com.app.qaem.common.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable

@Composable
fun QaemTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colors = if (isDarkTheme) QaemDarkColors else QaemLightColors
    CompositionLocalProvider(
        LocalQaemColors provides colors,
    ) {
        content()
    }
}

object QaemTheme {
    val colors: QaemColors
        @Composable
        @ReadOnlyComposable get() = LocalQaemColors.current
}