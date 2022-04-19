package com.cerberus.galtwallet.presentation.transaction

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cerberus.galtwallet.application.transaction.TransactionListService
import com.cerberus.galtwallet.shared.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(
    private val transactionListService: TransactionListService
): ViewModel() {

    private val _state = mutableStateOf(TransactionListState())
    val state: State<TransactionListState> = _state

    init {
        getTransactionList()
    }

    private fun getTransactionList() {
        transactionListService().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = TransactionListState(transactions = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = TransactionListState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = TransactionListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}