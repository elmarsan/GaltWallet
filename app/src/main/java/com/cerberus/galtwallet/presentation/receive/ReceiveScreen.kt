package com.cerberus.galtwallet.presentation.receive

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.cerberus.galtwallet.R
import com.cerberus.galtwallet.presentation.ui.layout.AppScaffold
import com.cerberus.galtwallet.shared.component.LoadingSpinner

@Composable
fun ReceiveScreen(
    navController: NavController,
    viewModel: ReceiveViewModel = hiltViewModel()
) {
    AppScaffold(
        navController = navController,
        onGoBack = { navController.popBackStack() },
        showBottomBar = true,
        showTopBar = true,
        header = stringResource(id = R.string.receive_funds),
        content = {
            if (viewModel.loading.value) {
                LoadingSpinner()
            } else if (viewModel.address.value != null) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.TopStart
                ) {
                    val addressSection = "addressSection"
                    val generateSection = "generateSection"

                    val constraints = ConstraintSet {
                        val addressSectionRef = createRefFor(addressSection)
                        val generateSectionRef = createRefFor(generateSection)

                        constrain(addressSectionRef) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            width = Dimension.percent(1f)
                            height = Dimension.percent(0.8f)
                        }

                        constrain(generateSectionRef) {
                            top.linkTo(addressSectionRef.bottom)
                            width = Dimension.percent(1f)
                            height = Dimension.percent(0.2f)
                        }
                    }

                    ConstraintLayout(constraints, modifier = Modifier.fillMaxSize()) {
                        Box(
                            modifier = Modifier.layoutId(addressSection),
                            contentAlignment = Alignment.Center,
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Image(
                                    bitmap = viewModel.address.value!!.qrcodeBitmap.asImageBitmap(),
                                    contentDescription = "Address QR",
                                )

                                Spacer(modifier = Modifier.height(24.dp))

                                Row {
                                    TextField(
                                        value = viewModel.address.value!!.plainAddress,
                                        onValueChange = {},
                                        colors = TextFieldDefaults.textFieldColors(
                                            backgroundColor = MaterialTheme.colors.background
                                        ),
                                        trailingIcon = {
                                            Icon(
                                                painter = painterResource(id = R.drawable.ic_baseline_content_copy_24),
                                                contentDescription = null
                                            )
                                        }
                                    )
                                }
                            }
                        }

                        Box(
                            modifier = Modifier.layoutId(generateSection),
                            contentAlignment = Alignment.TopCenter,
                        ) {
                            Button(onClick = { viewModel.generateAddress() }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_baseline_autorenew_24),
                                    contentDescription = null,
                                )

                                Spacer(modifier = Modifier.width(5.dp))

                                Text(
                                    text = stringResource(id = R.string.generate),
                                    style = MaterialTheme.typography.h4
                                )
                            }
                        }
                    }
                }
            }
        }
    )
}