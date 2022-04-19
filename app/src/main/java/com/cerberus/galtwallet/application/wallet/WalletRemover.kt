package com.cerberus.galtwallet.application.wallet

import com.cerberus.galtwallet.application.ApplicationService
import com.cerberus.galtwallet.domain.repository.PrivateKeyRepository
import javax.inject.Inject

private const val id = "WalletRemover"

class WalletRemover @Inject constructor(
    private val privateKeyRepository: PrivateKeyRepository
): ApplicationService<Unit, Unit>(id) {
    override suspend fun action(request: Unit) {
        privateKeyRepository.delete()
    }
}
