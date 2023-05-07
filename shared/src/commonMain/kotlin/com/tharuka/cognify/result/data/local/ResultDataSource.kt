package com.tharuka.cognify.result.data.local

import com.tharuka.cognify.core.data.local.realm_object.ChatItem
import io.realm.kotlin.types.RealmUUID

interface ResultDataSource {
    suspend fun getChatById(chatId:RealmUUID): ChatItem
}