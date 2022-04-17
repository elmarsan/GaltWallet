package com.cerberus.galtwallet.infrastructure.repository

import android.content.Context
import com.cerberus.galtwallet.domain.PrivateKey
import com.cerberus.galtwallet.domain.repository.PrivateKeyRepository

class SharedPreferencePrivateKeyRepository(
    private val context: Context
) : PrivateKeyRepository {
    private val XPRV = "xprv"
    private val MNEMONIC = "mnemonic"
    private val sharedPreferences = context.getSharedPreferences("current_wallet", Context.MODE_PRIVATE)

    override suspend fun find(): PrivateKey? {
        val xprv = sharedPreferences.getString(XPRV, null)
        val mnemonic = sharedPreferences.getString(MNEMONIC, null)

        if (xprv.isNullOrEmpty() || mnemonic.isNullOrEmpty())
            return null

        return PrivateKey.fromMnemonicString(mnemonic)
    }

    override suspend fun save(privateKey: PrivateKey) {
        sharedPreferences
            .edit()
            .apply {
                putString(XPRV, privateKey.key)
                putString(MNEMONIC, privateKey.mnemonic.joinToString(" "))
                putString("descriptor", privateKey.key)
                putString("changeDescriptor", privateKey.key)
            }.apply()
    }

    override suspend fun delete() {
        sharedPreferences
            .edit()
            .apply {
                putString(XPRV, null)
                putString(MNEMONIC, null)
            }.apply()
    }


}
