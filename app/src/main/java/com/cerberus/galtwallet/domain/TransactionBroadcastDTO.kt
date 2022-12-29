package com.cerberus.galtwallet.domain

data class TransactionBroadcastDTO(
    val to: String,
    val amount: Amount,
)