package com.cerberus.galtwallet.presentation.settings

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.cerberus.galtwallet.R
import com.cerberus.galtwallet.presentation.settings.components.SettingsOptionGroup
import com.cerberus.galtwallet.presentation.settings.components.SettingsOptionItem
import com.cerberus.galtwallet.presentation.ui.layout.AppScaffold
import com.cerberus.galtwallet.shared.component.LoadingSpinner
import java.util.*

@Composable
fun SettingsScreen(
    navController: NavController,
    viewModel: SettingsViewModel = hiltViewModel()
) {

    SettingScreenContent(
        navController = navController,
        viewModel = viewModel
    )
}

@Composable
fun SettingScreenContent(
    navController: NavController,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    AppScaffold(
        onGoBack = null,
        content = {
           if (viewModel.loading.value) {
               LoadingSpinner()
           } else {
               Box(
                   modifier = Modifier.fillMaxSize()
               ) {
                   Column(
                       modifier = Modifier
                           .fillMaxSize()
                           .padding(40.dp)
                   ) {
                       SettingsOptionGroup(
                           title = stringResource(id = R.string.interface_text)
                       ) {
                           SettingsOptionItem(
                               text = stringResource(id = R.string.language),
                               description = Locale.getDefault().displayLanguage,
                               onSelectOption = {
                                   Toast.makeText(context, "Language option", Toast.LENGTH_SHORT).show()
                               }
                           )
                       }

                      WalletOptions(viewModel = viewModel)
                   }
               }
           }
        },
        header = "SettingsScreen",
        navController = navController,
        showBottomBar = true,
        showTopBar = true
    )
}

@Composable
fun WalletOptions(
    viewModel: SettingsViewModel
) {
    val context = LocalContext.current
    var deleteDialog by remember { mutableStateOf(false)  }

    if (deleteDialog) {
        AlertDialog(
            onDismissRequest = { deleteDialog = false },
            title = { Text(text = stringResource(id = R.string.delete_wallet)) },
            text = { Text(text = stringResource(id = R.string.delete_wallet_description)) },
            confirmButton = {
                Button(
                    onClick = {
                        viewModel.deleteWallet(context = context)
                        deleteDialog = false
                    }
                ) {
                    Text(text = stringResource(id = R.string.understood_delete_anyway))
                }
            },
            dismissButton = {
                Button(
                    onClick = { deleteDialog = false }
                ) {
                    Text(text = stringResource(id = R.string.cancel))
                }
            }
        )
    }

    SettingsOptionGroup(
        title = stringResource(id = R.string.wallet)
    ) {
        SettingsOptionItem(
            text = stringResource(id = R.string.delete_wallet),
            description = stringResource(id = R.string.permanently_delete_current_wallet),
            onSelectOption = { deleteDialog = true }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSettingsScreenContent() {
    SettingScreenContent(navController = rememberNavController())
}
