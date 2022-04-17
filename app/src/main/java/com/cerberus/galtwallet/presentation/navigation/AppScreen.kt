package com.cerberus.galtwallet.presentation.navigation

sealed class AppScreen(val route: String) {
    object HomeScreen: AppScreen("HomeScreenRoute")
    object WalletScreen: AppScreen("WalletScreenRoute")
    object TransactionScreen: AppScreen("TransactionScreenRouter")
    object SettingsScreen: AppScreen("SettingsScreenRoute")
    object CreateWalletScreen: AppScreen("CreateWalletScreenRoute")
    object RecoveryWalletScreen: AppScreen("RecoveryWalletScreenRoute")
}
