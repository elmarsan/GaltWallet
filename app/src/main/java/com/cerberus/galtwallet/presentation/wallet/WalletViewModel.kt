package com.cerberus.galtwallet.presentation.wallet

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cerberus.galtwallet.application.WalletFinderService
import com.cerberus.galtwallet.domain.PrivateKey
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class WalletViewModel @Inject constructor(
    private val walletFinderService: WalletFinderService
): ViewModel() {

    private val _state = mutableStateOf<PrivateKey?>(null)
    val state: State<PrivateKey?> = _state

    init {
        find()
    }

    private fun find() {
        walletFinderService().onEach { result ->
            result.data
//            when (result) {
//                is Resource.Success -> {
//                    _state.value = result.data
//                }
//                is Resource.Error -> {
//                    _state.value = TransactionListState(
//                        error = result.message ?: "An unexpected error occurred"
//                    )
//                }
//                is Resource.Loading -> {
//                    _state.value = TransactionListState(isLoading = true)
//                }
//            }
        }.launchIn(viewModelScope)
    }
}
