package com.cerberus.galtwallet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.cerberus.galtwallet.presentation.navigation.AppNavigation
import com.cerberus.galtwallet.presentation.navigation.AppScreen
import com.cerberus.galtwallet.presentation.ui.theme.GaltWalletTheme
import com.cerberus.galtwallet.shared.component.LoadingSpinner
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel: MainViewModel by viewModels()

        setContent {
            GaltWalletTheme {
                if (viewModel.loading.value) {
                    LoadingSpinner()
                } else if (viewModel.existingWallet.value) {
                    AppNavigation(startDestination = AppScreen.WalletScreen)
                } else {
                    AppNavigation(startDestination = AppScreen.SetupScreen)
                }
            }
        }

    }
}
