package com.example.csd228lab2.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.csd228lab2.models.Conversation
import com.example.csd228lab2.models.Message
import com.example.csd228lab2.models.User

class ConvoViewModel : ViewModel() {
    var User1 = User(id = 1, userName = "Alice", email = "", avatar = 'A')
    var User2 = User(id = 2, userName = "Bob", email = "", avatar = 'B')
    var convo = Conversation(
        id = 1,
        users = listOf(User1, User2),
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
    fun listMessages(convo : Conversation) : List<Message> {
        return convo.messages
            }
        }


