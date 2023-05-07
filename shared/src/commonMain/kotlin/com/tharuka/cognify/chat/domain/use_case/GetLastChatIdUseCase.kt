package com.tharuka.cognify.chat.domain.use_case

import com.tharuka.cognify.chat.domain.error.ChatError
import com.tharuka.cognify.chat.domain.error.ChatException
import com.tharuka.cognify.chat.domain.repository.ChatRepository
import com.tharuka.cognify.core.domain.util.Resource
import io.realm.kotlin.types.RealmUUID

class GetLastChatIdUseCase(
    private val chatRepository: ChatRepository
) {

    suspend operator fun invoke(): Resource<RealmUUID?>{
        return try {
            val lastChatId = chatRepository.getLastChatId()
            return Resource.Success(lastChatId)
        }catch (exception: ChatException){
            exception.printStackTrace()
            Resource.Error(exception)
        }catch (exception: Exception){
            exception.printStackTrace()
            Resource.Error(ChatException(ChatError.LAST_CHAT_NOT_FOUND))
        }
    }

}