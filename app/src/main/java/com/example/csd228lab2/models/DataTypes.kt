/**
 * Data Types for Messenger
 * @author: Daniel Huff, @dhuff-sc
 * date: 2024-02-14
 */

package com.example.csd228lab2.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp

/**
 * Data class that represents a user
 *
 * Contains id, userName, email, and avatar
 * Meets the requirement for user data
 *
 * User entity for the Room database
 */
@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "user_name") val userName: String,
    val email: String,
    val avatar: Char? = null,
)

/**
 * Open data class that represents a message
 * Provides three subclasses for different message types
 * Text-only, media-only, and mixed media
 *
 * Contains id, mixMedia, text, status, sender, and timestamp
 * Meets the requirement for message status, message send time, and message sender
 *
 * Message entity for the Room database
 */
@Entity(tableName = "message")
open class Message(
    @PrimaryKey(autoGenerate = true) open val id: Int = 0,
    @ColumnInfo(name = "mix_media") open val mixMedia: List<List<Int>>? = emptyList(),
    open val text: String? = null,
    open val status: MessageStatus? = null,
    open val sender: User? = null,
    open val timestamp: Timestamp = Timestamp(System.currentTimeMillis())
)
data class TextMessage(
    override val id:Int,
    override val text:String,
    override val mixMedia: List<List<Int>>? = null,
    override val timestamp: Timestamp,
    override val status: MessageStatus,
    override val sender: User? = null)
    : Message(id = id, text = text, timestamp = timestamp, status = status, sender = sender)
data class MediaMessage(
    override val id:Int,
    override val text:String? = null,
    override val mixMedia: List<List<Int>>? = null,
    override val timestamp: Timestamp,
    override val status: MessageStatus,
    override val sender: User? = null)
    : Message(id = id, mixMedia = mixMedia, timestamp = timestamp, status = status, sender = sender)
data class MixMedia(
    override val id:Int,
    override val text:String?,
    override val mixMedia: List<List<Int>>,
    override val timestamp: Timestamp,
    override val status: MessageStatus,
    override val sender: User? = null)
    : Message(id = id, mixMedia = mixMedia, text = text, timestamp = timestamp, status = status, sender = sender)

/**
 * Enum class that represents the status of a message
 *
 * Contains SENT, DELIVERED, and FAILED
 * Meets the requirement for message status
 */
enum class MessageStatus(val value: String) {
    SENT("sent"),
    DELIVERED("delivered"),
    FAILED("failed to send")
}
/**
 * Data class that represents a conversation between users
 *
 * Contains list of users, list of messages, and read receipts Boolean
 * Meets the requirement for view conversation
 * Meets the requirement for toggle read receipts
 * Meets the requirement for single or multi-user conversation
 *
 * Conversation entity for the Room database
 */
@Entity(tableName = "conversation")
data class Conversation(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "conversation_name") val convoName: String?,
    val users: List<User>,
    val messages: List<Message>,
    @ColumnInfo(name = "read_receipts") val readReceipts: Boolean = true
)

