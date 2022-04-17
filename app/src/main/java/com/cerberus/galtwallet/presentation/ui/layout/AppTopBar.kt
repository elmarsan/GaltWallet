package com.cerberus.galtwallet.presentation.ui.layout

import androidx.compose.foundation.clickable
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AppTopBar(
    onGoBack: (() -> Unit?)? = null,
    header: String
) {
    TopAppBar {
        if (onGoBack != null) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Arrow back",
                modifier = Modifier.clickable { onGoBack() }
            )
        }

        Text(text = header)
    }
}
