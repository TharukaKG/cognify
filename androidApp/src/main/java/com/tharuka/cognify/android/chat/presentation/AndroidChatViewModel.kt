package com.tharuka.cognify.android.chat.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tharuka.cognify.android.core.presentatiton.navigation.Args
import com.tharuka.cognify.chat.domain.use_case.ChatUseCase
import com.tharuka.cognify.chat.domain.use_case.GetLastChatIdUseCase
import com.tharuka.cognify.chat.domain.VoiceToTextParser
import com.tharuka.cognify.chat.presentation.ChatEvent
import com.tharuka.cognify.chat.presentation.ChatViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AndroidChatViewModel @Inject constructor(
    private val chatUseCase: ChatUseCase,
    private val voiceToTextParser: VoiceToTextParser,
    private val getLastChatIdUseCase: GetLastChatIdUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val viewModel by lazy {
        val chatId = savedStateHandle.get<String>(Args.chatId)
        ChatViewModel(
            chatUseCase = chatUseCase,
            parser = voiceToTextParser,
            getLastChatIdUseCase = getLastChatIdUseCase,
            chatId = chatId,
            coroutineScope = viewModelScope
        )
    }

    val state = viewModel.state

    fun onEvent(chatEvent: ChatEvent) {
        viewModel.onChatEvent(chatEvent)
    }

}