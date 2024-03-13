package com.example.csd228lab2.ui.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.csd228lab2.models.Message

/*
* This is a composable function that represents a single row in the conversation message list
* Each row is a message in the conversation
* Each message is a card with the message's sender and text listed
 */
@Composable
fun MessageRow(message: Message) {
    Card {
        Box( modifier = Modifier.fillMaxWidth()) {
            message.sender?.let { Text(
                modifier = Modifier.padding(8.dp),
                text = it.userName + ": " + message.text
            )
            }
        }
    }
}