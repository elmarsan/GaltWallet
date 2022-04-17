package com.cerberus.galtwallet.domain

data class Transaction(
    val confirmations: Int?,
    val hash: String?,
    val fees: Amount,
    val amount: Amount,
    val block: Block,
    val from: String?,
    val to: String?
)
