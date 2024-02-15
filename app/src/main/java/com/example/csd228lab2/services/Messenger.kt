/**
 * Messenger Service
 * @author: Daniel Huff, @dhuff-sc
 * date: 2024-02-14
 */

package com.example.csd228lab2.services

import com.example.csd228lab2.models.Message

interface Messager {
    fun send(message: Message): Message
    fun delete(message: Message)
    fun refresh()
}

/**
 * Messenger class implements Messager interface
 * Meets requirements to send, delete, and refresh messages
 */
class Messenger: Messager {
    /**
     * Send a message
     * @param message: Any data format of the Message subclasses
     * @return Message
     */
    override fun send(message:Message): Message {
        return message
    }
    /**
     * Delete a message
     * @param message: Any data format of the Message subclasses
     */
    override fun delete(message: Message) {
    }
    /**
     * Refresh messages
     */
    override fun refresh() {}
}


interface MessagerQueue {
    fun queueMessage(message: Message)
    fun sendQueuedMessages(m: Messenger)
    fun isOnline(): Boolean
}

/**
 * MessengerQueue class implements MessagerQueue interface
 * Contains a list of messages to be sent
 * Allows queueing of messages and sending of queued messages
 * Allows checking if online before sending
 * Meets requirements to queue messages, send queued messages, and check if online
 */
class MessengerQueue: MessagerQueue {
    private val messages = mutableListOf<Message>()
    /**
     * Queue a message before check if online
     * @param message: Any data format of the Message subclasses
     */
    override fun queueMessage(message: Message) {
        messages.add(message)
    }
    /**
     * Send queued messages if online
     * @param m: Messenger object that the send method can be called upon
     */
    override fun sendQueuedMessages(m: Messenger) { // Set up some possibly valid code for now
        if (isOnline()) {
            for (message in messages) {
                Messenger().send(message)
            }
        }
    }
    /**
     * Check if online before sending messages
     * @return Boolean
     */
    override fun isOnline(): Boolean {
        return true
    }
}

// TODO: Split up ADRS to narrow scopes AND split up testing and test cases

