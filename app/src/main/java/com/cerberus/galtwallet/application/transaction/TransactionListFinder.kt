package com.cerberus.galtwallet.application.transaction

import com.cerberus.galtwallet.application.ApplicationService
import com.cerberus.galtwallet.domain.Transaction
import com.cerberus.galtwallet.domain.Wallet
import javax.inject.Inject

private const val id = "TransactionListFinder"

class TransactionListFinder @Inject constructor(
    private val wallet: Wallet

): ApplicationService<Unit, List<Transaction>>(id) {

    override suspend fun action(request: Unit): List<Transaction> {
        return wallet.getTransactionList()
    }
}
