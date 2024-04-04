package com.example.csd228lab2

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.csd228lab2.ui.screens.ConvoListScreen
import com.example.csd228lab2.ui.screens.ConvoScreen
import com.example.csd228lab2.ui.screens.CreateUserScreen
import com.example.csd228lab2.ui.theme.CSD228Lab2Theme
import com.example.csd228lab2.ui.screens.DataStoresScreen
import com.example.csd228lab2.ui.viewmodels.DataStoresViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch


/*
* This is the main activity for the app
* It establishes the content theme and sets the content to the ChatApp composable
* The ChatApp composable is the main entry point for the app
 */

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "darkModePreferences")
val Context.settingsDataStore: DataStore<AppSettings> by dataStore("settings.pb",
    DataStoresViewModel.SettingsSerializer
)
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val settingsDataStore: DataStore<AppSettings> = baseContext.settingsDataStore
            val viewModel: DataStoresViewModel = viewModel(factory = DataStoresViewModel.DataStoresViewModelFactory(
                dataStore,
                settingsDataStore))
            val darkModeState by viewModel.darkModeState.collectAsState()
            val protoDarkModeState by viewModel.useDarkMode.collectAsState(initial = false)

            CSD228Lab2Theme(darkTheme = darkModeState || protoDarkModeState) {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    ChatApp(dataStore = dataStore, settingsDataStore = settingsDataStore)
                }
            }
        }
        lifecycleScope.launch {
            try {
            baseContext.dataStore.data.first()
            } catch (e: Exception) {
                Log.d("DataStore", "Error: ${e.message}")
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

fun NavController.dataStores() {
    navigate("dataStores")
}

/*
* This is the ChatApp composable function
* This contains the NavHost component which allows for navigation between the conversation list,
* create user, and conversation screens. We also set our startDestination to the conversation list screen
* We also establish the navController extensions for easier testing
*/
@Composable
fun ChatApp(dataStore: DataStore<Preferences>, settingsDataStore: DataStore<AppSettings>, modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "convoList", modifier = modifier) {
        composable("convoList") {
            ConvoListScreen(
                cb = { navController.createUser()},
                navToConvo = {navController.convo(it)},
                navToDataStores = {navController.dataStores()}
            )
        }
        composable("createUser") {
            CreateUserScreen(cb = {navController.convoList()})
        }
        composable("convo/{convoId}") { _ ->
            ConvoScreen(onBack = {navController.popBackStack()})
//                convoId = backStackEntry.arguments?.getString("convoId")!!
        }
        composable("dataStores") {
            DataStoresScreen(
                onBack = {navController.popBackStack()},
                dataStore = dataStore,
                settingsDataStore = settingsDataStore
            )
        }
    }

}
