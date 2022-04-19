package com.cerberus.galtwallet.presentation.setup.recovery

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.cerberus.galtwallet.R
import com.cerberus.galtwallet.domain.PrivateKey
import com.cerberus.galtwallet.presentation.setup.component.RecoveryMnemonicForm
import com.cerberus.galtwallet.presentation.ui.layout.AppScaffold
import com.cerberus.galtwallet.shared.component.LoadingSpinner

private const val TAG = "RecoveryWalletScreen"

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
            if (viewModel.loading.value) {
                LoadingSpinner()
            } else {
                RecoveryMnemonicForm(
                    buttonText = stringResource(id = R.string.restore),
                    onSubmitForm = { mnemonic ->
                        viewModel.persistPrivateKey(PrivateKey.fromMnemonicList(mnemonic))
                    }
                )
            }
        },
    )
}
