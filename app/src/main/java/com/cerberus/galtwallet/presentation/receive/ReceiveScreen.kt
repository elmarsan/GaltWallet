package com.cerberus.galtwallet.presentation.receive

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.cerberus.galtwallet.R
import com.cerberus.galtwallet.domain.Address
import com.cerberus.galtwallet.presentation.navigation.AppScreen
import com.cerberus.galtwallet.presentation.ui.layout.AppScaffold

val address = Address("1FsJGzSHk7zr7JWAvtEPLV7sBXAHaYiqo2")

@Composable
fun ReceiveScreen(
    navController: NavController
) {
    AppScaffold(
        navController = navController,
        header = "ReceiveScreen",
        onGoBack = { navController.popBackStack() },
        showBottomBar = true,
        showTopBar = true,
        content = {
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
                                bitmap = address.qrcodeBitmap.asImageBitmap(),
                                contentDescription = "Address QR",
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Row(
                                modifier = Modifier.border(border = BorderStroke(1.5.dp, color = Color.Gray))
                            ) {
                                TextField(
                                    value = address.plainAddress,
                                    onValueChange = {},
                                    colors = TextFieldDefaults.textFieldColors(
                                        backgroundColor = MaterialTheme.colors.background
                                    ),
                                    trailingIcon = {
                                        Icon(
                                            painter = painterResource(id = R.drawable.ic_baseline_content_copy_24),
                                            contentDescription = null,
                                            modifier = Modifier.background(color = Color.LightGray)
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
                        Button(onClick = { /*TODO*/ }) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_baseline_autorenew_24),
                                contentDescription = null,
                            )

                            Spacer(modifier = Modifier.width(5.dp))

                            Text(
                                text = "Generate",
                                style = MaterialTheme.typography.h4
                            )
                        }
                    }
                }
            }
        }
    )
}