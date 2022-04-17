package com.cerberus.galtwallet.presentation.initialize.component

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.cerberus.galtwallet.shared.component.FormInputText
import com.cerberus.galtwallet.shared.component.FormInputTextData

private const val TAG = "MnemonicForm"

@Composable
fun MnemonicForm(
    mnemonic: List<String>,
    buttonText: String,
    onSubmitForm: () -> Unit
) {
    FormView(mnemonic, buttonText, onSubmitForm)
}

@Composable
private fun FormView(
    mnemonic: List<String>,
    buttonText: String,
    onCreateWallet: () -> Unit
) {
    val formFields = mutableListOf<FormInputTextData>()

    mnemonic.forEachIndexed { i, word ->
        var value by remember { mutableStateOf("") }
        var errorMsg by remember { mutableStateOf("") }

        formFields.add(
            FormInputTextData(
                value = value,
                onValueChange = {
                    value = it

                    errorMsg = if (value != word) {
                        "Word does not match"
                    } else {
                        ""
                    }
                },
                errorMsg = errorMsg,
                label = "Expected word: $word"
            )
        )
    }

    val leftColumnFields = formFields.slice(0 until formFields.size / 2)
    val rightColumnFields = formFields.slice(formFields.size / 2 until formFields.size)

    fun validForm(): Boolean {
        return leftColumnFields.none { !it.errorMsg.isNullOrEmpty() || it.value.isEmpty() } &&
                rightColumnFields.none { !it.errorMsg.isNullOrEmpty() || it.value.isEmpty() }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopStart
    ) {
        val formColumn1Id = "formColumn1"
        val formColumn2Id = "formColumn2"
        val formBottomId = "formBottom"

        val constraints = ConstraintSet {
            val formColumn1 = createRefFor(formColumn1Id)
            val formColumn2 = createRefFor(formColumn2Id)
            val formBottom = createRefFor(formBottomId)

            constrain(formColumn1) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                width = Dimension.percent(0.5f)
                height = Dimension.percent(0.8f)
            }

            constrain(formColumn2) {
                top.linkTo(parent.top)
                start.linkTo(formColumn1.end)
                width = Dimension.percent(0.5f)
                height = Dimension.percent(0.8f)
            }

            constrain(formBottom) {
                top.linkTo(formColumn2.bottom)
                width = Dimension.percent(1f)
                height = Dimension.percent(0.2f)
            }
        }

        ConstraintLayout(constraints, modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .layoutId(formColumn1Id)
                    .padding(6.dp)
            ) {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(leftColumnFields) {
                        FormInputText(it)
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                }
            }

            Box(
                modifier = Modifier
                    .layoutId(formColumn2Id)
                    .padding(6.dp)
            ) {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(rightColumnFields) {
                        FormInputText(it)
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                }
            }

            Box(
                modifier = Modifier
                    .layoutId(formBottomId)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(
                        onClick = {
                            Log.d(TAG, "OnSubmitFormClick")
                            onCreateWallet()
                        },
                        enabled = validForm()
                    ) {
                        Text(text = buttonText)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun Overview() {
    val mnemonic = listOf(
        "Word-1",
        "Word-2",
        "Word-3",
        "Word-4",
        "Word-5",
        "Word-6",
        "Word-7",
        "Word-8",
        "Word-9",
        "Word-10",
        "Word-11",
        "Word-12",
    )

    FormView(mnemonic, "Create wallet") {}
}