package com.cerberus.galtwallet.presentation.initialize.create

import com.cerberus.galtwallet.domain.PrivateKey

data class CreateWalletState(
    val isLoading: Boolean = false,
    val privateKey: PrivateKey? = null,
    val error: String = ""
)
