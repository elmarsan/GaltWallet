package com.cerberus.galtwallet.domain.repository

import com.cerberus.galtwallet.domain.PrivateKey

interface PrivateKeyRepository {
    suspend fun find(): PrivateKey?

    suspend fun save(privateKey: PrivateKey)

    suspend fun delete()

    suspend fun generate(): PrivateKey {
        return PrivateKey.generate()
    }
}
