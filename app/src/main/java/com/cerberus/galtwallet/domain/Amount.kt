package com.cerberus.galtwallet.domain

class Amount(private val satoshi: ULong) {
    private val btc = satoshi / 100000000u

    val satoshis get(): String {
        return this.satoshi.toString()
    }

    val btcString get(): String {
        return "%.8f".format(btc)
    }
}
