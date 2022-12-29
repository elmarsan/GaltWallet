package com.cerberus.galtwallet.presentation.wallet

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.cerberus.galtwallet.presentation.navigation.AppScreen
import com.cerberus.galtwallet.presentation.ui.layout.AppScaffold
import com.cerberus.galtwallet.R

@Composable
fun WalletScreen(
    navController: NavController,
    viewModel: WalletViewModel = hiltViewModel()
) {
    WalletScreenContent(
        navController = navController,
        viewModel = viewModel
    )
}

@Composable
fun WalletScreenContent(
    navController: NavController,
    viewModel: WalletViewModel = hiltViewModel()
) {
    AppScaffold(
        onGoBack = null,
        navController = navController,
        showBottomBar = true,
        showTopBar = true,
        header = stringResource(id = R.string.app_name),
        content = {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.TopStart,

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
                        height = Dimension.percent(0.5f)
                    }

                    constrain(actionSection) {
                        top.linkTo(balanceSection.bottom)
                        width = Dimension.percent(1f)
                        height = Dimension.percent(0.4f)
                    }
                }

                ConstraintLayout(constraints, modifier = Modifier.fillMaxSize()) {
                    Box(
                        modifier = Modifier.layoutId(balance),
                        contentAlignment = Alignment.TopCenter,

                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                        ) {
                            Image(
                                painterResource(R.drawable.btc_logo),
                                contentDescription = "btc_logo",
                            )

                            Text(
                                text = stringResource(id = R.string.balance) + ": ${viewModel.balance.toString()}",
                                style = MaterialTheme.typography.h4,
                                fontWeight = FontWeight(600)
                            )
                        }
                    }

                    Box(
                        modifier = Modifier.layoutId(actions),
                        contentAlignment = Alignment.Center,
                    ) {
                        Row {
                            Button(
                                onClick = { navController.navigate(AppScreen.SendScreen.route) },
                                modifier = Modifier
                                    .width(120.dp)
                                    .height(50.dp)
                            ) {
                                Text(text = stringResource(id = R.string.send))
                            }

                            Spacer(modifier = Modifier.width(25.dp))

                            Button(
                                onClick = { navController.navigate(AppScreen.ReceiveScreen.route) },
                                modifier = Modifier
                                    .width(120.dp)
                                    .height(50.dp)
                            ) {
                                Text(text = stringResource(id = R.string.receive))
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
