package com.example.csd228lab2.database

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.Update
import com.example.csd228lab2.models.Conversation
import com.example.csd228lab2.models.Message
import com.example.csd228lab2.models.User

@Database(version = 1, entities = [User::class, Message::class, Conversation::class])
abstract class RoomDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun messageDao(): MessageDao
    abstract fun conversationDao(): ConversationDao

    @Dao
    interface UserDao {
        @Insert
        fun insertUser(user: User)

        @Delete
        fun deleteUser(user: User)

        @Update
        fun updateUser(user: User)

        @Query("SELECT * FROM user WHERE id = :id")
        fun getUserById(id: Int): User

        @Query("SELECT * FROM user")
        fun getAllUsers(): List<User>

    }

    @Dao
    interface MessageDao {
        @Insert
        fun insertMessage(message: Message)

        @Delete
        fun deleteMessage(message: Message)

        @Update
        fun updateMessage(message: Message)

        @Query("SELECT * FROM message WHERE id = :id")
        fun getMessageById(id: Int): Message

        @Query("SELECT * FROM message")
        fun getAllMessages(): List<Message>
    }

    @Dao
    interface ConversationDao {
        @Insert
        fun insertConversation(conversation: Conversation)

        @Delete
        fun deleteConversation(conversation: Conversation)

        @Update
        fun updateConversation(conversation: Conversation)

        @Query("SELECT * FROM message WHERE id = :id")
        fun getConversationById(id: Int): Conversation

        @Query("SELECT * FROM message")
        fun getAllConversations(): List<Conversation>
    }

}