package com.tharuka.cognify.history.domain

import com.tharuka.cognify.history.domain.model.History
import io.realm.kotlin.types.RealmList

interface HistoryRepository {
    suspend fun getHistory(): List<History>
}