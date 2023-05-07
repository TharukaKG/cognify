package com.tharuka.cognify.history.domain.error


enum class HistoryError {
    ITEMS_NOT_FOUND,
    UNKNOWN_ERROR
}

class HistoryException(val error: HistoryError): Exception("some error occur while getting history items ${error.name}")