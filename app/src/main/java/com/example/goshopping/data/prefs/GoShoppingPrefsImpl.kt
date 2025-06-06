package com.example.goshopping.data.prefs

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GoShoppingPrefsImpl @Inject constructor(
    @ApplicationContext context: Context,
) : GoShoppingPrefs {
    private val Context.dataStore by preferencesDataStore(name = STORE_NAME)
    private val dataStore = context.dataStore

    override val rotationEnabledStream: Flow<Boolean> = dataStore.data.catch {
        emit(emptyPreferences())
    }.map {
        it[STORE_KEY_ROTATION] != false
    }

    override suspend fun toggleRotation() {
        dataStore.edit {
            it[STORE_KEY_ROTATION] = it[STORE_KEY_ROTATION]?.not() == true
        }
    }

    companion object {
        private const val STORE_NAME = "go_shopping_prefs"
        private val STORE_KEY_LOCAL_STORAGE = booleanPreferencesKey("local_storage")
        private val STORE_KEY_ROTATION = booleanPreferencesKey("rotation")
    }
}
