package com.cerberus.galtwallet.presentation.ui.layout

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.cerberus.galtwallet.R
import com.cerberus.galtwallet.presentation.navigation.AppScreen

data class BottomBarItem(
    var text: String,
    var resourceId: Int,
    var screen: AppScreen
)

@Composable
fun AppBottomBar(navController: NavController) {
    val items = listOf(
        BottomBarItem(
            text = "Wallet",
            resourceId = R.drawable.ic_baseline_account_balance_wallet_24,
            screen = AppScreen.WalletScreen
        ),
        BottomBarItem(
            text = "History",
            resourceId = R.drawable.ic_baseline_history_24,
            screen = AppScreen.TransactionScreen
        ),
        BottomBarItem(
            text = "Settings",
            resourceId = R.drawable.ic_baseline_settings_24,
            screen = AppScreen.SettingsScreen
        ),
    )

    BottomNavigation {
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(painterResource(item.resourceId), item.text) },
                label = { Text(item.text) },
                selected = navController.currentBackStackEntry?.destination?.route == item.screen.route,
                onClick = { navController.navigate(item.screen.route) }
            )
        }
    }
}
