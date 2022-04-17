package com.cerberus.galtwallet.application.wallet

import com.cerberus.galtwallet.domain.PrivateKey
import com.cerberus.galtwallet.domain.repository.PrivateKeyRepository
import com.cerberus.galtwallet.shared.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PrivateKeyPersister @Inject constructor(
    private val repository: PrivateKeyRepository
){
    operator fun invoke(key: PrivateKey): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading<Unit>())
        repository.save(key)
        emit(Resource.Success<Unit>(Unit))
    }
}
