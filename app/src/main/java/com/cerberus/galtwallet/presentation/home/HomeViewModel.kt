package com.cerberus.galtwallet.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cerberus.galtwallet.application.privatekey.PrivateKeyFinder
import com.cerberus.galtwallet.shared.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val privateKeyFinder: PrivateKeyFinder
): ViewModel() {
    private val _state = mutableStateOf(FindPrivateKeyState())
    val state: State<FindPrivateKeyState> = _state

    init {
        privateKeyFinder.invoke().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = FindPrivateKeyState(privateKey = result.data)
                }
                is Resource.Error -> {
                    _state.value = FindPrivateKeyState(
                        error = result.message ?: "An unexpected error occurred while finding wallet"
                    )
                }
                is Resource.Loading -> {
                    _state.value = FindPrivateKeyState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}
