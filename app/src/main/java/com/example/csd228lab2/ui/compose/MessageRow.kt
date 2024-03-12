package com.example.csd228lab2.ui.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.csd228lab2.models.Message

@Composable
fun MessageRow(message: Message) {
    Card {
        Box(modifier = Modifier.fillMaxWidth()) {
            message.text?.let { Text(it) }
    }
    }
}