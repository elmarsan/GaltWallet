package com.cerberus.galtwallet.presentation.settings.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SettingsOptionItem(
    text: String,
    description: String,
    onSelectOption: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onSelectOption()}
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.body2,
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = description,
            style = MaterialTheme.typography.body2,
        )
    }
}
