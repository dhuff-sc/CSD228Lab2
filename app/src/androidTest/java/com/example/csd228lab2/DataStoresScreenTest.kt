package com.example.csd228lab2

import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.csd228lab2.ui.screens.DataStoresScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import kotlinx.coroutines.test.runTest

@RunWith(AndroidJUnit4::class)
class DataStoresScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun dataStoresScreentest() = runTest {
        val mockDataStorePreferences: DataStore<Preferences> = createDataStore("testPreferences")
        val mockDataStoreAppSettings: DataStore<AppSettings> = createDataStore("testAppSettings")

        composeTestRule.setContent {
            DataStoresScreen(onBack = {}, dataStore = mockDataStorePreferences, settingsDataStore = mockDataStoreAppSettings)

            composeTestRule.onNodeWithText("Dark Mode Example").assertExists()
            composeTestRule.onNodeWithText("Back").assertExists()
            composeTestRule.onNodeWithTag("column").assertExists().onChildren()

            composeTestRule.onNodeWithTag("switch1").assertExists()
            composeTestRule.onNodeWithTag("switch2").assertExists()
            composeTestRule.onNodeWithTag("check1").assertExists()
            composeTestRule.onNodeWithTag("check2").assertExists()

            composeTestRule.onNodeWithTag("switch1").performClick()
            composeTestRule.onNodeWithTag("switch2").performClick()

            composeTestRule.onNodeWithTag("check1").assertIsEnabled()
            composeTestRule.onNodeWithTag("check2").assertIsEnabled()
        }
    }
}