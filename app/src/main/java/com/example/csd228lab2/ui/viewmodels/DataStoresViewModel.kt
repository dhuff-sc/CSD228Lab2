package com.example.csd228lab2.ui.viewmodels


import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch


// Need to create two preferences using PreferenceDataStore and ProtoDataStore
// Used to change the theme of the app to darkMode or lightMode
class DataStoresViewModel(private val dataStore: DataStore<Preferences>) : ViewModel() {

    private val _darkModeState = MutableStateFlow(false)
    val darkModeState: StateFlow<Boolean> = _darkModeState

    init {
        viewModelScope.launch {
            dataStore.data.map { preferences ->
                preferences[DARK_MODE_KEY] ?: false
            }.collect {
                _darkModeState.value = it
            }
        }
    }
    fun toggleDarkModePref(isDarkMode: Boolean) {
            viewModelScope.launch {
                dataStore.edit { preferences ->
                    preferences[DARK_MODE_KEY] = isDarkMode
                }
            }
    }
    companion object {
        private val DARK_MODE_KEY = booleanPreferencesKey("dark_mode_pref")
    }

}
