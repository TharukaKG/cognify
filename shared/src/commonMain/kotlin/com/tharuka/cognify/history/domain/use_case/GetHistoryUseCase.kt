package com.tharuka.cognify.history.domain.use_case

import com.tharuka.cognify.core.domain.util.Resource
import com.tharuka.cognify.history.domain.HistoryRepository
import com.tharuka.cognify.history.domain.error.HistoryException
import com.tharuka.cognify.history.domain.model.History

class GetHistoryUseCase(private val historyRepository: HistoryRepository) {
    suspend operator fun invoke(): Resource<List<History>>{
        return try {
            val result = historyRepository.getHistory()
            Resource.Success(result)
        }catch(exception:HistoryException){
            exception.printStackTrace()
            Resource.Error(exception)
        }catch (exception:Exception){
            exception.printStackTrace()
            Resource.Error(exception)
        }
    }
}