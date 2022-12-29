package com.cerberus.galtwallet.presentation.send

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.cerberus.galtwallet.R
import com.cerberus.galtwallet.presentation.navigation.AppScreen
import com.cerberus.galtwallet.presentation.ui.layout.AppScaffold
import com.cerberus.galtwallet.presentation.ui.theme.WhitePrimary

@Composable
fun SendScreen(
    navController: NavController,
    viewModel: SendViewModel = hiltViewModel()
) {
    AppScaffold(
        navController = navController,
        onGoBack = { navController.popBackStack() },
        showBottomBar = true,
        showTopBar = true,
        header = stringResource(id = R.string.send_funds),
        content = {
//            var address by remember { mutableStateOf("") }

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
                ) {
//                QRScanner()

//                val onWordChange: (String) -> Unit = {},

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    TextField(
                        value = viewModel.address.value,
                        onValueChange = {  viewModel.setAddress(it) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(width = 1.dp, color = WhitePrimary, shape = RectangleShape),
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = MaterialTheme.colors.background
                        )
                    )

                    Spacer(modifier = Modifier.height(25.dp))


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
                            onClick = { /*  QRScanner */ },
                            modifier = Modifier
                                .width(120.dp)
                                .height(50.dp),
                            enabled = viewModel.address.value.isNotEmpty()
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_baseline_qr_code_scanner_24),
                                contentDescription = null,
                                tint = WhitePrimary
                            )
                            Spacer(modifier = Modifier.width(15.dp))
                            Text(text = stringResource(id = R.string.scan))
                        }
                    }
                }
            }
        }
    )
}
