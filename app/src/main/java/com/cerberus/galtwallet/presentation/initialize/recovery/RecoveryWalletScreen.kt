package com.cerberus.galtwallet.presentation.initialize.recovery

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.cerberus.galtwallet.presentation.ui.layout.AppScaffold

@Composable
fun RecoveryWalletScreen(
    navController: NavController,
    viewModel: RecoveryViewModel = hiltViewModel()
) {
    AppScaffold(
        navController = navController,
        header = "RecoveryWalletScreen",
        showTopBar = true,
        showBottomBar = false,
        onGoBack = { navController.popBackStack() },
        content = {
            Text("RecoveryWalletScreen")
        },
    )
}