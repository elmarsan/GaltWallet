package com.cerberus.galtwallet.presentation.transaction

import com.cerberus.galtwallet.domain.Transaction

data class TransactionListState(
    val isLoading: Boolean = false,
    val transactions: List<Transaction> = emptyList(),
    val error: String = ""
)
