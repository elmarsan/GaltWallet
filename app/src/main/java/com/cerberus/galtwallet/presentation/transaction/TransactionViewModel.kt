package com.cerberus.galtwallet.presentation.transaction

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cerberus.galtwallet.application.transaction.TransactionListFinder
import com.cerberus.galtwallet.domain.Transaction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(
    private val transactionListFinder: TransactionListFinder
): ViewModel() {

    val loading = mutableStateOf(true)
    val error = mutableStateOf("")
    val data = mutableStateOf<List<Transaction>>(emptyList())

    init {
        getTransactionList()
    }

    private fun getTransactionList() {
        transactionListFinder(Unit)
            .onStart { loading.value = true }
            .onCompletion { loading.value = false }
            .catch { err -> error.value = err.message ?: "An error occurred while getting the transaction history" }
            .onEach { data.value = it }
            .launchIn(viewModelScope)
    }
}