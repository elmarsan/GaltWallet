package com.cerberus.galtwallet.presentation.initialize.create

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.cerberus.galtwallet.domain.PrivateKey
import com.cerberus.galtwallet.presentation.initialize.component.MnemonicForm
import com.cerberus.galtwallet.presentation.ui.layout.AppScaffold
import com.cerberus.galtwallet.shared.component.LoadingSpinner

@Composable
fun CreateWalletScreen(
    navController: NavController,
    viewModel: CreateWalletViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    AppScaffold(
        navController = navController,
        header = "CreateWalletScreen",
        showTopBar = true,
        showBottomBar = false,
        onGoBack = { navController.popBackStack() },
        content = {
            Box(modifier = Modifier.fillMaxSize()) {
                if (state.privateKey != null) {
                    MnemonicForm(
                        mnemonic = state.privateKey.mnemonic,
                        buttonText = "CreateWallet",
                        onSubmitForm = { }
                    )
                }

                if (state.isLoading) {
                    LoadingSpinner()
                }
            }
        },
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewCreateWalletScreen() {
//    val privateKey = PrivateKey.generate()
    val navController = rememberNavController()

    AppScaffold(
        navController = navController,
        header = "CreateWalletScreen",
        showTopBar = true,
        showBottomBar = false,
        onGoBack = { navController.popBackStack() },
        content = {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                MnemonicForm(
                    mnemonic = listOf(
                        "Word-1",
                        "Word-2",
                        "Word-3",
                        "Word-4",
                        "Word-5",
                        "Word-6",
                        "Word-7",
                        "Word-8",
                        "Word-9",
                        "Word-10",
                        "Word-11",
                        "Word-12",
                    ),
                    buttonText = "Create wallet",
                    onSubmitForm = { }
                )
            }
        },
    )
}
