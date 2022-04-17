package com.cerberus.galtwallet.presentation.transaction

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.cerberus.galtwallet.presentation.ui.layout.AppScaffold

@Composable
fun TransactionScreen(
    navController: NavController,
    viewModel: TransactionViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    AppScaffold(
        onGoBack = null,
        content = { Text("TransactionScreen") } ,
        header = "TransactionScreen",
        navController = navController,
        showBottomBar = true,
        showTopBar = true
    )
}
