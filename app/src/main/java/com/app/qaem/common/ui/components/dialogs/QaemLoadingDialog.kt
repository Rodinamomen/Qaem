package com.app.qaem.common.ui.components.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.app.qaem.common.ui.components.preview.PreviewAllVariants
import com.app.qaem.common.ui.theme.QaemTheme

@Composable
fun QaemLoadingDialog(modifier: Modifier = Modifier) {
    Dialog(onDismissRequest = {}) {
        Box(
            modifier = modifier
                .background(color = QaemTheme.colors.background, shape = RoundedCornerShape(24.dp))
                .padding(50.dp)
        ) {
            CircularProgressIndicator(color = QaemTheme.colors.primary)
        }
    }
}

@PreviewAllVariants
@Composable
private fun QaemLoadingDialogPreview() = QaemTheme {
    QaemLoadingDialog()
}