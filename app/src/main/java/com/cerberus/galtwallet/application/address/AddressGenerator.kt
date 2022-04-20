package com.cerberus.galtwallet.application.address

import android.util.Log
import com.cerberus.galtwallet.application.ApplicationService
import com.cerberus.galtwallet.domain.Address
import com.cerberus.galtwallet.domain.Wallet
import javax.inject.Inject

private const val id = "AddressGenerator"

class AddressGenerator @Inject constructor(
    private val wallet: Wallet
): ApplicationService<Unit, Address>(id) {
    override suspend fun action(request: Unit): Address {
        val address = wallet.getNewAddress()
        Log.d(id, "address generated ${address.plainAddress}")
        return address
    }
}
