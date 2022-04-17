package com.cerberus.galtwallet.presentation.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.cerberus.galtwallet.presentation.navigation.AppScreen
import com.cerberus.galtwallet.presentation.ui.layout.AppScaffold

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val privateKey = viewModel.state.value

    LaunchedEffect(Unit) {
        if (privateKey != null) {
            navController.navigate(route = AppScreen.WalletScreen.route)
        }
    }

    AppScaffold(
        navController = navController,
        content = {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(onClick = { navController.navigate(AppScreen.CreateWalletScreen.route) }) {
                        Text(text = "Create")
                    }

                    Spacer(modifier = Modifier.width(20.dp))

                    Button(onClick = { navController.navigate(AppScreen.RecoveryWalletScreen.route) }) {
                        Text(text = "Recover")
                    }
                }
            }
        },
        header = "HomeScreen",
        showTopBar = true,
        showBottomBar = false
    )
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    AppScaffold(
        navController = rememberNavController(),
        content = {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(onClick = { }) {
                        Text(text = "Create")
                    }

                    Spacer(modifier = Modifier.width(20.dp))

                    Button(onClick = { }) {
                        Text(text = "Recover")
                    }
                }
            }
        },
        header = "HomeScreen",
        showTopBar = true,
        showBottomBar = false
    )
}