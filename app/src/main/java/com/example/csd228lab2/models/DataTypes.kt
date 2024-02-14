package com.example.csd228lab2.models

import java.sql.Timestamp

/**
 * Data Types for Messenger
 * author: Daniel Huff, @dhuff-sc
 * date: 2024-02-14
 */

// 1. Store Basic User Data
data class User(
    val id: Int? = null,
    val userName: String,
    val email: String,
    val avatar: Char? = null,
)

// 5. When message was sent, Timestamp**
open class Message(
    private val id: Int,
    private val mixMedia: List<List<Int>>? = emptyList(),
    private val text: String? = null,
    private val status: MessageStatus? = null,
    private val sender: User? = null,
    private val timestamp: Timestamp = Timestamp(System.currentTimeMillis())
)
data class TextMessage(
    val id:Int,
    val text:String?,
    val mixMedia: List<List<Int>>? = null,
    val timestamp: Timestamp,
    val status: MessageStatus,
    val sender: User? = null)
    : Message(id = id, text = text, timestamp = timestamp, status = status, sender = sender)
data class MediaMessage(
    val id:Int,
    val text:String? = null,
    val mixMedia: List<List<Int>>,
    val timestamp: Timestamp,
    val status: MessageStatus,
    val sender: User? = null)
    : Message(id = id, mixMedia = mixMedia, timestamp = timestamp, status = status, sender = sender)
// 9. Send mixed media
data class MixMedia(
    val id:Int,
    val text: String,
    val mixMedia: List<List<Int>>,
    val timestamp: Timestamp,
    val status: MessageStatus,
    val sender: User? = null)
    : Message(id = id, mixMedia = mixMedia, text = text, timestamp = timestamp, status = status, sender = sender)

// 5. See when sent
enum class MessageStatus(val value: String) {
    SENT("sent"),
    DELIVERED("delivered"),
    FAILED("failed to send")
}

// 3. History of conversations
// 4. Single person or group chat
// 6. Toggle read receipts
data class Conversation(
    val users: List<User>,
    val messages: List<Message>,
    val readReceipts: Boolean = true
)

// 2. Send messages while offline?
// Discuss Repository? Post messages while offline?
// 8. Delete messages from a conversation?

