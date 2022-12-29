package com.cerberus.galtwallet.domain

abstract class Wallet {
    abstract suspend fun setup()

    abstract fun getBalance(): Amount

    abstract fun getNewAddress(): Address

    abstract fun broadcastTransaction(transactionBroadcastDTO: TransactionBroadcastDTO)

    abstract fun getTransactionList(): List<Transaction>
}
