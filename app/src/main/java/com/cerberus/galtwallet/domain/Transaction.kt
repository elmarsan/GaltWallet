package com.cerberus.galtwallet.domain

enum class TransactionType {
    INCOMING, OUTCOMING
}

data class Transaction(
    val txId: String,
    val amount: Amount,
    val type: TransactionType
)
