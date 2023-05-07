package com.tharuka.cognify.result.presentation

sealed class ResultEvent{
    object OnClose: ResultEvent()
    data class OnContinue(val chatId:String?): ResultEvent()
}
