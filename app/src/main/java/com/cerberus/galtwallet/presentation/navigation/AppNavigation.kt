package com.cerberus.galtwallet.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cerberus.galtwallet.presentation.setup.SetupScreen
import com.cerberus.galtwallet.presentation.initialize.create.CreateWalletScreen
import com.cerberus.galtwallet.presentation.initialize.recovery.RecoveryWalletScreen
import com.cerberus.galtwallet.presentation.receive.ReceiveScreen
import com.cerberus.galtwallet.presentation.send.SendScreen
import com.cerberus.galtwallet.presentation.settings.SettingsScreen
import com.cerberus.galtwallet.presentation.transaction.TransactionScreen
import com.cerberus.galtwallet.presentation.wallet.WalletScreen

@Composable
fun AppNavigation(
    startDestination: AppScreen
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = startDestination.route
    ) {
        composable(route = AppScreen.SetupScreen.route) { SetupScreen(navController) }
        composable(route = AppScreen.WalletScreen.route) { WalletScreen(navController) }
        composable(route = AppScreen.TransactionScreen.route) { TransactionScreen(navController) }
        composable(route = AppScreen.SettingsScreen.route) { SettingsScreen(navController) }
        composable(route = AppScreen.CreateWalletScreen.route) { CreateWalletScreen(navController) }
        composable(route = AppScreen.RecoveryWalletScreen.route) { RecoveryWalletScreen(navController) }
        composable(route = AppScreen.SendScreen.route) { SendScreen(navController) }
        composable(route = AppScreen.ReceiveScreen.route) { ReceiveScreen(navController) }
    }
}