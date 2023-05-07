package com.tharuka.cognify.android.result.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tharuka.cognify.android.core.presentatiton.navigation.Args
import com.tharuka.cognify.result.domain.use_case.GetResultsUseCase
import com.tharuka.cognify.result.presentation.ResultEvent
import com.tharuka.cognify.result.presentation.ResultViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AndroidResultViewModel @Inject constructor(
    private val getResultsUseCase: GetResultsUseCase,
    private val savedStateHandle: SavedStateHandle,
): ViewModel() {

    private val viewModel by lazy {
        ResultViewModel(
            getResultsUseCase = getResultsUseCase,
            chatId = savedStateHandle.get<String>(key = Args.chatId),
            continuable = savedStateHandle.get<Boolean>(key = Args.continuable),
            viewModelScope
        )
    }

    val state = viewModel.state

}