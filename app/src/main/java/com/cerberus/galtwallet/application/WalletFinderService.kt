package com.cerberus.galtwallet.application

import android.util.Log
import com.cerberus.galtwallet.domain.PrivateKey
import com.cerberus.galtwallet.domain.repository.PrivateKeyRepository
import com.cerberus.galtwallet.shared.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

private const val TAG = "WalletFinderService"

class WalletFinderService @Inject constructor(
    private val privateKeyRepository: PrivateKeyRepository
){
    operator fun invoke(): Flow<Resource<PrivateKey?>> = flow {
        try {
            emit(Resource.Loading<PrivateKey?>())
            val privateKey = privateKeyRepository.find()

            Log.d(TAG, "private key: ${privateKey?.key}")

            emit(Resource.Success<PrivateKey?>(privateKey))
        } catch (e: Exception) {
            emit(Resource.Error<PrivateKey?>(e.message ?: "An error occurred while finding wallet"))
        }
    }
}
