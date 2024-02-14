import com.example.csd228lab2.models.*
import org.junit.Test
import java.sql.Timestamp
import com.example.csd228lab2.models.User
import org.junit.Assert.*

class DataTypesUnitTest {

    // Create basic user data and assert they are individual user instances
    // Creation of id is optional for the moment, but good to have to differentiate users
    @Test
    fun testUserData() {
        val user = User(id = 1, userName = "Daniel", email = "daniel@sc.ca", avatar = 'D')
        val user2 = User(id = 2, userName = "Ryan", email = "ryan@sc.ca", avatar = 'R')
        val user3 = User(userName = "Daniel", email = "daniel@sc.ca", avatar = 'D')
        val user4 = user2.copy()
        assertEquals("Daniel", user.userName)
        assertEquals("daniel@sc.ca", user.email)
        assertEquals('D', user.avatar)
        assertEquals(1, user.id)
        assertNotEquals(user, user2)
        assertNotEquals(user, user3)
        assertEquals(user2, user4)
        assertNull(user3.id)

    }

    // Message status enum is working correctly, String value can be accessed
    @Test
    fun testMessageStatus() {
        assertEquals("sent", MessageStatus.SENT.value)
        assertEquals("delivered", MessageStatus.DELIVERED.value)
        assertEquals("failed to send", MessageStatus.FAILED.value)
    }

    // Testing Message subclasses, nulls where appropriate, timestamps and status are showing correctly
    @Test
    fun testMessageSubclasses() {
        val testTime = Timestamp(System.currentTimeMillis())

        val textMessage = TextMessage(id = 1, text = "This is a text-only message!", status = MessageStatus.SENT, timestamp = Timestamp(System.currentTimeMillis()))
        assertEquals(1, textMessage.id)
        assertEquals("This is a text-only message!", textMessage.text)
        assertEquals(null, textMessage.mixMedia)
        assertEquals(testTime.time / 1000, textMessage.timestamp.time / 1000)
        assertEquals(MessageStatus.SENT, textMessage.status)

        val mediaMessage = MediaMessage(id = 2, mixMedia = listOf(listOf(1, 2), listOf(3, 4)), status = MessageStatus.FAILED, timestamp = Timestamp(System.currentTimeMillis()))
        assertEquals(2, mediaMessage.id)
        assertEquals(null, mediaMessage.text)
        assertEquals(listOf(listOf(1, 2), listOf(3, 4)), mediaMessage.mixMedia)
        assertEquals(testTime.time / 1000, mediaMessage.timestamp.time / 1000)
        assertEquals(MessageStatus.FAILED, mediaMessage.status)

        val mixMedia = MixMedia(id = 3, text = "This is a mixed media message!", mixMedia = listOf(listOf(5, 6)), status = MessageStatus.DELIVERED, timestamp = Timestamp(System.currentTimeMillis()))
        assertEquals(3, mixMedia.id)
        assertEquals("This is a mixed media message!", mixMedia.text)
        assertEquals(listOf(listOf(5, 6)), mixMedia.mixMedia)
        assertEquals(testTime.time / 1000, mixMedia.timestamp.time / 1000)
        assertEquals(MessageStatus.DELIVERED, mixMedia.status)
    }

    // Test Conversation data class, users and messages are stored correctly, read receipt is true by default
    @Test
    fun testConversation() {
        val user1 = User(userName = "User1", email = "user1@sc.ca")
        val user2 = User(userName = "User2", email = "user2@sc.ca")

        val message1 = TextMessage(id = 1, text = "Hello, User2", sender = user1, status = MessageStatus.DELIVERED, timestamp = Timestamp(System.currentTimeMillis()))
        val message2 = TextMessage(id = 2, text = "How are you?", sender = user2, status = MessageStatus.DELIVERED, timestamp = Timestamp(System.currentTimeMillis()))
        val message3 = TextMessage(id = 3, text = "someone set up us the bomb!", sender = user1, status = MessageStatus.FAILED, timestamp = Timestamp(System.currentTimeMillis()))
        val message4 = TextMessage(id = 4, text = "I'm good, thanks!", sender = user1, status = MessageStatus.DELIVERED, timestamp = Timestamp(System.currentTimeMillis()))
        val message5 = TextMessage(id = 5, text = "I didn't think you got my message for a sec lol", sender = user2, status = MessageStatus.SENT, timestamp = Timestamp(System.currentTimeMillis()))
        val message6 = TextMessage(id = 6, text = null, sender = user2, status = MessageStatus.SENT, timestamp = Timestamp(System.currentTimeMillis()))
        val message7 = TextMessage(id = 7, text = "", sender = user1, status = MessageStatus.DELIVERED, timestamp = Timestamp(System.currentTimeMillis()))
        val messages = listOf(message1, message2, message3, message4, message5, message6)

        val conversation = Conversation(users = listOf(user1, user2), messages = messages, readReceipts = true)
        assertEquals(listOf(user1, user2), conversation.users)
        assertEquals(messages, conversation.messages)
        assertEquals(true, conversation.readReceipts)
        assertEquals(6, conversation.messages.size)
        assertNotEquals(message3, message4)
        assertEquals(message3, conversation.messages[2])
        assertEquals(message3.status, MessageStatus.FAILED)
        assertEquals(message5.status, MessageStatus.SENT)
        assertNull(message6.text)
        assertEquals("", message7.text)

    }
}