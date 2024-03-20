package com.example.csd228lab2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.csd228lab2.ui.screens.ConvoListScreen
import com.example.csd228lab2.ui.screens.ConvoScreen
import com.example.csd228lab2.ui.screens.CreateUserScreen
import com.example.csd228lab2.ui.theme.CSD228Lab2Theme

/*
* This is the main activity for the app
* It establishes the content theme and sets the content to the ChatApp composable
* The ChatApp composable is the main entry point for the app
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CSD228Lab2Theme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    ChatApp()
                }
            }
        }
    }
}

/*
* These NavControllers are extension functions that allow for easy navigation between screens
* They are used in the ChatApp composable to navigate between the conversation list,
* create user, and conversation screens
 */
fun NavController.convoList() {
    navigate("convoList")
}
fun NavController.createUser() {
    navigate("createUser")
}

fun NavController.convo(convoId: Int) {
    navigate("convo/$convoId")
}

/*
* This is the ChatApp composable function
* This contains the NavHost component which allows for navigation between the conversation list,
* create user, and conversation screens. We also set our startDestination to the conversation list screen
* We also establish the navController extensions for easier testing
 */
@Preview
@Composable
fun ChatApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "convoList", modifier = modifier) {
        composable("convoList") {
            ConvoListScreen(cb = {navController.createUser()}, navToConvo = {navController.convo(it)})
        }
        composable("createUser") {
            CreateUserScreen(cb = {navController.convoList()})
        }
        composable("convo/{convoId}") { backStackEntry ->
            ConvoScreen(onBack = {navController.popBackStack()})
//                convoId = backStackEntry.arguments?.getString("convoId")!!
        }
    }

}
