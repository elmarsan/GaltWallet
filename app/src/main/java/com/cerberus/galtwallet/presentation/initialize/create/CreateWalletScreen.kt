package com.cerberus.galtwallet.presentation.initialize.create

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.cerberus.galtwallet.presentation.initialize.component.CreateMnemonicForm
import com.cerberus.galtwallet.presentation.navigation.AppScreen
import com.cerberus.galtwallet.presentation.ui.layout.AppScaffold
import com.cerberus.galtwallet.shared.component.LoadingSpinner
import com.cerberus.galtwallet.R

private const val TAG = "CreateWalletScreen"

enum class CreateWalletStep {
    SHOW_MNEMONIC,
    VALIDATE_MNEMONIC
}

@Composable
fun CreateWalletScreen(
    navController: NavController,
    viewModel: CreateWalletViewModel = hiltViewModel()
) {
    var step by remember { mutableStateOf(CreateWalletStep.SHOW_MNEMONIC) }
    val state = viewModel.state.value

    fun onGoBack() {
        if (step == CreateWalletStep.SHOW_MNEMONIC) {
            navController.popBackStack()
        } else if (step == CreateWalletStep.VALIDATE_MNEMONIC) {
            step = CreateWalletStep.SHOW_MNEMONIC
        }
    }

    AppScaffold(
        navController = navController,
        header = "CreateWalletScreen",
        showTopBar = true,
        showBottomBar = false,
        onGoBack = { onGoBack() },
        content = {
            Box(modifier = Modifier.fillMaxSize()) {
                if (state.privateKey != null) {
                    if (step == CreateWalletStep.SHOW_MNEMONIC) {
                        MnemonicWordsScreen(
                            mnemonic = state.privateKey.mnemonic,
                            onNextClick = { step = CreateWalletStep.VALIDATE_MNEMONIC }
                        )
                    } else if (step == CreateWalletStep.VALIDATE_MNEMONIC){
                        CreateMnemonicForm(
                            mnemonic = state.privateKey.mnemonic,
                            buttonText = stringResource(id = R.string.create),
                            onSubmitForm = {
                                viewModel.persistPrivateKey()
                                navController.navigate(AppScreen.WalletScreen.route)
                            }
                        )
                    }
                }

                if (state.isLoading) {
                    LoadingSpinner()
                }
            }
        },
    )
}
