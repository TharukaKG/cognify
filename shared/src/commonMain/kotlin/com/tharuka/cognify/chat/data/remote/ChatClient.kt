package com.tharuka.cognify.chat.data.remote

interface ChatClient{
    suspend fun chat(question:String): String
}