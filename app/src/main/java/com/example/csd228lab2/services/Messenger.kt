package com.example.csd228lab2.services

open class Message(private val mixMedia: List<List<Int>>? = emptyList(), private val text:String? = null) {}

data class TextMessage(val text:String):Message(mixMedia = null, text = text)
data class MediaMessage(val media: List<List<Int>> = emptyList()): Message(mixMedia = media)
data class MixMedia(val text: String, val media: List<List<Int>>): Message(mixMedia = media, text = text)

enum class MessageStatus private constructor(val value: String) {
    sent("sent"),
    delivered("delivered"),
    read("read"),
    failed("failed to send")
}

interface Messager {
    fun send(message: Message): Message
    fun refresh()
}

class Messenger: Messager {
    override fun send(message:Message): Message {
        return Message()
    }

    override fun refresh() {}
}