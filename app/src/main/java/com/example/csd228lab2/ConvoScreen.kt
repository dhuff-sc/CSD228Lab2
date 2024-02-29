package com.example.csd228lab2

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.csd228lab2.ui.ConvoViewModel

@Composable
fun ConvoScreen( navController: NavController,
//                 viewModel: ConvoViewModel = viewModel()
) {
    Column {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text("Conversation goes here")
        }
    }
}