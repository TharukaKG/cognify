package com.tharuka.cognify.chat.presentation

import com.tharuka.cognify.chat.domain.error.ChatError
import io.realm.kotlin.types.RealmUUID

data class ChatState(
    val chatId: RealmUUID? = null,
    val error: ChatError? = null,
    val question: String = "",
    val answer: String? = null,
    val canRecord:Boolean = true,
    val generatingStatus: GeneratingStatus = GeneratingStatus.IDLE,
    val voiceToTextState:VoiceToTextStatus = VoiceToTextStatus.IDLE,
)

enum class GeneratingStatus {
    IDLE,
    GENERATING,
    GENERATED
}

enum class VoiceToTextStatus {
    IDLE,
    ERROR,
    DISPLAYING_RESULTS,
    SPEAKING
}
