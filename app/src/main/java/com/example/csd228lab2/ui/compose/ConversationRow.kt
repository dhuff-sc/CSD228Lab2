package com.example.csd228lab2.ui.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.csd228lab2.models.Conversation

/*
* This is a composable function that represents a single row in the conversation list
* Each conversation row is a card with the conversation's users listed
 */
@Composable
fun ConversationRow (convo : Conversation, onClick : (Int) -> Unit) {
    Card(
        modifier = Modifier.clickable(onClick = {onClick(convo.id)})
            .fillMaxWidth()
            .padding(8.dp)
            .testTag("conversationRow")
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(text = convo.users.joinToString { it.userName })
        }

    }
}