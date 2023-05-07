package com.tharuka.cognify.chat.data.remote

@kotlinx.serialization.Serializable
data class AnswerDto(
    val content: String,
    val message: String
)
