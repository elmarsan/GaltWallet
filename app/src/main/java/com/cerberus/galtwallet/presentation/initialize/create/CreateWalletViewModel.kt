package com.cerberus.galtwallet.presentation.initialize.create

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cerberus.galtwallet.application.wallet.PrivateKeyGenerator
import com.cerberus.galtwallet.application.wallet.PrivateKeyPersister
import com.cerberus.galtwallet.shared.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CreateWalletViewModel @Inject constructor(
    private val privateKeyGenerator: PrivateKeyGenerator,
    private val privateKeyPersister: PrivateKeyPersister
): ViewModel() {

    private val _createState = mutableStateOf(CreateWalletState())
    val state: State<CreateWalletState> = _createState

    init {
        createPrivateKey()
    }

    fun persistPrivateKey() {
        if (state.value.privateKey !== null) {
            privateKeyPersister(state.value.privateKey!!).onEach { result ->
                when (result) {
                    is Resource.Success -> {}
                    is Resource.Error -> {
                        _createState.value = CreateWalletState(
                            error = result.message ?: "An unexpected error occurred while creating the wallet"
                        )
                    }
                    is Resource.Loading -> {
                        _createState.value = CreateWalletState(isLoading = true)
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    private fun createPrivateKey() {
        privateKeyGenerator().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _createState.value = CreateWalletState(privateKey = result.data)
                }
                is Resource.Error -> {
                    _createState.value = CreateWalletState(
                        error = result.message ?: "An unexpected error occurred while creating the wallet"
                    )
                }
                is Resource.Loading -> {
                    _createState.value = CreateWalletState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}
