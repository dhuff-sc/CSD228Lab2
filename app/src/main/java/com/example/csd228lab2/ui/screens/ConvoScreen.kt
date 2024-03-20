package com.example.csd228lab2.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.csd228lab2.ui.compose.MessageRow
import com.example.csd228lab2.ui.viewmodels.ConvoViewModel

/*
* This is a composable function that represents the conversation screen
* It displays a list of messages in a conversation
* Each message is a card with the message's sender and text listed
* From here, the user can navigate back to the conversation list screen
 */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConvoScreen( onBack: () -> Unit,
                 viewModel: ConvoViewModel = viewModel()
) {
    Scaffold( modifier = Modifier.testTag("convoScaffold"),
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Convo") },
                navigationIcon = {
                    IconButton(onClick = onBack, modifier = Modifier.testTag("convoBackButton")) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        bottomBar = {
            BottomAppBar(){
                OutlinedTextField( value = "Type a message...",
                    onValueChange = { /*TODO*/}
                )
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(Icons.Filled.Send, contentDescription = "Send")

                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            for (message in viewModel.convo.messages) {
                MessageRow(message = message)
            }

        }

    }
}

