package com.example.csd228lab2

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.csd228lab2.ui.screens.DataStoresScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DataStoresScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun dataStoresScreentest() {
        composeTestRule.setContent {
            val mockDataStorePreferences: DataStore<Preferences> = (DataStore::class.java) as DataStore<Preferences>
            val mockDataStoreAppSettings: DataStore<AppSettings> = (DataStore::class.java) as DataStore<AppSettings>

            DataStoresScreen(onBack = {}, dataStore = mockDataStorePreferences, settingsDataStore = mockDataStoreAppSettings)



        }
    }
}