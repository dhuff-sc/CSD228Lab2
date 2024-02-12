package com.example.csd228lab2.models

import java.sql.Timestamp

// 1. Store Basic User Data
data class User(
    val userName: String,
    val avatar: Char? = null,
)

// 5. When message was sent, Timestamp**
open class Message(
    private val mixMedia: List<List<Int>>? = emptyList(),
    private val text:String? = null,
    private val status: MessageStatus = MessageStatus.sent,
    private val sender: User? = null,
    private val timestamp: Timestamp = Timestamp(System.currentTimeMillis()) // Java Utils Timestamp, discuss
)
data class TextMessage(
    val text:String
)
    : Message(mixMedia = null, text = text)
data class MediaMessage(
    val media: List<List<Int>> = emptyList()
)
    : Message(mixMedia = media)
// 9. Send mixed media
data class MixMedia(
    val text: String,
    val media: List<List<Int>>
)
    : Message(mixMedia = media, text = text)

// 5. See when sent
enum class MessageStatus(val value: String) {
    sent("sent"),
    delivered("delivered"),
    read("read"),
    failed("failed to send")

// 3. History of conversations
// 4. Single person or group chat
// 6. Toggle read receipts
data class Conversation(
    val users: List<User>,
    val messages: List<Message>,
    val startDate: Timestamp = Timestamp(System.currentTimeMillis()), // This too
    val readReceipts: Boolean = true
)

}

// 2. Send messages while offline?
// Discuss Repository? Post messages while offline?
// 8. Delete messages from a conversation?

