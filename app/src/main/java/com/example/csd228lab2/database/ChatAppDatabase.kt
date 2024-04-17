package com.example.csd228lab2.database

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import androidx.room.Update
import com.example.csd228lab2.models.Conversation
import com.example.csd228lab2.models.Message
import com.example.csd228lab2.models.User
import java.sql.Timestamp

/*
* Database class that represents the chat app database
* Contains tables for users, messages, and conversations
* Implements Daos for each table
 */
@TypeConverters(Converters::class)
@Database(version = 1, entities = [User::class, Message::class, Conversation::class])
abstract class ChatAppDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun messageDao(): MessageDao
    abstract fun conversationDao(): ConversationDao
}

/*
* Dao interface for the user table
* Contains functions to insert, delete, update, and query users
 */
@Dao
interface UserDao {
    @Insert
    fun insertUser(user: User)

    @Delete
    fun deleteUser(user: User)

    @Update
    fun updateUser(user: User)

    @Query("SELECT * FROM user WHERE :id LIKE id")
    fun getUserById(id: Int): User

    @Query("SELECT * FROM user")
    fun getAllUsers(): List<User>

}

/*
* Dao interface for the message table
* Contains functions to insert, delete, update, and query messages
 */
@Dao
interface MessageDao {
    @Insert
    fun insertMessage(message: Message)

    @Delete
    fun deleteMessage(message: Message)

    @Update
    fun updateMessage(message: Message)

    @Query("SELECT * FROM message WHERE :id LIKE id")
    fun getMessageById(id: Int): Message

    @Query("SELECT * FROM message")
    fun getAllMessages(): List<Message>
}

/*
* Dao interface for the conversation table
* Contains functions to insert, delete, update, and query conversations
 */
@Dao
interface ConversationDao {
    @Insert
    fun insertConversation(conversation: Conversation)

    @Delete
    fun deleteConversation(conversation: Conversation)

    @Update
    fun updateConversation(conversation: Conversation)

    @Query("SELECT * FROM conversation WHERE :id LIKE id")
    fun getConversationById(id: Int): Conversation?

    @Query("SELECT * FROM conversation")
    fun getAllConversations(): List<Conversation>
}

class Converters {
    private val _sep = "_&&_"
    private val _divider = "`"
    @TypeConverter
    fun toBinaryContent(data: List<List<Int>>?): String {
        if (data == null) return "null"
        return "[${data.joinToString(_sep) { 
            it.joinToString(_divider)
        }}]"
    }

    @TypeConverter
    fun fromBinaryContent(data: String): List<List<Int>>? {
        if (data == "null") return null
        return data.replace("[","").replace("]","").split(_sep).map {
            it.split(_divider).map { bit -> bit.toInt() }
        }
    }

    @TypeConverter
    fun fromTimestamp(ts: Timestamp): Long = ts.time
    @TypeConverter
    fun toTimestamp(time: Long): Timestamp {
        return Timestamp(time)
    }

    @TypeConverter
    fun toNullableUser(data: String):User? {
        if (data == "null") return null

        val parts = data.split(_sep)
        val avatar = if (parts.size > 3) parts[3].toCharArray()[0] else null
        return User(id = parts[0].toInt(), userName = parts[1], email = parts[2], avatar = avatar)
    }

    @TypeConverter
    fun fromNullableUser(data: User?): String {
        if (data == null) return "null"

        return "${data.id}$_sep${data.userName}$_sep${data.email}${if (data.avatar != null) "$_sep${data.avatar}" else ""}"
    }

    // This could be completely dropped if the Conversation class used List<String>, and message used a string for user
    @TypeConverter
    fun toUserList(data: String): List<User> {
        if (data.isBlank() || data.isEmpty() || data == "[]") return emptyList()
        return data.replace("[","").replace("]","").split(_sep).map {
            val parts = it.split(_divider)
            val avatar = if (parts.size > 3) parts[3].toCharArray()[0] else null
            User(id = parts[0].toInt(), userName = parts[1], email = parts[2], avatar = avatar)
        }
    }

    @TypeConverter
    fun fromUserList(data: List<User>): String {
        return "[${data.joinToString(_sep) { "${it.id}$_divider${it.userName}$_divider${it.email}${if (it.avatar != null) "$_divider${it.avatar}" else ""}" }}]"
    }

    @TypeConverter
    fun fromMessageList(data: List<Message>): String {
        return "[${data.joinToString(_sep) { 
            "${it.text}$_divider${if (it.sender != null) "${it.sender!!.id}$_divider${it.sender!!.userName}$_divider${it.sender!!.email}${if (it.sender!!.avatar != null) "$_divider${it.sender!!.avatar}" else ""}" else "null"}"
        }}]"
    }

    @TypeConverter
    fun toMessageList(data: String):List<Message> {
        if (data.isBlank() || data.isEmpty() || data == "[]") return emptyList()
        return data.replace("[","").replace("]","").split(_sep).map {
            val parts = it.split(_divider)
            val avatar = if (parts.size > 4) parts[4].toCharArray()[0] else null
            val user = User(id = parts[1].toInt(), userName = parts[2], email = parts[3], avatar = avatar)
            Message(text = parts[0], sender = user)
        }
    }
}