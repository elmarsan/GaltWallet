package com.cerberus.galtwallet.presentation.wallet

import androidx.lifecycle.ViewModel
import com.cerberus.galtwallet.domain.Wallet
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WalletViewModel @Inject constructor(
    private val wallet: Wallet
): ViewModel() {
    val balance = wallet.getBalance()
}