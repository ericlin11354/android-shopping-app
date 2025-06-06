package com.example.goshopping.data.prefs

import kotlinx.coroutines.flow.Flow

interface GoShoppingPrefs {
    val rotationEnabledStream: Flow<Boolean>

    suspend fun toggleRotation()
}

