package com.vontage.app.presentation.setup

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

// DataStore instance
private val Context.dataStore by preferencesDataStore("vontage_prefs")

// Keys
private val FIRST_LAUNCH_KEY = booleanPreferencesKey("first_launch")

class SetupViewModel @Inject constructor(
    private val context: Context
) : ViewModel() {

    // Check if this is the first launch
    val isFirstLaunch: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[FIRST_LAUNCH_KEY] ?: true // Default to true
        }

    // Mark setup as completed
    suspend fun markSetupCompleted() {
        context.dataStore.edit { preferences ->
            preferences[FIRST_LAUNCH_KEY] = false
        }
    }
}
