package com.cerberus.galtwallet.infrastructure.repository

import com.cerberus.galtwallet.domain.Amount
import com.cerberus.galtwallet.domain.Block
import com.cerberus.galtwallet.domain.Transaction
import com.cerberus.galtwallet.domain.repository.TransactionRepository

class MockTransactionRepository: TransactionRepository {
    override suspend fun findAll(): List<Transaction> {
        return listOf(
            Transaction(
                confirmations = 10,
                hash = "a7bfc9d361e4fd624589715274bdfe7e87eb6606f570ca06b70673b332bf910d",
                from = "tb1qyt7s3daksagcnsv4yuyfht4cglk3tgzm4vl3t9",
                to = "tb1qyt7s3daksagcnsv4yuyfht4cglk3tgzm4vl3t9",
                fees = Amount(1200),
                amount = Amount(500000),
                block = Block(722222, 1650109364)
            ),
            Transaction(
                confirmations = 10,
                hash = "a7bfc9d361e4fd624589715274bdfe7e87eb6606f570ca06b70673b332bf910d",
                from = "tb1qyt7s3daksagcnsv4yuyfht4cglk3tgzm4vl3t9",
                to = "tb1qyt7s3daksagcnsv4yuyfht4cglk3tgzm4vl3t9",
                fees = Amount(1200),
                amount = Amount(500000),
                block = Block(722222, 1650109364)
            ),
            Transaction(
                confirmations = 10,
                hash = "a7bfc9d361e4fd624589715274bdfe7e87eb6606f570ca06b70673b332bf910d",
                from = "tb1qyt7s3daksagcnsv4yuyfht4cglk3tgzm4vl3t9",
                to = "tb1qyt7s3daksagcnsv4yuyfht4cglk3tgzm4vl3t9",
                fees = Amount(1200),
                amount = Amount(500000),
                block = Block(722222, 1650109364)
            ),
        )
    }
}
