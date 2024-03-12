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

fun NavController.convoList() {
    navigate("convoList")
}
fun NavController.createUser() {
    navigate("createUser")
}

fun NavController.convo(convoId: Int) {
    navigate("convo/$convoId")
}
@Preview
@Composable
fun ChatApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "createUser", modifier = modifier) {
        composable("convoList") {
            ConvoListScreen(cb = {}, navToConvo = {navController.convo(it)})
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
