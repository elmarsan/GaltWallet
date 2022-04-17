package com.cerberus.galtwallet.presentation.wallet

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.cerberus.galtwallet.presentation.navigation.AppScreen
import com.cerberus.galtwallet.presentation.ui.layout.AppScaffold

@Composable
fun WalletScreen(
    navController: NavController,
    viewModel: WalletViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    LaunchedEffect(Unit) {
        if (state == null) {
            navController.navigate(route = AppScreen.CreateWalletScreen.route)
        }
    }

    AppScaffold(
        onGoBack = null,
        content = { Text("WalletScreen") },
        header = "WalletScreen",
        navController = navController,
        showBottomBar = true,
        showTopBar = true
    )
}
