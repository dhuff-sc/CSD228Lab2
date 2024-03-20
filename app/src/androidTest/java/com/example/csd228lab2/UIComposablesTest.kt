package com.example.csd228lab2

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.csd228lab2.models.Conversation
import com.example.csd228lab2.models.Message
import com.example.csd228lab2.models.User
import com.example.csd228lab2.ui.compose.ConversationRow
import com.example.csd228lab2.ui.compose.MessageRow
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UIComposablesTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val User1 = User(id = 1, userName = "Alice", email = "", avatar = 'A')
    private val User2 = User(id = 2, userName = "Bob", email = "", avatar = 'B')
    private val testConversation = Conversation(
        id = 1,
        convoName = "Alice and Bob",
        users = listOf(User1, User2
        ),
        messages = listOf(
            Message(
                id = 1,
                sender = User1,
                text = "Hello, Bob!"
            ),
            Message(
                id = 2,
                sender = User2,
                text = "Hi, Alice!"
            )
        )
    )

    @Test
    fun messageRowTest() {
        val message = Message(
            id = 1,
            sender = User1,
            text = "Hello!"
        )
        composeTestRule.setContent {
            MessageRow(message = message)
        }
        composeTestRule.onNodeWithTag("messageRow").assertExists()
        composeTestRule.onNodeWithText("Alice: Hello!").assertExists()
    }
        @Test
        fun conversationRowTest() {

            composeTestRule.setContent {
                ConversationRow(convo = testConversation, onClick = {})
            }

            composeTestRule.onNodeWithTag("conversationRow").assertExists()
            composeTestRule.onNodeWithText("Alice, Bob").assertExists()

            composeTestRule.onNodeWithTag("conversationRow").performClick()
        }
    }
