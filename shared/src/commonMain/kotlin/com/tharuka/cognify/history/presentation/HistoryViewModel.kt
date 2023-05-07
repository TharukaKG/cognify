package com.tharuka.cognify.history.presentation

import com.tharuka.cognify.core.domain.util.Resource
import com.tharuka.cognify.history.domain.error.HistoryException
import com.tharuka.cognify.history.domain.use_case.GetHistoryUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HistoryViewModel(
    private val getHistoryUseCase: GetHistoryUseCase,
    coroutineScope: CoroutineScope?
){

    private val viewModelScope:CoroutineScope = coroutineScope?: CoroutineScope(Dispatchers.Main)

    private val _state:MutableStateFlow<HistoryState> = MutableStateFlow(HistoryState())
    val state get() = _state

    init {
        getHistory()
    }

    private fun getHistory() = viewModelScope.launch {

        val result = getHistoryUseCase()

        _state.update {
            it.copy(loading = true)
        }

        when(result){
            is Resource.Error -> {
                _state.update {
                    it.copy(
                        loading = false,
                        error = (result.throwable as? HistoryException)?.error
                    )
                }
            }
            is Resource.Success -> {
                _state.update {
                    it.copy(
                        loading = false,
                        historyItems = result.data?.map{history ->
                            HistoryUiItem(
                                id = history.chatId,
                                text = history.text
                            )
                        }?: emptyList()
                    )
                }
            }
        }

    }

}