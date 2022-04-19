package com.cerberus.galtwallet

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cerberus.galtwallet.application.wallet.WalletFinder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val walletFinder: WalletFinder

): ViewModel() {

    var loading = mutableStateOf(true)
    var existingWallet = mutableStateOf(false)

    init {
        walletFinder(Unit)
            .onStart { loading.value = true }
            .onCompletion { loading.value = false }
            .onEach { existingWallet.value = it }
            .launchIn(viewModelScope)
    }
}