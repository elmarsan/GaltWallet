package com.cerberus.galtwallet.application.wallet

import com.cerberus.galtwallet.domain.PrivateKey
import com.cerberus.galtwallet.domain.repository.PrivateKeyRepository
import com.cerberus.galtwallet.shared.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PrivateKeyGenerator @Inject constructor(
    private val repository: PrivateKeyRepository
){
    operator fun invoke(): Flow<Resource<PrivateKey>> = flow {
        emit(Resource.Loading<PrivateKey>())
        val key = repository.generate()
        emit(Resource.Success<PrivateKey>(key))
    }
}
