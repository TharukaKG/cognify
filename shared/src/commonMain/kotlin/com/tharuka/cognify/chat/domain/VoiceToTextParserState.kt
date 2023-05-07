package com.tharuka.cognify.chat.domain

data class VoiceToTextParserState(
    val result:String = "",
    val error:String? = null,
    val isSpeaking:Boolean = false
)