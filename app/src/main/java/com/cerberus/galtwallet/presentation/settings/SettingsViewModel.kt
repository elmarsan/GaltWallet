package com.cerberus.galtwallet.presentation.settings

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(

): ViewModel() {
    private val _state = mutableStateOf(true)
    val state: State<Boolean> = _state
}