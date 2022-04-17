package com.cerberus.galtwallet.domain.repository

import com.cerberus.galtwallet.domain.Transaction

interface TransactionRepository {
    suspend fun findAll(): List<Transaction>
}
