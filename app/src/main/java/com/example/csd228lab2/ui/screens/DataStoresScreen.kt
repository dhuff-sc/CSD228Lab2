package com.example.csd228lab2.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.csd228lab2.ui.viewmodels.DataStoresViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DataStoresScreen(
    onBack: () -> Unit,
    viewModel: DataStoresViewModel = viewModel()
) {
    val darkModeState by viewModel.darkModeState.collectAsState()

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
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Switch(
                    checked = darkModeState,
                    onCheckedChange = { viewModel.toggleDarkModePref(it) },
                    modifier = Modifier.padding(8.dp)
                )
                Text(text = "Toggle Dark Mode (Preferences)", modifier = Modifier.padding(top = 16.dp))

//                Switch(
//                    checked = viewModel.darkModeProto.value,
//                    onCheckedChange = {viewModel.toggleDarkModeProto(it)},
//                    modifier = Modifier.padding(8.dp)
//                )
//                Text(text = "Toggle Dark Mode (Proto)", modifier = Modifier.padding(top = 16.dp))
            }
        }
    )
}