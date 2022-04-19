package com.cerberus.galtwallet.presentation.settings.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SettingsOptionGroup(
    title: String,
    items: @Composable () -> Unit
) {
    Text(
        text = title,
        style = MaterialTheme.typography.body1,
        color = MaterialTheme.colors.primary
    )
    Spacer(modifier = Modifier.height(10.dp))
    items()
    Divider()
    Spacer(modifier = Modifier.height(30.dp))
}