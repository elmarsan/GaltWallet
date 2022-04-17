package com.cerberus.galtwallet.shared.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType

data class FormInputTextData(
    val value: String,
    val errorMsg: String?,
    val label: String?,
    val onValueChange: (String) -> Unit = {},
)

@Composable
fun FormInputText(formInputTextData: FormInputTextData) {
    var visited by remember { mutableStateOf(false) }
    var isFocused by remember { mutableStateOf(false) }

    TextField(
        value = formInputTextData.value,
        onValueChange = formInputTextData.onValueChange,
        isError = !formInputTextData.errorMsg.isNullOrEmpty() && visited && !isFocused,
        label = { formInputTextData.label?.let { Text(formInputTextData.label) } },
        modifier = Modifier
            .fillMaxWidth()
            .onFocusChanged { st ->
                visited = true
                isFocused = st.isFocused
            },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.background
        ),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Text
        )
    )
}
