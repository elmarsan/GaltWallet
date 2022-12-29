package com.cerberus.galtwallet.presentation.send

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cerberus.galtwallet.application.transaction.TransactionBroadcaster
import com.cerberus.galtwallet.domain.Amount
import com.cerberus.galtwallet.domain.TransactionBroadcastDTO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class SendViewModel @Inject constructor(
    private val transactionBroadcaster: TransactionBroadcaster
): ViewModel() {
    var amount = mutableStateOf<ULong>(0u)
    var address = mutableStateOf("")
    var loading = mutableStateOf(false)

    fun setAmount(newAmount: ULong) {
        amount.value = newAmount
    }

    fun setAddress(newAddress: String) {
        address.value = newAddress
    }

    fun broadcastTransaction() {
        if (amount.value > 0u && address.value.isNotEmpty()) {
            val transactionBroadcastDTO = TransactionBroadcastDTO(
                amount = Amount(amount.value),
                to = address.value
            )

            transactionBroadcaster(transactionBroadcastDTO)
                .onStart { loading.value = true }
                .onCompletion { loading.value = false }
                .onEach {  }
                .launchIn(viewModelScope)
        }
    }
}