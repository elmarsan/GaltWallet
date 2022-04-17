package com.cerberus.galtwallet.presentation.initialize.create

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cerberus.galtwallet.application.wallet.PrivateKeyGenerator
import com.cerberus.galtwallet.shared.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CreateWalletViewModel @Inject constructor(
    private val privateKeyGenerator: PrivateKeyGenerator
): ViewModel() {

    private val _state = mutableStateOf(CreateWalletState())
    val state: State<CreateWalletState> = _state

    init {
        createPrivateKey()
    }

    private fun createPrivateKey() {
        privateKeyGenerator().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = CreateWalletState(privateKey = result.data)
                }
                is Resource.Error -> {
                    _state.value = CreateWalletState(
                        error = result.message ?: "An unexpected error occurred while creating the wallet"
                    )
                }
                is Resource.Loading -> {
                    _state.value = CreateWalletState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}
