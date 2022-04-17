package com.cerberus.galtwallet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.cerberus.galtwallet.presentation.navigation.AppNavigation
import com.cerberus.galtwallet.presentation.ui.theme.GaltWalletTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GaltWalletTheme {
                AppNavigation()
            }
        }
    }
}