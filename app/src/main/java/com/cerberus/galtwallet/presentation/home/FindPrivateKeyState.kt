package com.cerberus.galtwallet.presentation.home

import com.cerberus.galtwallet.domain.PrivateKey

class FindPrivateKeyState(
    val isLoading: Boolean = false,
    val privateKey: PrivateKey? = null,
    val error: String = ""
)