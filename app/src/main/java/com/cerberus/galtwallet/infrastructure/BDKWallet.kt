package com.cerberus.galtwallet.infrastructure

import android.util.Log
import com.cerberus.galtwallet.domain.Address
import com.cerberus.galtwallet.domain.Amount
import com.cerberus.galtwallet.domain.Transaction
import com.cerberus.galtwallet.domain.Wallet
import com.cerberus.galtwallet.domain.repository.PrivateKeyRepository
import org.bitcoindevkit.*
import javax.inject.Inject

private const val TAG = "BDKWallet"

class BDKWallet @Inject constructor(
    private val privateKeyRepository: PrivateKeyRepository
) : Wallet() {
    private lateinit var wallet: org.bitcoindevkit.Wallet

    object LogProgress : BdkProgress {
        override fun update(progress: Float, message: String?) {
            Log.d(TAG, "syncing wallet $progress $message")
        }
    }

    override suspend fun setup() {
        val privateKey = privateKeyRepository.find()!!

        Log.d(TAG, "setup wallet with private key: ${privateKey.key}")

        wallet = Wallet(
            "wpkh([c258d2e4/84/1/0]${privateKey.key}/0/*)",
            "wpkh([c258d2e4/84/1/0]${privateKey.key}/1/*)",
            Network.TESTNET,
            DatabaseConfig.Memory,
            BlockchainConfig.Electrum(
                ElectrumConfig("ssl://electrum.blockstream.info:60002", null, 5u, null, 10u)
            )
        )

        wallet.sync(LogProgress, null)
    }

    override fun getBalance(): Amount {
        return Amount(wallet.getBalance())
    }

    override fun getNewAddress(): Address {
        return Address(wallet.getNewAddress())
    }

    override fun broadcastTransaction(transaction: Transaction) {
        TODO("Not yet implemented")
    }
}
