package com.cerberus.galtwallet.presentation.receive

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cerberus.galtwallet.application.address.AddressGenerator
import com.cerberus.galtwallet.domain.Address
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class ReceiveViewModel @Inject constructor(
    private val addressGenerator: AddressGenerator
): ViewModel() {

    var loading = mutableStateOf(false)
    var address = mutableStateOf<Address?>(null)

    init {
        generateAddress()
    }

    fun generateAddress() {
        addressGenerator(Unit)
            .onStart { loading.value = true }
            .onCompletion { loading.value = false }
            .onEach { address.value = it }
            .launchIn(viewModelScope)
    }
}
