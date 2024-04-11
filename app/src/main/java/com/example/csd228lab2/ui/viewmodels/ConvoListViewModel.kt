package com.example.csd228lab2.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.csd228lab2.database.ChatAppDatabase
import com.example.csd228lab2.models.Conversation
import com.example.csd228lab2.models.Message
import com.example.csd228lab2.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/*
* Hardcoded data for the conversation list
* Temporary until database/repository is implemented
 */
class ConvoListViewModel(private val conversationDao: ChatAppDatabase.ConversationDao) : ViewModel() {
    var User1 = User(id = 1, userName = "Alice", email = "", avatar = 'A')
    var User2 = User(id = 2, userName = "Bob", email = "", avatar = 'B')
    var convo = Conversation(
        id = 1,
        convoName = "Alice and Bob",
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

    fun listConversations() : List<Conversation> {
        return conversationDao.getAllConversations()
    }

    fun getConversationById(id: Int) : Conversation? {
        return conversationDao.getConversationById(id)
    }




}