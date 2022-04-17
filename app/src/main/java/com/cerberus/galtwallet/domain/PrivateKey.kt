package com.cerberus.galtwallet.domain

import android.util.Log
import org.bitcoindevkit.Network
import org.bitcoindevkit.WordCount
import org.bitcoindevkit.generateExtendedKey
import org.bitcoindevkit.restoreExtendedKey

class PrivateKey private constructor(
    val key: String,
    var mnemonic: List<String>
) {
    companion object Factory {
        fun generate(): PrivateKey {
            val extendedKey = generateExtendedKey(
                network = Network.TESTNET,
                wordCount = WordCount.WORDS12,
                password = null
            )

            return PrivateKey(
                key = extendedKey.xprv,
                mnemonic = extendedKey.mnemonic.split(" ")
            )
        }

        fun fromMnemonicString(mnemonicString: String): PrivateKey {
            Log.d("PrivateKey", mnemonicString)
            val extendedKey = restoreExtendedKey(
                network = Network.TESTNET,
                mnemonic = mnemonicString,
                password = null
            )

            return PrivateKey(
                key = extendedKey.xprv,
                mnemonic = extendedKey.mnemonic.split(" ")
            )
        }

        fun fromMnemonicList(mnemonicList: List<String>): PrivateKey {
            val extendedKey = restoreExtendedKey(
                network = Network.TESTNET,
                mnemonic = mnemonicList.joinToString(" "),
                password = null
            )

            return PrivateKey(
                key = extendedKey.xprv,
                mnemonic = mnemonicList
            )
        }
    }
}
