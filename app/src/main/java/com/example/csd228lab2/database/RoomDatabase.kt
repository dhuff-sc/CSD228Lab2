package com.example.csd228lab2.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.csd228lab2.models.Message
import com.example.csd228lab2.models.User

class RoomDatabase {

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
        fun insertConversation(conversation: List<Message>)

        @Delete
        fun deleteConversation(conversation: List<Message>)

        @Update
        fun updateConversation(conversation: List<Message>)

        @Query("SELECT * FROM message WHERE id = :id")
        fun getConversationById(id: Int): List<Message>

        @Query("SELECT * FROM message")
        fun getAllConversations(): List<List<Message>>
    }

}