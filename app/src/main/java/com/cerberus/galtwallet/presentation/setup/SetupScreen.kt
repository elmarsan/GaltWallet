package com.cerberus.galtwallet.presentation.setup

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.cerberus.galtwallet.R
import com.cerberus.galtwallet.presentation.navigation.AppScreen
import com.cerberus.galtwallet.presentation.ui.layout.AppScaffold

private const val TAG = "SetupScreen"

@Composable
fun SetupScreen(
    navController: NavController,
) {
    Log.d(TAG, "app started")

    AppScaffold(
        navController = navController,
        content = {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = { navController.navigate(AppScreen.CreateWalletScreen.route) },
                        modifier = Modifier
                            .width(120.dp)
                            .height(50.dp)
                    ) {
                        Text(text = stringResource(id = R.string.create))
                    }

                    Spacer(modifier = Modifier.width(20.dp))

                    Button(
                        onClick = { navController.navigate(AppScreen.RecoveryWalletScreen.route) },
                        modifier = Modifier
                            .width(120.dp)
                            .height(50.dp)
                    ) {
                        Text(text = stringResource(id = R.string.recover))
                    }
                }
            }
        },
        header = stringResource(id = R.string.app_name),
        showTopBar = true,
        showBottomBar = false
    )
}
