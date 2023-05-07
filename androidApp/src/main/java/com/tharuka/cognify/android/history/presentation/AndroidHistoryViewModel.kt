package com.tharuka.cognify.android.history.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tharuka.cognify.history.domain.use_case.GetHistoryUseCase
import com.tharuka.cognify.history.presentation.HistoryEvent
import com.tharuka.cognify.history.presentation.HistoryViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AndroidHistoryViewModel @Inject constructor(
    private val getHistoryUseCase: GetHistoryUseCase
): ViewModel(){

    private val viewModel by lazy {
        HistoryViewModel(getHistoryUseCase, viewModelScope)
    }

    val state = viewModel.state

}