package com.cerberus.galtwallet.presentation.setup.recovery

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cerberus.galtwallet.application.privatekey.PrivateKeyPersister
import com.cerberus.galtwallet.domain.PrivateKey
import com.cerberus.galtwallet.domain.Wallet
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class RecoveryViewModel @Inject constructor(
    private val privateKeyPersister: PrivateKeyPersister,
    private val wallet: Wallet
) : ViewModel() {

    var loading = mutableStateOf(false)

    fun persistPrivateKey(privateKey: PrivateKey) {
        privateKeyPersister(privateKey)
            .onStart { loading.value = true }
            .onCompletion {
                loading.value = false
                wallet.setup()
            }
            .launchIn(viewModelScope)
    }
}
