package com.tharuka.cognify.history.presentation

import com.tharuka.cognify.history.domain.error.HistoryError

data class HistoryState(
    val historyItems:List<HistoryUiItem> = emptyList(),
    val loading:Boolean = false,
    val error:HistoryError? = null
)