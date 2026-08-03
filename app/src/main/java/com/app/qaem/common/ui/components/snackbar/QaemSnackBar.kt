package com.app.qaem.common.ui.components.snackbar

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.app.qaem.R
import com.app.qaem.common.ui.components.preview.PreviewAllVariants
import com.app.qaem.common.ui.theme.QaemTheme

@Composable
fun QaemSnackBar(
    message: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    colors: QaemSnackBarColors = QaemSnackBarDefaults.colors(),
    shape: RoundedCornerShape = QaemSnackBarDefaults.shape,
    horizontalArrangement: Dp = QaemSnackBarDefaults.horizontalArrangement,
    boarderWidth: Dp = QaemSnackBarDefaults.borderWidth,
    iconSize: Dp = QaemSnackBarDefaults.iconSize,
) {
    Snackbar(
        modifier = modifier
            .fillMaxWidth()
            .border(width = boarderWidth, color = colors.borderColor, shape = shape),
        shape = shape,
        containerColor = colors.containerColor,
    ) {
        Row(
            Modifier
                .background(color = colors.containerColor),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(horizontalArrangement)
        ) {
            icon?.let {
                Icon(
                    modifier = Modifier.size(iconSize),
                    imageVector = icon,
                    tint = colors.iconColor,
                    contentDescription = null
                )
            }
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = message,
                //  style = QaemTheme.typography.body.medium,
                color = QaemTheme.colors.secondary
            )
        }
    }
}

data class QaemSnackBarColors(
    val containerColor: Color,
    val contentColor: Color,
    val borderColor: Color,
    val iconColor: Color,
)

object QaemSnackBarDefaults {
    val shape = RoundedCornerShape(16.dp)
    val horizontalArrangement = 8.dp
    val borderWidth = 1.dp
    val iconSize = 24.dp

    @Composable
    fun colors(
        containerColor: Color = Color.Unspecified,
        contentColor: Color = Color.Unspecified,
        borderColor: Color = Color.Unspecified,
        iconColor: Color = Color.Unspecified,
    ) = QaemSnackBarColors(
        containerColor = containerColor,
        contentColor = contentColor,
        borderColor = borderColor,
        iconColor = iconColor
    )

}

@PreviewAllVariants
@Composable
private fun QaemSnackBarPreview() = QaemTheme {
    QaemSnackBar(
        message = "Some error happened.",
        icon = ImageVector.vectorResource(R.drawable.ic_snack_bar_fail),
        colors = QaemSnackBarColors(
            containerColor = QaemTheme.colors.background,
            contentColor = QaemTheme.colors.secondary,
            borderColor = QaemTheme.colors.secondary,
            iconColor = QaemTheme.colors.status.redAccent
        ),
    )
}