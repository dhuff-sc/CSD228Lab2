package com.example.csd228lab2.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.csd228lab2.ui.viewmodels.CreateUserViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

/*
* This is a composable function that represents the create user screen
* It allows the user to create a new user with a username and email
* The user can click the create user button to create a new user which will also navigate to the convo list
* The user may return to this screen from the convo list screen
 */
@Composable
fun CreateUserScreen(
    cb : () -> Unit,
    viewModel: CreateUserViewModel = viewModel()
) {
    var userName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .testTag("createUserScreen")
        ) {
            Text("Create User")
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(value = userName,
                onValueChange = { userName = it},
                label = { Text("Username") }
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(value = email,
                onValueChange = { email = it},
                label = { Text("Email") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    viewModel.createUser(userName, email)
                    cb()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Create User")
        }
    }
}