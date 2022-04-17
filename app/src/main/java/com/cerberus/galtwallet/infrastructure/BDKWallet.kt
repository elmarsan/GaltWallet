package com.cerberus.galtwallet.infrastructure

import android.util.Log
import com.cerberus.galtwallet.domain.Address
import com.cerberus.galtwallet.domain.Amount
import com.cerberus.galtwallet.domain.PrivateKey
import com.cerberus.galtwallet.domain.Wallet
import com.cerberus.galtwallet.domain.Transaction
import org.bitcoindevkit.*

private const val TAG = "BDKWallet"

class BDKWallet(privateKey: PrivateKey) : Wallet(privateKey) {
    private var wallet: org.bitcoindevkit.Wallet = Wallet(
        "wpkh([c258d2e4/84/1/0]${privateKey.key}/0/*)",
        "wpkh([c258d2e4/84/1/0]$${privateKey.key}/1/*)",
        Network.TESTNET,
        DatabaseConfig.Memory,
        BlockchainConfig.Electrum(
            ElectrumConfig("ssl://electrum.blockstream.info:60002", null, 5u, null, 10u)
        )
    )

    object LogProgress: BdkProgress {
        override fun update(progress: Float, message: String?) {
            Log.d(TAG, "syncing wallet $progress $message")
        }
    }

    init {
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
