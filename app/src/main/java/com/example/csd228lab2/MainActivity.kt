package com.example.csd228lab2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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

@Composable
fun ChatApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "createUser", modifier = modifier) {
        composable("convoList") {
            ConvoListScreen(navController = navController)
        }
        composable("createUser") {
            CreateUserScreen(navController = navController)
        }
        composable("convo/{convoId}") { backStackEntry ->
            ConvoScreen(
                navController = navController,
//                convoId = backStackEntry.arguments?.getString("convoId")!!
            )
        }
    }

}
