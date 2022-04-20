package com.cerberus.galtwallet.presentation.transaction

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.cerberus.galtwallet.presentation.ui.layout.AppScaffold
import com.cerberus.galtwallet.shared.component.LoadingSpinner

@Composable
fun TransactionScreen(
    navController: NavController,
    viewModel: TransactionViewModel = hiltViewModel()
) {
    AppScaffold(
        onGoBack = null,
        content = {
            if (viewModel.loading.value) {
                LoadingSpinner()
            } else {
              LazyColumn {
                  items(viewModel.data.value) {
                      Text(text = it.amount.btcString)
                  }
              }
            }
        },
        header = "TransactionScreen",
        navController = navController,
        showBottomBar = true,
        showTopBar = true
    )
}
