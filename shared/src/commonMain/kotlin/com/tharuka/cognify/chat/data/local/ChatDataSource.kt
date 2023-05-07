package com.tharuka.cognify.chat.data.local

import io.realm.kotlin.types.RealmUUID

interface ChatDataSource {

    suspend fun createNewChat(answer:String, question:String): RealmUUID

    suspend fun updateChat(id:RealmUUID, answer:String, question: String): Boolean?

    suspend fun getLastChatId(): RealmUUID?

}