package com.tharuka.cognify.chat.data.remote

import com.tharuka.cognify.NetworkConstants
import com.tharuka.cognify.chat.domain.error.ChatError
import com.tharuka.cognify.chat.domain.error.ChatException
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.utils.io.errors.*

class ChatClientImpl(
    private val httpClient: HttpClient
): ChatClient {

    override suspend fun chat(question: String): String {
        val result = try {
            httpClient.post {
                url(NetworkConstants.BASE_URL+"/dev/cognify")
                contentType(ContentType.Application.Json)
                setBody(
                    QuestionDto(
                        listOf(
                            Message(
                                question = question
                            )
                        )
                    )
                )
            }
        }catch (exception: IOException){
            throw ChatException(ChatError.SERVICE_UNAVAILABLE)
        }

        println("DEBUGGING:::${result.body<AnswerDto>()}")

        when(result.status.value){
            in 200..299 ->Unit
            in 400..499 -> throw ChatException(ChatError.CLIENT_ERROR)
            500 -> throw ChatException(ChatError.SERVER_ERROR)
            else -> throw ChatException(ChatError.UNKNOWN_ERROR)
        }

        return try {
            result.body<AnswerDto>().content
        }catch (exception: Exception){
            throw ChatException(ChatError.SERVER_ERROR)
        }

    }

}