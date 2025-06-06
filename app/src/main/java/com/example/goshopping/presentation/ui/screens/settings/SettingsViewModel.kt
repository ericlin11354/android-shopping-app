package com.example.goshopping.presentation.ui.screens.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.goshopping.data.prefs.GoShoppingPrefs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(private val prefs: GoShoppingPrefs) : ViewModel() {
    private val _uiState = MutableStateFlow(SettingsUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            prefs.rotationEnabledStream
                .collect {
                    _uiState.value = _uiState.value.copy(rotationEnabled = it)
                }
        }
    }

    fun handleIntent(intent: SettingsIntent) {
        when (intent) {
            SettingsIntent.TOGGLE_ROTATION -> toggleRotation()
        }
    }

    private fun toggleRotation() {
        viewModelScope.launch {
            prefs.toggleRotation()
        }
    }

}
