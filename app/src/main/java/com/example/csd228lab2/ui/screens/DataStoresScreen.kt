package com.example.csd228lab2.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.csd228lab2.ui.viewmodels.DataStoresViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.csd228lab2.AppSettings

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DataStoresScreen(
    onBack: () -> Unit,
    dataStore: DataStore<Preferences>,
    settingsDataStore: DataStore<AppSettings>,
    viewModel: DataStoresViewModel = viewModel(factory = DataStoresViewModel.DataStoresViewModelFactory(
        dataStore,
        settingsDataStore
    )
    )
    ) {
    val darkModeState by viewModel.darkModeState.collectAsState()
//    val useLocalFSStorageState by viewModel.useLocalFSStorage.collectAsState(initial = false)

    val triggerDarkMode = remember { mutableStateOf(false) }

    LaunchedEffect(triggerDarkMode.value) {
        if (triggerDarkMode.value) {
            viewModel.toggleDarkMode(triggerDarkMode.value)
            triggerDarkMode.value = false
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Dark Mode Example") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }

            )
        },
        content = {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize()
            ) {
                Spacer(modifier = Modifier.padding(26.dp))
                Text(text = "Toggle Dark Mode (Preferences)", modifier = Modifier.padding(top = 16.dp))
                Switch(
                    checked = darkModeState,
                    onCheckedChange = { viewModel.toggleDarkModePref(it) },
                    modifier = Modifier
                )

                Text(text = "Dark Mode is on (Pref):", modifier = Modifier.padding(top = 16.dp))
                Checkbox(
                    checked = darkModeState,
                    onCheckedChange = null,
                    modifier = Modifier)
                Spacer(modifier = Modifier.padding(8.dp))
//                Switch(
//                    checked = useLocalFSStorageState,
//                    onCheckedChange = { newValue ->
//                        triggerDarkMode.value = newValue
//                    },
//                    modifier = Modifier
//                )
//                Checkbox(
//                    checked = useLocalFSStorageState,
//                    onCheckedChange = null,
//                    modifier = Modifier)

            }
        }
    )
}