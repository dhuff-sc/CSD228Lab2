import com.example.csd228lab2.models.*
import org.junit.Test
import java.sql.Timestamp
import com.example.csd228lab2.models.User
import org.junit.Assert.*
import org.junit.Before

class DataTypesUnitTest {
    private lateinit var user: User
    private lateinit var user2: User
    private lateinit var user3: User
    private lateinit var user4: User
    private lateinit var testTime: Timestamp
    private lateinit var textMessage: TextMessage
    private lateinit var mediaMessage: MediaMessage
    private lateinit var mixMedia: MixMedia
    private lateinit var message1: TextMessage
    private lateinit var message2: TextMessage
    private lateinit var message3: TextMessage
    private lateinit var message4: TextMessage
    private lateinit var message5: TextMessage
    private lateinit var message6: TextMessage
    private lateinit var message7: TextMessage
    private lateinit var message8: MediaMessage
    private lateinit var message9: MixMedia
    private lateinit var conversation: Conversation
    private lateinit var messages: List<Message>

    @Before
    fun setUp() {
        user = User(id = 1, userName = "Daniel", email = "daniel@sc.ca", avatar = 'D')
        user2 = User(id = 2, userName = "Ryan", email = "ryan@sc.ca", avatar = 'R')
        user3 = User(userName = "Daniel", email = "daniel@sc.ca", avatar = 'D')
        user4 = user2.copy()
    }

    @Test
    fun testCreateUserData() {
        assertEquals("Daniel", user.userName)
        assertEquals("daniel@sc.ca", user.email)
        assertEquals('D', user.avatar)
        assertEquals(1, user.id)
    }

    @Test
    fun testUserEquality() {
        assertEquals(user, user3)
        assertEquals(user2, user4)
        assertNotEquals(user, user2)
        assertNotEquals(user3, user4)
    }

    @Test
    fun testUserToString() {
        assertEquals("User(id=1, userName=Daniel, email=daniel@sc.ca, avatar=D)", user.toString())
    }

    // Message status enum is working correctly, String value can be accessed
    @Test
    fun testMessageStatus() {
        assertEquals("sent", MessageStatus.SENT.value)
        assertEquals("delivered", MessageStatus.DELIVERED.value)
        assertEquals("failed to send", MessageStatus.FAILED.value)
    }

//    @Before
//    fun setUpMessage() {
//        testTime = Timestamp(System.currentTimeMillis())
//        textMessage = TextMessage(
//            id = 1,
//            text = "This is a text-only message!",
//            status = MessageStatus.SENT,
//            timestamp = Timestamp(System.currentTimeMillis()))
//        mediaMessage = MediaMessage(id = 2,
//            mixMedia = listOf(listOf(1, 2), listOf(3, 4)),
//            status = MessageStatus.FAILED,
////            timestamp = Timestamp(System.currentTimeMillis()))
////        mixMedia = MixMedia(
////            id = 3,
////            text = "This is a mixed media message!",
////            mixMedia = listOf(listOf(5, 6)),
////            status = MessageStatus.DELIVERED,
////            timestamp = Timestamp(System.currentTimeMillis()))
//    }
    // Testing Message subclasses, nulls where appropriate, timestamps and status are showing correctly
    @Test
    fun testCreateTextMessage() {

        assertEquals(1, textMessage.id)
        assertEquals("This is a text-only message!", textMessage.text)
        assertEquals(testTime.time / 1000, textMessage.timestamp.time / 1000)
        assertEquals(MessageStatus.SENT, textMessage.status)
    }

    @Test
    fun testCreateMediaMessage() {
        assertEquals(2, mediaMessage.id)
        assertEquals(listOf(listOf(1, 2), listOf(3, 4)), mediaMessage.mixMedia)
        assertEquals(testTime.time / 1000, mediaMessage.timestamp.time / 1000)
        assertEquals(MessageStatus.FAILED, mediaMessage.status)
    }

