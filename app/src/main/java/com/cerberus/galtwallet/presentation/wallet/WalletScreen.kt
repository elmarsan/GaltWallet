package com.cerberus.galtwallet.presentation.wallet

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.cerberus.galtwallet.presentation.ui.layout.AppScaffold

@Composable
fun WalletScreen(
    navController: NavController,
    viewModel: WalletViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    AppScaffold(
        onGoBack = null,
        content = { Text("WalletScreen") },
        header = "WalletScreen",
        navController = navController,
        showBottomBar = true,
        showTopBar = true
    )
}
