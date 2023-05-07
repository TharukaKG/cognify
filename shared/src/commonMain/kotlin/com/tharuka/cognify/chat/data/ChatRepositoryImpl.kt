package com.tharuka.cognify.chat.data

import com.tharuka.cognify.chat.data.local.ChatDataSource
import com.tharuka.cognify.chat.data.remote.ChatClient
import com.tharuka.cognify.chat.domain.repository.ChatRepository
import io.realm.kotlin.types.RealmUUID

class ChatRepositoryImpl(
    private val chatDataSource: ChatDataSource,
    private val chatClient: ChatClient
): ChatRepository {

    override suspend fun chat(question: String): String {
        return chatClient.chat(question = question)
    }

    override suspend fun insertChatItem(answer:String, question: String): RealmUUID {
        return chatDataSource.createNewChat(answer = answer, question = question)
    }

    override suspend fun updateChatItem(id:RealmUUID, answer:String, question: String): Boolean? {
        return chatDataSource.updateChat(id = id, answer = answer, question = question)
    }

    override suspend fun getLastChatId(): RealmUUID? {
        return chatDataSource.getLastChatId()
    }
}