package com.cerberus.galtwallet.application

import com.cerberus.galtwallet.domain.Transaction
import com.cerberus.galtwallet.domain.repository.TransactionRepository
import com.cerberus.galtwallet.shared.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TransactionListService @Inject constructor(
    private val repository: TransactionRepository
){
    operator fun invoke(): Flow<Resource<List<Transaction>>> = flow {
        try {
            emit(Resource.Loading<List<Transaction>>())
            val transactionList = repository.findAll()
            emit(Resource.Success<List<Transaction>>(transactionList))
        } catch (e: Exception) {
            emit(Resource.Error<List<Transaction>>(e.message ?: "An error occurred while retrieving transaction history"))
        }
    }
}
