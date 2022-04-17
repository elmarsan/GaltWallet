package com.cerberus.galtwallet.domain


abstract class Wallet(protected val privateKey: PrivateKey) {
    abstract fun getBalance(): Amount

    abstract fun getNewAddress(): Address

    abstract fun broadcastTransaction(transaction: Transaction)
}
