package com.tharuka.cognify.result.presentation

import com.tharuka.cognify.result.domain.error.ResultError

data class ResultState(
    val chatId:String? = null,
    val continuable: Boolean = false,
    val results: List<ResultUiItem> = emptyList(),
    val loading:Boolean = false,
    val error: ResultError? = null
)
