package com.tharuka.cognify.chat.presentation

sealed class ChatEvent {
    data class OnTypeQuestion(val question:String): ChatEvent()
    object Go: ChatEvent()
    object GotoHistory: ChatEvent()
    object OnErrorSeen: ChatEvent()
    object CloseChatResult: ChatEvent()
    object GoToProfile: ChatEvent()
    data class PermissionResult(val isGRanted:Boolean, val isPermanentlyDeclined:Boolean): ChatEvent()
    object ToggleRecord: ChatEvent()
    object ClearQuestion: ChatEvent()
}