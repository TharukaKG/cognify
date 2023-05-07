package com.tharuka.cognify.result.data.local

import com.tharuka.cognify.core.data.local.realm_object.ChatItem
import com.tharuka.cognify.result.domain.error.ResultError
import com.tharuka.cognify.result.domain.error.ResultException
import io.realm.kotlin.Realm
import io.realm.kotlin.query.find

import io.realm.kotlin.types.RealmUUID

class ResultDataSourceImpl(
    private val realm:Realm
): ResultDataSource {
    override suspend fun getChatById(chatId: RealmUUID): ChatItem {
        return try {
            realm.query(ChatItem::class, "id == $0", chatId).find().first()
        } catch (exception: Exception) {
            exception.printStackTrace()
            throw ResultException(ResultError.CHAT_NOT_FOUND)
        }
    }

}