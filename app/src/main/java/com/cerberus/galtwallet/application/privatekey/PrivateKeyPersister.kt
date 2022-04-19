package com.cerberus.galtwallet.application.privatekey

import com.cerberus.galtwallet.application.ApplicationService
import com.cerberus.galtwallet.domain.PrivateKey
import com.cerberus.galtwallet.domain.repository.PrivateKeyRepository
import javax.inject.Inject

private const val id = "PrivateKeyPersister"

class PrivateKeyPersister @Inject constructor(
    private val repository: PrivateKeyRepository
): ApplicationService<PrivateKey, Unit>(id){

    override suspend fun action(request: PrivateKey) {
        repository.save(request)
    }
}
