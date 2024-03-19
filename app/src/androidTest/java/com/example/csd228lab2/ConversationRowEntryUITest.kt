package com.example.csd228lab2

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.csd228lab2.models.Conversation
import com.example.csd228lab2.models.Message
import com.example.csd228lab2.models.User
import com.example.csd228lab2.ui.compose.ConversationRow
import org.junit.runner.RunWith
import org.junit.Rule
import org.junit.Test


@RunWith(AndroidJUnit4::class)
class ConversationRowEntryUITest {

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

    private val lastMessage = Message(
        id = 2,
        sender = User(3, "Dan", "email@email.com", 'D'),
        text = "Hello!"
    )

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun showsConversationName() {
        composeTestRule.setContent {
            ConversationRow(
                convo = testConversation,
                onClick = {}
            )
        }
        composeTestRule.onNodeWithText("Alice and Bob").assertExists()
    }
    fun callsOnClick() {

    }
    fun showsLastMessageFromConversation() {

    }

    fun showTimestampOfMessage() {

    }
}