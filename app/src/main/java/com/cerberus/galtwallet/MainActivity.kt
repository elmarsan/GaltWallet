package com.cerberus.galtwallet

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.cerberus.galtwallet.presentation.navigation.AppNavigation
import com.cerberus.galtwallet.presentation.navigation.AppScreen
import com.cerberus.galtwallet.presentation.ui.theme.GaltWalletTheme
import com.cerberus.galtwallet.shared.component.LoadingSpinner
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val requestPermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {  }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel: MainViewModel by viewModels()
        requestPermission.launch(Manifest.permission.CAMERA)

        setContent {
            GaltWalletTheme {
                when {
                    viewModel.loading.value -> {
                        LoadingSpinner()
                    }
                    viewModel.existingWallet.value -> {
                        AppNavigation(startDestination = AppScreen.WalletScreen)
                    }
                    else -> {
                        AppNavigation(startDestination = AppScreen.SetupScreen)
                    }
                }
            }
        }

    }
}
