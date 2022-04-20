package com.cerberus.galtwallet.domain

import java.math.BigDecimal

class Amount(private val satoshi: Long) {
    private val btc = BigDecimal(satoshi.toString())

    val satoshis get(): String {
        return this.satoshi.toString()
    }

    val btcString get(): String {
        return btc.divide(BigDecimal("100000000")).toString()
    }
}
