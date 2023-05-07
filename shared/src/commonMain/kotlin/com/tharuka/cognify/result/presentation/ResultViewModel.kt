package com.tharuka.cognify.result.presentation

import com.tharuka.cognify.core.domain.util.Resource
import com.tharuka.cognify.result.domain.error.ResultException
import com.tharuka.cognify.result.domain.use_case.GetResultsUseCase
import io.realm.kotlin.types.RealmUUID
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ResultViewModel(
    private val getResultsUseCase: GetResultsUseCase,
    private val chatId:String?,
    private val continuable: Boolean? = false,
    private val coroutineScope: CoroutineScope?
) {

    private val _state = MutableStateFlow(ResultState())
    val state:StateFlow<ResultState> get() = _state

    init {
        _state.update {
            it.copy(
                chatId = chatId,
                continuable = continuable?:false
            )
        }
        loadResults(state.value)
    }

    private fun loadResults(state: ResultState) = coroutineScope?.launch {

        if(state.chatId==null) return@launch

        val result = getResultsUseCase(chatId = RealmUUID.from(state.chatId))

        _state.update { it.copy(loading = true) }

        when(result){
            is Resource.Error -> {
                _state.update {
                    it.copy(
                        loading = false,
                        error = (result.throwable as? ResultException)?.error
                    )
                }
            }
            is Resource.Success -> {
                _state.update {
                    it.copy(
                        chatId = state.chatId,
                        loading = false,
                        results = result.data?.conversations?.map { conversation->
                            ResultUiItem(
                                id = conversation.id.toString(),
                                answer = conversation.answer?:"",
                                question = conversation.question?:""
                            )
                        }?: emptyList()
                    )
                }
            }
        }
    }

}