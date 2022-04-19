package com.cerberus.galtwallet.presentation.home

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.cerberus.galtwallet.R
import com.cerberus.galtwallet.presentation.navigation.AppScreen
import com.cerberus.galtwallet.presentation.ui.layout.AppScaffold
import com.cerberus.galtwallet.shared.component.LoadingSpinner

private const val TAG = "HomeScreen"

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Log.d("HomeScreen", "app started")

    LaunchedEffect(Unit) {
        Log.d("HomeScreen", "checking wallet existence ${state.privateKey != null}")

        if (state.privateKey != null) {
            navController.navigate(route = AppScreen.WalletScreen.route)
        }
    }

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
                    if (state.isLoading) {
                        LoadingSpinner()
                    } else {
                        Button(onClick = { navController.navigate(AppScreen.CreateWalletScreen.route) }) {
                            Text(text = stringResource(id = R.string.create))
                        }

                        Spacer(modifier = Modifier.width(20.dp))

                        Button(onClick = { navController.navigate(AppScreen.RecoveryWalletScreen.route) }) {
                            Text(text = stringResource(id = R.string.recover))
                        }
                    }
                }
            }
        },
        header = "HomeScreen",
        showTopBar = true,
        showBottomBar = false
    )
}
