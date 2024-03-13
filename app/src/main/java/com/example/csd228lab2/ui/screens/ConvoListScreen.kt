package com.example.csd228lab2.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.twotone.AccountCircle
import androidx.compose.material.icons.twotone.AddCircle
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.csd228lab2.ui.viewmodels.ConvoListViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.csd228lab2.models.Conversation
import com.example.csd228lab2.ui.compose.ConversationRow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConvoListScreen(
    cb : () -> Unit = {},
    viewModel: ConvoListViewModel = viewModel(),
    navToConvo: (Int) -> Unit
) {


    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Convo List") },
                navigationIcon = {
                    IconButton(onClick = { cb() }) {
                        Icon(Icons.TwoTone.AccountCircle, contentDescription = "Create Account")
                    }
                }
            )
        },
        floatingActionButton = {
            IconButton(onClick = {}) {
                Icon(Icons.TwoTone.AddCircle, contentDescription = "Create Convo")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            viewModel.convoList.forEach { convo ->
                ConversationRow(convo, onClick = navToConvo)
            }
        }
    }
}
