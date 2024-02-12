package com.example.csd228lab2.services

import com.example.csd228lab2.models.Message


interface Messager {
    fun send(message: Message): Message
    fun delete(message: Message)
    fun refresh()
}

class Messenger: Messager {
    override fun send(message:Message): Message {
        return Message()
    }

    override fun delete(message: Message) {
    }

    override fun refresh() {}
}

