package com.tharuka.cognify.result.domain.error

import com.tharuka.cognify.chat.domain.error.ChatError

enum class ResultError {
    CHAT_NOT_FOUND,
    UNKNOWN_ERROR
}

class ResultException(val error: ResultError): Exception("some error occur while loading result ${error.name}")