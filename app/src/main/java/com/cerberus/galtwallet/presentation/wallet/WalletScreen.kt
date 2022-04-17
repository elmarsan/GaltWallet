package com.cerberus.galtwallet.presentation.wallet

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.cerberus.galtwallet.presentation.navigation.AppScreen
import com.cerberus.galtwallet.presentation.ui.layout.AppScaffold

@Composable
fun WalletScreen(
    navController: NavController,
) {
    WalletScreenContent(navController = navController)
}

@Composable
fun WalletScreenContent(navController: NavController) {
    AppScaffold(
        onGoBack = null,
        header = "WalletScreen",
        navController = navController,
        showBottomBar = true,
        showTopBar = true,
        content = {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.TopStart
            ) {
                val balance = "balance"
                val actions = "actions"

                val constraints = ConstraintSet {
                    val balanceSection = createRefFor(balance)
                    val actionSection = createRefFor(actions)

                    constrain(balanceSection) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        width = Dimension.percent(1f)
                        height = Dimension.percent(0.2f)
                    }

                    constrain(actionSection) {
                        top.linkTo(balanceSection.bottom)
                        width = Dimension.percent(1f)
                        height = Dimension.percent(0.8f)
                    }
                }

                ConstraintLayout(constraints, modifier = Modifier.fillMaxSize()) {
                    Box(
                        modifier = Modifier.layoutId(balance),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text = "Balance",
                            style = MaterialTheme.typography.h4
                        )
                    }

                    Box(
                        modifier = Modifier.layoutId(actions),
                        contentAlignment = Alignment.Center,
                    ) {
                        Row {
                            Button(onClick = { navController.navigate(AppScreen.SendScreen.route) }) {
                                Text("Send")
                            }

                            Spacer(modifier = Modifier.width(25.dp))

                            Button(onClick = { navController.navigate(AppScreen.ReceiveScreen.route) }) {
                                Text("Receive")
                            }
                        }
                    }
                }
            }
        },
    )
}

@Preview(showBackground = true)
@Composable
fun WalletScreenPreview() {
    WalletScreenContent(rememberNavController())
}
