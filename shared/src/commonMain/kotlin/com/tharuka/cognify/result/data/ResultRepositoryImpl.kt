package com.tharuka.cognify.result.data

import com.tharuka.cognify.core.data.local.realm_object.ChatItem
import com.tharuka.cognify.result.data.local.ResultDataSource
import com.tharuka.cognify.result.domain.ResultsRepository
import io.realm.kotlin.types.RealmUUID

class ResultRepositoryImpl(
    private val resultDataSource: ResultDataSource
):ResultsRepository {

    override suspend fun getResults(chatId: RealmUUID): ChatItem {
        return resultDataSource.getChatById(chatId)
    }
}