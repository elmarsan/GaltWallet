package com.cerberus.galtwallet.presentation.initialize.recovery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cerberus.galtwallet.application.wallet.PrivateKeyPersister
import com.cerberus.galtwallet.domain.PrivateKey
import com.cerberus.galtwallet.shared.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RecoveryViewModel @Inject constructor(
    private val privateKeyPersister: PrivateKeyPersister
) : ViewModel() {
    fun persistPrivateKeyFromMnemonic(mnemonic: List<String>) {
        val privateKey = PrivateKey.fromMnemonicList(mnemonic)

        privateKeyPersister(privateKey).onEach { result ->
            when (result) {
                is Resource.Success -> {}
                is Resource.Error -> {}
                is Resource.Loading -> {}
            }
        }.launchIn(viewModelScope)
    }
}
