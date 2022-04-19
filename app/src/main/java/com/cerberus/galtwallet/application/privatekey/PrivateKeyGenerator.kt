package com.cerberus.galtwallet.application.privatekey

import com.cerberus.galtwallet.application.ApplicationService
import com.cerberus.galtwallet.domain.PrivateKey
import com.cerberus.galtwallet.domain.repository.PrivateKeyRepository
import javax.inject.Inject

private const val id = "PrivateKeyGenerator"

class PrivateKeyGenerator @Inject constructor(
    private val repository: PrivateKeyRepository,
): ApplicationService<Unit, PrivateKey>(id) {

    override suspend fun action(request: Unit): PrivateKey {
        return repository.generate()
    }
}
