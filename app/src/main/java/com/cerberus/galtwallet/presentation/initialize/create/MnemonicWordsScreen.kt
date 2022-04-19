package com.cerberus.galtwallet.presentation.initialize.create

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.cerberus.galtwallet.R


private const val TAG = "MnemonicWordsScreen"

@Composable
fun MnemonicWordsScreen(
    mnemonic: List<String>,
    onNextClick: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopStart
    ) {
        val mnemonicTopId = "mnemonicTop"
        val mnemonicColumn1Id = "mnemonicColumn1"
        val mnemonicColumn2Id = "mnemonicColumn2"
        val mnemonicBottomId = "mnemonicBottom"

        val constraints = ConstraintSet {
            val mnemonicTop = createRefFor(mnemonicTopId)
            val mnemonicColumn1 = createRefFor(mnemonicColumn1Id)
            val mnemonicColumn2 = createRefFor(mnemonicColumn2Id)
            val mnemonicBottom = createRefFor(mnemonicBottomId)

            constrain(mnemonicTop) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                width = Dimension.percent(1f)
                height = Dimension.percent(0.1f)
            }

            constrain(mnemonicColumn1) {
                top.linkTo(mnemonicTop.bottom)
                start.linkTo(mnemonicTop.start)
                width = Dimension.percent(0.5f)
                height = Dimension.percent(0.7f)
            }

            constrain(mnemonicColumn2) {
                top.linkTo(mnemonicTop.bottom)
                start.linkTo(mnemonicColumn1.end)
                width = Dimension.percent(0.5f)
                height = Dimension.percent(0.7f)
            }

            constrain(mnemonicBottom) {
                top.linkTo(mnemonicColumn2.bottom)
                width = Dimension.percent(1f)
                height = Dimension.percent(0.2f)
            }
        }

        ConstraintLayout(constraints, modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .layoutId(mnemonicTopId)
                    .padding(20.dp)
            ) {
                Column {
                    Text(
                        text = stringResource(id = R.string.write_down_your_mnemonic_code),
                        style = MaterialTheme.typography.h2
                    )
                }
            }

            Box(
                modifier = Modifier
                    .layoutId(mnemonicColumn1Id)
                    .padding(6.dp)
            ) {
                FormColumn(mnemonicFields = mnemonic.slice(0 until mnemonic.size / 2))
            }

            Box(
                modifier = Modifier
                    .layoutId(mnemonicColumn2Id)
                    .padding(6.dp)
            ) {
                FormColumn(
                    mnemonicFields = mnemonic.slice(mnemonic.size / 2 until mnemonic.size),
                    indexStart = 6
                )
            }

            Box(
                modifier = Modifier
                    .layoutId(mnemonicBottomId)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(
                        onClick = {
                            Log.d(TAG, "OnSubmitFormClick")
                            onNextClick()
                        },
                    ) {
                        Text(text = stringResource(id = R.string.next))
                    }
                }
            }
        }
    }
}

@Composable
fun FormColumn(
    mnemonicFields: List<String>,
    indexStart: Int = 0
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        itemsIndexed(mnemonicFields) { index, text ->
            Text(
                text = "${index + indexStart + 1}. $text",
                style = MaterialTheme.typography.h3
            )
            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}