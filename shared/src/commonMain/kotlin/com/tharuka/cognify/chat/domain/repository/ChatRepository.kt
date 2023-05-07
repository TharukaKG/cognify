package com.tharuka.cognify.chat.domain.repository

import io.realm.kotlin.types.RealmUUID

interface ChatRepository {

    suspend fun chat(question: String): String

    suspend fun insertChatItem(answer:String, question: String): RealmUUID

    suspend fun updateChatItem(id: RealmUUID, answer:String, question: String): Boolean?

    suspend fun getLastChatId(): RealmUUID?

}