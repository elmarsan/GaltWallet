package com.cerberus.galtwallet.presentation.ui.layout

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun AppScaffold(
    navController: NavController,
    showTopBar: Boolean = true,
    showBottomBar: Boolean = true,
    content: @Composable() () -> Unit,
    onGoBack: (() -> Unit)? = null,
    header: String
) {
    Scaffold(
        topBar = {
            if (showTopBar) {
                AppTopBar(
                    header = header,
                    onGoBack = onGoBack
                )
            }
        },
        bottomBar = {
            if (showBottomBar) {
                AppBottomBar(navController = navController)
            }
        }
    ) {
        content()
    }
}
