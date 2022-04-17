package com.cerberus.galtwallet.application.wallet

import com.cerberus.galtwallet.domain.PrivateKey
import com.cerberus.galtwallet.domain.repository.PrivateKeyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PrivateKeyFinder @Inject constructor(
    private val repository: PrivateKeyRepository
) {
    operator fun invoke(): Flow<PrivateKey?> = flow {
        emit(repository.find())
    }
}
