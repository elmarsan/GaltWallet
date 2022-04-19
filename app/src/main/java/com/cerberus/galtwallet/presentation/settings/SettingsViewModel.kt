package com.cerberus.galtwallet.presentation.settings

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cerberus.galtwallet.MainActivity
import com.cerberus.galtwallet.application.wallet.WalletRemover
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val walletRemover: WalletRemover,
): ViewModel() {
    var loading = mutableStateOf(false)

    fun deleteWallet(context: Context) {
        walletRemover(Unit)
            .onStart { loading.value = true }
            .onCompletion {
                loading.value = false
                context.startActivity(Intent(context, MainActivity::class.java))
            }
            .launchIn(viewModelScope)
    }
}
