package com.cerberus.galtwallet.presentation.initialize.recovery

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.cerberus.galtwallet.presentation.initialize.component.CreateMnemonicForm
import com.cerberus.galtwallet.presentation.initialize.component.RecoveryMnemonicForm
import com.cerberus.galtwallet.presentation.navigation.AppScreen
import com.cerberus.galtwallet.presentation.ui.layout.AppScaffold

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
            RecoveryMnemonicForm(
                buttonText = "Recover",
                onSubmitForm = {
                      Log.d(TAG, "Recovery submit form")
//                    Toast.makeText(this, "Recovery!!", Toast.LENGTH_LONG).show()
//                    navController.navigate(AppScreen.WalletScreen.route)
                }
            )
        },
    )
}