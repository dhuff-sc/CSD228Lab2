package com.example.csd228lab2.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.csd228lab2.ui.viewmodels.ConvoListViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ConvoListScreen(
    navController: NavController,
    viewModel: ConvoListViewModel = viewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Conversations")
        Spacer(modifier = Modifier.height(16.dp))
        Text("Conversations are listed here")
        }
    }
