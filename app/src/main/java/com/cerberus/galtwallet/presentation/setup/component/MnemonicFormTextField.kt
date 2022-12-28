package com.cerberus.galtwallet.presentation.setup.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.material.Icon
import androidx.compose.ui.graphics.Color
import com.cerberus.galtwallet.R

data class MnemonicWord(
    val word: String,
    val expectedWord: String? = null,
    val wordNumber: Int,
    val onWordChange: (String) -> Unit = {},
) {
    fun isValid(): Boolean {
        if (expectedWord == null) {
            return true
        }

        return word == expectedWord
    }

    fun isEmpty(): Boolean {
        return word.isEmpty()
    }
}

@Composable
fun MnemonicFormTextField(mnemonicWord: MnemonicWord) {
    var visited by remember { mutableStateOf(false) }
    var isFocused by remember { mutableStateOf(false) }
    val displayError = (visited && !isFocused) && !mnemonicWord.isValid() && !mnemonicWord.isEmpty()

    println(mnemonicWord)

    OutlinedTextField(
        value = mnemonicWord.word,
        onValueChange = mnemonicWord.onWordChange,
        isError = displayError,
        label = { Text((mnemonicWord.wordNumber + 1).toString()) },
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
        ),
        leadingIcon = {
            if (displayError) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_error_outline_24),
                    contentDescription = null,
                    tint= Color.Unspecified
                )
            } else if (mnemonicWord.isValid()) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_check_circle_outline_24),
                    contentDescription = null,
                    tint= Color.Unspecified
                )
            }
        },
    )
}
