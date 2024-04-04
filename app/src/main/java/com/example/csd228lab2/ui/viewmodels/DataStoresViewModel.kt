package com.example.csd228lab2.ui.viewmodels

import com.example.csd228lab2.AppSettings
import android.content.Context
import android.util.Log
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.protobuf.InvalidProtocolBufferException
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.io.InputStream
import java.io.OutputStream


// Need to create two preferences using PreferenceDataStore and ProtoDataStore
// Used to change the theme of the app to darkMode or lightMode
class DataStoresViewModel(private val dataStore: DataStore<Preferences>,
                          private val settingsDataStore: DataStore<AppSettings>) : ViewModel() {

    private val _darkModeState = MutableStateFlow(false)
    val darkModeState: StateFlow<Boolean> = _darkModeState

    val useLocalFSStorage: Flow<Boolean> = settingsDataStore.data.map {
        it.useLocalFsStorage
    }

    val useDarkMode: Flow<Boolean> = settingsDataStore.data.map {
        it.useDarkMode
    }



    // PreferenceDataStore version
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

    suspend fun toggleUseLocalFSStorage() {
        settingsDataStore.updateData { settings ->
            settings.toBuilder()
                .setUseLocalFsStorage(!settings.useLocalFsStorage)
                .build()
        }

    }

    fun toggleDarkModeProto(isDarkMode: Boolean) {
        viewModelScope.launch {
            settingsDataStore.updateData { settings ->
                settings.toBuilder()
                    .setUseDarkMode(isDarkMode)
                    .build()
            }
        }
    }


// 1. Define the custom Settings serializer
// 1.1. Use Settings from app/src/main/proto/settings.proto
object SettingsSerializer: Serializer<AppSettings> {
    // 1.2. Provide a default value in the event that the file is not yet created.
    override val defaultValue: AppSettings = AppSettings.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): AppSettings {
        try {
            return AppSettings.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            Log.d("SettingsSerializer", "Unable to read settings proto.")
            throw CorruptionException("Cannot read proto.",exception)
        }
    }

    override suspend fun writeTo(t: AppSettings, output: OutputStream) = t.writeTo(output)
}

// 2. Use the property delegate to define the dataStore on the context
// 1.1. Use Settings type from app/src/main/proto/settings.proto
val Context.settingsDataStore: DataStore<AppSettings> by dataStore(
    // 2.1 Custom filename to store the datastore contents
    "settings.pb",
    // 2.2 Provide the custom Serializer from 1.2
    SettingsSerializer
)

class DataStoresViewModelFactory(private val dataStore: DataStore<Preferences>,
                                 private val settingsDataStore: DataStore<AppSettings>) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DataStoresViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DataStoresViewModel(dataStore, settingsDataStore) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
}


