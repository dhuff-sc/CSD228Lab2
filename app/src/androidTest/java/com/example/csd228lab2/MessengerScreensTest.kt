package com.example.csd228lab2

import org.junit.Test
import org.junit.runner.RunWith
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.csd228lab2.ui.screens.ConvoListScreen
import com.example.csd228lab2.ui.screens.ConvoScreen
import com.example.csd228lab2.ui.screens.CreateUserScreen
import org.junit.Rule

@RunWith(AndroidJUnit4::class)
class MessengerScreensTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun convoListScreenTest() {
        composeTestRule.setContent {
            ConvoListScreen(navToConvo = {})
        }
        composeTestRule.onNodeWithTag("convoListScaffold").assertExists()
        composeTestRule.onNodeWithText("Convo List").assertExists()
        composeTestRule.onNodeWithContentDescription("Create Convo").assertExists()
        composeTestRule.onAllNodesWithTag("conversationRow").assertCountEquals(1)
    }
    @Test
    fun convoScreenTest() {
        composeTestRule.setContent {
//            ConvoScreen(onBack = {})
        }
        composeTestRule.onNodeWithTag("convoScaffold").assertExists()
        composeTestRule.onNodeWithText("Convo").assertExists()
        composeTestRule.onNodeWithContentDescription("Send").assertExists()
        composeTestRule.onNodeWithText("Type a message...").assertExists()
        composeTestRule.onAllNodesWithTag("messageRow").assertCountEquals(2)
        composeTestRule.onNodeWithTag("convoBackButton").assertExists()

        composeTestRule.onNodeWithTag("convoBackButton").performClick()
    }
    @Test
    fun createUserScreenTest() {
        composeTestRule.setContent {
            CreateUserScreen(cb = {})
        }

        composeTestRule.onNodeWithTag("createUserScreen").assertExists()
        composeTestRule.onNodeWithText("Username").assertExists()
        composeTestRule.onNodeWithText("Email").assertExists()
        composeTestRule.onNodeWithTag("createUserButton").assertExists()
        composeTestRule.onNodeWithTag("createUserText", true).assertExists()

        composeTestRule.onNodeWithTag("createUserButton").performClick()

    }
}