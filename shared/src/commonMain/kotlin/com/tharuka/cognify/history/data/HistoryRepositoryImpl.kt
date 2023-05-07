package com.tharuka.cognify.history.data

import com.tharuka.cognify.history.data.local.HistoryDataSource
import com.tharuka.cognify.history.domain.HistoryRepository
import com.tharuka.cognify.history.domain.model.History

class HistoryRepositoryImpl(
    private val historyDataSource: HistoryDataSource
): HistoryRepository {
    override suspend fun getHistory(): List<History> {
        return historyDataSource.getHistory()
    }
}