    @Test
    fun testCreateMixMedia() {
        assertEquals(3, mixMedia.id)
        assertEquals("This is a mixed media message!", mixMedia.text)
        assertEquals(listOf(listOf(5, 6)), mixMedia.mixMedia)
        assertEquals(testTime.time / 1000, mixMedia.timestamp.time / 1000)
        assertEquals(MessageStatus.DELIVERED, mixMedia.status)
    }

    @Test
    fun testMessageTypesSupportNulls() {
        assertNull(textMessage.mixMedia)
        assertNull(mediaMessage.text)
    }

    @Test
    fun testMessageToString() {
        assertEquals("TextMessage(id=1, text=This is a text-only message!, mixMedia=null, timestamp=${testTime}, status=SENT, sender=null)", textMessage.toString())
        assertEquals("MediaMessage(id=2, text=null, mixMedia=[[1, 2], [3, 4]], timestamp=${testTime}, status=FAILED, sender=null)", mediaMessage.toString())
        assertEquals("MixMedia(id=3, text=This is a mixed media message!, mixMedia=[[5, 6]], timestamp=${testTime}, status=DELIVERED, sender=null)", mixMedia.toString())
    }

    // Test Conversation data class, users and messages are stored correctly, read receipt is true by default

    @Before
    fun setUpConversation() {
        user = User(id = 1, userName = "user1", email = "user1@sc.ca", avatar = 'U')
        user2 = User(id = 2, userName = "user2", email = "user2@sc.ca", avatar = 'U')
        message1 = TextMessage(id = 1, text = "Hello, User2", sender = user, status = MessageStatus.DELIVERED, timestamp = Timestamp(System.currentTimeMillis()))
        message2 = TextMessage(id = 2, text = "How are you?", sender = user2, status = MessageStatus.DELIVERED, timestamp = Timestamp(System.currentTimeMillis()))
        message3 = TextMessage(id = 3, text = "someone set up us the bomb!", sender = user, status = MessageStatus.FAILED, timestamp = Timestamp(System.currentTimeMillis()))
        message4 = TextMessage(id = 4, text = "I'm good, thanks!", sender = user, status = MessageStatus.DELIVERED, timestamp = Timestamp(System.currentTimeMillis()))
        message5 = TextMessage(id = 5, text = "I didn't think you got my message for a sec lol", sender = user2, status = MessageStatus.SENT, timestamp = Timestamp(System.currentTimeMillis()))
        message6 = TextMessage(id = 6, text = null, sender = user2, status = MessageStatus.SENT, timestamp = Timestamp(System.currentTimeMillis()))
        message7 = TextMessage(id = 7, text = "", sender = user, status = MessageStatus.DELIVERED, timestamp = Timestamp(System.currentTimeMillis()))
//        message8 = MediaMessage(id = 8, mixMedia = listOf(listOf(1, 2), listOf(3, 4)), sender = user, status = MessageStatus.SENT, timestamp = Timestamp(System.currentTimeMillis()))
        message9 = MixMedia(id = 9, text = "This is a mixed media message!", mixMedia = listOf(listOf(5, 6)), sender = user2, status = MessageStatus.DELIVERED, timestamp = Timestamp(System.currentTimeMillis()))
        messages = listOf(message1, message2, message3, message4, message5, message6, message7, message8, message9)
//        conversation = Conversation( id = 1, users = listOf(user, user2), messages = messages, readReceipts = true)
    }
    @Test
    fun testCreateConversationWithUsers() {
        assertEquals(user, conversation.users[0])
        assertEquals(listOf(user, user2), conversation.users)
    }

    @Test
    fun testConversationContainsMessages() {
        assertEquals(messages, conversation.messages)
        assertEquals(9, conversation.messages.size)
        assertNotEquals(message3, message4)
        assertEquals(message3, conversation.messages[2])
        assertEquals(message3.status, MessageStatus.FAILED)
        assertEquals(message5.status, MessageStatus.SENT)
        assertEquals("", message7.text)
    }

    @Test
    fun testConversationReadReceipts() {
        assertEquals(true, conversation.readReceipts)
    }
}