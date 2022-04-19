package com.cerberus.galtwallet.presentation.setup.create

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cerberus.galtwallet.application.privatekey.PrivateKeyGenerator
import com.cerberus.galtwallet.application.privatekey.PrivateKeyPersister
import com.cerberus.galtwallet.domain.PrivateKey
import com.cerberus.galtwallet.domain.Wallet
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class CreateWalletViewModel @Inject constructor(
    private val privateKeyGenerator: PrivateKeyGenerator,
    private val privateKeyPersister: PrivateKeyPersister,
    private val wallet: Wallet
): ViewModel() {

    var loading = mutableStateOf(false)
    var privateKey = mutableStateOf<PrivateKey?>(null)

    init {
        createPrivateKey()
    }

    fun persistPrivateKey() {
        if (privateKey.value != null) {
            privateKeyPersister(privateKey.value!!)
                .onStart { loading.value = true }
                .onCompletion {
                    loading.value = false
                    wallet.setup()
                }
                .launchIn(viewModelScope)
        }
    }

    private fun createPrivateKey() {
        privateKeyGenerator(Unit)
            .onStart { loading.value = true }
            .onCompletion { loading.value = false }
            .onEach { privateKey.value = it }
            .launchIn(viewModelScope)
    }
}
