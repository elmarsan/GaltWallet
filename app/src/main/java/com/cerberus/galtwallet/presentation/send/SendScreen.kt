package com.cerberus.galtwallet.presentation.send

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.cerberus.galtwallet.R
import com.cerberus.galtwallet.presentation.ui.layout.AppScaffold

@Composable
fun SendScreen(
    navController: NavController
) {
    AppScaffold(
        navController = navController,
        content = { Text("SendScreen") },
        onGoBack = { navController.popBackStack() },
        showBottomBar = true,
        showTopBar = true,
        header = stringResource(id = R.string.send_funds)
    )
}
