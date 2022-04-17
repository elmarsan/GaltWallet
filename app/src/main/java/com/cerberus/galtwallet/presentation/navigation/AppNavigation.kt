package com.cerberus.galtwallet.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cerberus.galtwallet.presentation.home.HomeScreen
import com.cerberus.galtwallet.presentation.initialize.create.CreateWalletScreen
import com.cerberus.galtwallet.presentation.initialize.recovery.RecoveryWalletScreen
import com.cerberus.galtwallet.presentation.settings.SettingsScreen
import com.cerberus.galtwallet.presentation.transaction.TransactionScreen
import com.cerberus.galtwallet.presentation.wallet.WalletScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AppScreen.HomeScreen.route
    ) {
        composable(route = AppScreen.HomeScreen.route) { HomeScreen(navController) }
        composable(route = AppScreen.WalletScreen.route) { WalletScreen(navController) }
        composable(route = AppScreen.TransactionScreen.route) { TransactionScreen(navController) }
        composable(route = AppScreen.SettingsScreen.route) { SettingsScreen(navController) }
        composable(route = AppScreen.CreateWalletScreen.route) { CreateWalletScreen(navController) }
        composable(route = AppScreen.RecoveryWalletScreen.route) { RecoveryWalletScreen(navController) }
    }
}
