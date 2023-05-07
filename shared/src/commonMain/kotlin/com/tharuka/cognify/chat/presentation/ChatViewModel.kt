package com.tharuka.cognify.chat.presentation

import com.tharuka.cognify.chat.domain.VoiceToTextParser
import com.tharuka.cognify.chat.domain.error.ChatError
import com.tharuka.cognify.chat.domain.error.ChatException
import com.tharuka.cognify.chat.domain.use_case.ChatUseCase
import com.tharuka.cognify.chat.domain.use_case.GetLastChatIdUseCase
import com.tharuka.cognify.core.domain.util.Resource
import com.tharuka.cognify.core.domain.util.toCommonStateFlow
import io.realm.kotlin.types.RealmUUID
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ChatViewModel(
    private val chatUseCase: ChatUseCase,
    private val getLastChatIdUseCase: GetLastChatIdUseCase,
    private val parser: VoiceToTextParser,
    chatId: String? = null,
    coroutineScope: CoroutineScope?
) {

    private val viewModelScope: CoroutineScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)

    private var chatJob: Job? = null

    private val _state = MutableStateFlow(ChatState())
    val state = _state.toCommonStateFlow()

    private companion object {
        const val newChat: String = "newChat"
    }

    init {
        when (chatId) {
            newChat -> {
                _state.update { it.copy(chatId = null) }
            }
            null -> {
                getLastChatId()
            }
            else -> {
                _state.update { it.copy(chatId = RealmUUID.from(chatId)) }
            }
        }
        // get voice results
        getVoiceResults(state.value)
    }

    fun onChatEvent(event: ChatEvent) {
        when (event) {
            is ChatEvent.OnTypeQuestion -> {
                _state.update {
                    it.copy(
                        question = event.question
                    )
                }
            }
            is ChatEvent.ClearQuestion ->{
                _state.update {
                    it.copy(
                        generatingStatus = GeneratingStatus.IDLE,
                        voiceToTextState = VoiceToTextStatus.IDLE,
                        question = ""
                    )
                }
            }
            is ChatEvent.Go -> {
                chat(state.value)
            }
            is ChatEvent.CloseChatResult -> {
                _state.update {
                    it.copy(
                        generatingStatus = GeneratingStatus.IDLE,
                        voiceToTextState = VoiceToTextStatus.IDLE,
                        question = "",
                        answer = null
                    )
                }
            }
            is ChatEvent.PermissionResult -> {
                _state.update {
                    it.copy(
                        canRecord = event.isGRanted
                    )
                }
            }
            is ChatEvent.ToggleRecord -> {
                toggleRecord(state.value)
            }
            else -> Unit
        }
    }

    private fun chat(state: ChatState) {
        if (state.question.isBlank() && state.generatingStatus == GeneratingStatus.GENERATING) return

        chatJob = viewModelScope.launch {

            _state.update {
                it.copy(
                    generatingStatus = GeneratingStatus.GENERATING
                )
            }

            when (val result = chatUseCase.execute(state.chatId, state.question)) {
                is Resource.Error -> {
                    _state.update {
                        it.copy(
                            generatingStatus = GeneratingStatus.GENERATED,
                            error = (result.throwable as? ChatException)?.error
                        )
                    }
                }
                is Resource.Success -> {
                    _state.update {
                        it.copy(
                            generatingStatus = GeneratingStatus.GENERATED,
                            chatId = result.data?.second,
                            answer = result.data?.third
                        )
                    }
                }
            }
        }
    }

    private fun getLastChatId() = viewModelScope.launch {
        when (val result = getLastChatIdUseCase()) {
            is Resource.Error -> {
                _state.update {
                    it.copy(error = (result.throwable as? ChatException)?.error)
                }
            }
            is Resource.Success -> {
                _state.update {
                    it.copy(
                        chatId = result.data
                    )
                }
            }
        }
    }

    private fun toggleRecord(state: ChatState) {
        if (state.voiceToTextState == VoiceToTextStatus.SPEAKING || state.generatingStatus != GeneratingStatus.IDLE) return
        parser.reset()
        parser.startListening()
    }

    private fun getVoiceResults(state: ChatState) = viewModelScope.launch {
        parser.state.onEach { voiceResult ->
            _state.update {
                it.copy(
                    question = voiceResult.result,
                    error = if (state.canRecord) {
                        ChatError.VOICE_TO_TEXT_ERROR
                    } else {
                        ChatError.NO_PERMISSION
                    },
                    voiceToTextState = when {
                        !state.canRecord || voiceResult.error != null -> VoiceToTextStatus.ERROR
                        voiceResult.result.isNotBlank() && !voiceResult.isSpeaking -> VoiceToTextStatus.DISPLAYING_RESULTS
                        voiceResult.isSpeaking -> VoiceToTextStatus.SPEAKING
                        else -> VoiceToTextStatus.IDLE
                    }
                )
            }
        }.stateIn(viewModelScope)
    }

}