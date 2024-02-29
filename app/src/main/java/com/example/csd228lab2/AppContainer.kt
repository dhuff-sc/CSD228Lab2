package com.example.csd228lab2

import com.example.csd228lab2.models.Message
import com.example.csd228lab2.models.User
import com.example.csd228lab2.models.Conversation

interface AppContainer {
    val User: User
    val Message: Message
    val Conversation: Conversation
}

//class AppContainerImp : AppContainer {
//    override val User: User = User()
//    override val Message: Message = Message()
//    override val Conversation: Conversation = Conversation()
//}