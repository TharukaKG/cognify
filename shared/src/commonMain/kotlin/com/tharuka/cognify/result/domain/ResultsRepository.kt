package com.tharuka.cognify.result.domain

import com.tharuka.cognify.core.data.local.realm_object.ChatItem
import io.realm.kotlin.types.RealmUUID

interface ResultsRepository {
    suspend fun getResults(chatId:RealmUUID):ChatItem
}