package com.app.qaem.common.ui.components.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.app.qaem.common.ui.components.preview.PreviewAllVariants
import com.app.qaem.common.ui.theme.QaemTheme

@Composable
fun QaemScreen(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colorStops = arrayOf(
                        0.0f to QaemTheme.colors.status.purpleAccent,
                        0.2f to QaemTheme.colors.background,
                        1.0f to QaemTheme.colors.background,
                    )
                )
            )
            .padding(horizontal = 40.dp),

        ) {
        content()
    }
}

@Composable
@PreviewAllVariants
private fun QaemScreenPreview() = QaemTheme() {
    QaemScreen(){}
}