package com.tharuka.cognify.chat.domain

import com.tharuka.cognify.core.domain.util.CommonStateFlow

interface VoiceToTextParser {
    val state:CommonStateFlow<VoiceToTextParserState>

    fun startListening()

    fun stopListening()

    fun cancel()

    fun reset()
}