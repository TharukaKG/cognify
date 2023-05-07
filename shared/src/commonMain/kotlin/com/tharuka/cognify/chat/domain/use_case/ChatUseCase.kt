package com.tharuka.cognify.chat.domain.use_case

import com.tharuka.cognify.chat.domain.error.ChatException
import com.tharuka.cognify.chat.domain.repository.ChatRepository
import com.tharuka.cognify.core.domain.util.Resource
import io.realm.kotlin.types.RealmUUID

class ChatUseCase(
    private val chatRepository:ChatRepository
) {

    suspend fun execute(chatId: RealmUUID?, question:String): Resource<Triple<Boolean, RealmUUID, String>>{
        return try {

            val result = chatRepository.chat(question)

            return if(chatId!=null){
                val success = chatRepository.updateChatItem(chatId, result, question)?:false
                Resource.Success(data = Triple(first = success, second = chatId, third = result))
            }else{
                val id = chatRepository.insertChatItem(result, question)
                Resource.Success(data = Triple(first = true, second = id, third = result))
            }

        }catch (exception: ChatException){
            exception.printStackTrace()
            Resource.Error(exception)
        }
    }

}