package com.tharuka.cognify.history.data.local

import com.tharuka.cognify.core.data.local.realm_object.ChatItem
import com.tharuka.cognify.history.domain.error.HistoryError
import com.tharuka.cognify.history.domain.error.HistoryException
import com.tharuka.cognify.history.domain.model.History
import io.realm.kotlin.Realm

class HistoryDataSourceImpl(private val realm: Realm): HistoryDataSource {

    override suspend fun getHistory(): List<History> {
        return try {
            realm.query(ChatItem::class).find().toHistory()
        }catch (exception: Exception){
            exception.printStackTrace()
            throw HistoryException(HistoryError.ITEMS_NOT_FOUND)
        }
    }

}