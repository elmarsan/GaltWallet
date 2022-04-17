package com.cerberus.galtwallet.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cerberus.galtwallet.application.wallet.PrivateKeyFinder
import com.cerberus.galtwallet.domain.PrivateKey
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val privateKeyFinder: PrivateKeyFinder
): ViewModel() {
    private val _state = mutableStateOf<PrivateKey?>(null)
    val state: State<PrivateKey?> = _state

    init {
        privateKeyFinder.invoke().onEach {  key ->
            _state.value = key
        }.launchIn(viewModelScope)
    }
}
