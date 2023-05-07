package com.tharuka.cognify.history.data.local

import com.tharuka.cognify.history.domain.model.History

interface HistoryDataSource {
    suspend fun getHistory(): List<History>
}