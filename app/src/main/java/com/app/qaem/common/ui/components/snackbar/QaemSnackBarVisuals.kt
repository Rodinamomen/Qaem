package com.app.qaem.common.ui.components.snackbar


import androidx.annotation.DrawableRes
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarVisuals
import androidx.compose.runtime.Composable
import com.app.qaem.R
import com.app.qaem.common.ui.message.MessageType
import com.app.qaem.common.ui.theme.QaemTheme

data class QaemSnackBarVisuals(
    val messageType: MessageType,
    override val actionLabel: String,
    override val duration: SnackbarDuration,
    override val message: String,
    override val withDismissAction: Boolean,
) : SnackbarVisuals {
    val colors: QaemSnackBarColors
        @Composable
        get() = when (messageType) {
            MessageType.SUCCESS -> QaemSnackBarColors(
                containerColor = QaemTheme.colors.background,
                contentColor = QaemTheme.colors.secondary,
                borderColor = QaemTheme.colors.secondary,
                iconColor = QaemTheme.colors.status.greenAccent
            )

            MessageType.ERROR -> QaemSnackBarColors(
                containerColor = QaemTheme.colors.background,
                contentColor = QaemTheme.colors.secondary,
                borderColor = QaemTheme.colors.primary,
                iconColor = QaemTheme.colors.status.redAccent
            )


            MessageType.RETRY -> QaemSnackBarColors(
                containerColor = QaemTheme.colors.background,
                contentColor = QaemTheme.colors.secondary,
                borderColor =QaemTheme.colors.primary,
                iconColor = QaemTheme.colors.status.redAccent
            )

            MessageType.DEFAULT ->  QaemSnackBarColors(
                containerColor = QaemTheme.colors.background,
                contentColor = QaemTheme.colors.secondary,
                borderColor = QaemTheme.colors.primary,
                iconColor = QaemTheme.colors.status.greenAccent
            )
        }
    val iconRes : Int
        @DrawableRes get() = when(messageType) {
            MessageType.SUCCESS -> R.drawable.ic_snack_bar_success
            MessageType.ERROR -> R.drawable.ic_snack_bar_fail
            MessageType.RETRY ->  R.drawable.ic_snack_bar_fail
            MessageType.DEFAULT -> R.drawable.ic_snack_bar_success
        }
}