package com.cerberus.galtwallet.application.wallet

import com.cerberus.galtwallet.application.ApplicationService
import com.cerberus.galtwallet.domain.repository.PrivateKeyRepository
import javax.inject.Inject

private const val id = "WalletFinder"

class WalletFinder @Inject constructor(
    private val privateKeyRepository: PrivateKeyRepository
): ApplicationService<Unit, Boolean>(id) {

    override suspend fun action(request: Unit): Boolean {
        return privateKeyRepository.find() != null
    }
}
