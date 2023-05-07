package com.tharuka.cognify.chat.data.remote

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class QuestionDto(
    @SerialName("messages")val messages: List<Message>
)

@kotlinx.serialization.Serializable
data class Message(
    @SerialName("role") val role: String = "user",
    @SerialName("content") val question:String
)