package com.cerberus.galtwallet.application.transaction

import com.cerberus.galtwallet.application.ApplicationService
import com.cerberus.galtwallet.domain.TransactionBroadcastDTO
import com.cerberus.galtwallet.domain.Wallet
import javax.inject.Inject

private const val id = "TransactionBroadcaster"

class TransactionBroadcaster @Inject constructor(
    private val wallet: Wallet
): ApplicationService<TransactionBroadcastDTO, Unit>(id) {
    override suspend fun action(request: TransactionBroadcastDTO) {
        wallet.broadcastTransaction(transactionBroadcastDTO = request)
    }
}
