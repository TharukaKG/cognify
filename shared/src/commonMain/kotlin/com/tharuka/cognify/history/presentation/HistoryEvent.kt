package com.tharuka.cognify.history.presentation

sealed class HistoryEvent{
    data class SelectItem(val chatId:String): HistoryEvent()
    object GotoChat: HistoryEvent()
    object NewChat: HistoryEvent()
}
