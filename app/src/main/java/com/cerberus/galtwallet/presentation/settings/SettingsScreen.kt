package com.cerberus.galtwallet.presentation.settings

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.cerberus.galtwallet.presentation.ui.layout.AppScaffold

@Composable
fun SettingsScreen(
    navController: NavController,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    AppScaffold(
        onGoBack = null,
        content = { Text("SettingsScreen") } ,
        header = "SettingsScreen",
        navController = navController,
        showBottomBar = true,
        showTopBar = true
    )
}
