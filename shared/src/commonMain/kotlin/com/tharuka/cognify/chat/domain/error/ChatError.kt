package com.tharuka.cognify.chat.domain.error


enum class ChatError(val error:String?) {
    CLIENT_ERROR("Client error"),
    SERVICE_UNAVAILABLE("Service unavailable"),
    SERVER_ERROR("Server error"),
    LAST_CHAT_NOT_FOUND("Last chat not found"),
    UNKNOWN_ERROR("Unknown error"),
    VOICE_TO_TEXT_ERROR("Cannot recognize voice"),
    NO_PERMISSION("Can't Record without Permission")
}

class ChatException(val error: ChatError): Exception(error.name)