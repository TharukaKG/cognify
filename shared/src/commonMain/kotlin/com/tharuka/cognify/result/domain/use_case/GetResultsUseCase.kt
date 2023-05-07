package com.tharuka.cognify.result.domain.use_case

import com.tharuka.cognify.core.data.local.realm_object.ChatItem
import com.tharuka.cognify.core.domain.util.Resource
import com.tharuka.cognify.result.domain.ResultsRepository
import io.realm.kotlin.types.RealmUUID

class GetResultsUseCase(
    private val resultsRepository: ResultsRepository
) {
    suspend operator fun invoke(chatId:RealmUUID):Resource<ChatItem>{
        return try {
            val chatItem = resultsRepository.getResults(chatId)
            Resource.Success(chatItem)
        }catch (exception:Exception){
            exception.printStackTrace()
            Resource.Error(exception)
        }
    }
}