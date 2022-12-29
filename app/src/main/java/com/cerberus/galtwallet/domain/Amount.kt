package com.cerberus.galtwallet.domain

import java.math.BigDecimal

class Amount(val uLongAmount: ULong) {
    override fun toString(): String {
        return BigDecimal(uLongAmount.toString()).divide(BigDecimal("100000000")).toString()
    }
}
