package com.example.csd228lab2.services

import com.example.csd228lab2.models.Message

/**
 * Messenger Service
 * author: Daniel Huff, @dhuff-sc
 * date: 2024-02-14
 */

interface Messager {
    fun send(message: Message): Message
    fun delete(message: Message)
    fun refresh()
}

// 8. Delete messages from a conversation
class Messenger: Messager {
    override fun send(message:Message): Message {
        return message
    }

    override fun delete(message: Message) {
    }

    override fun refresh() {}
}

// 2. Send messages while offline
interface MessagerQueue {
    fun queueMessage(message: Message)
    fun sendQueuedMessages()
    fun isOnline(): Boolean
}

class MessengerQueue: MessagerQueue {
    private val messages = mutableListOf<Message>()
    override fun queueMessage(message: Message) {
        messages.add(message)
    }
    override fun sendQueuedMessages() {
        if (isOnline()) {
            for (message in messages) {
                Messenger().send(message)
            }
        }
    }
    override fun isOnline(): Boolean {
        return true
    }
}

