package com.tharuka.cognify.di

import com.tharuka.cognify.chat.data.ChatRepositoryImpl
import com.tharuka.cognify.chat.data.local.ChatDataSource
import com.tharuka.cognify.chat.data.local.ChatDataSourceImpl
import com.tharuka.cognify.chat.data.remote.ChatClient
import com.tharuka.cognify.chat.data.remote.ChatClientImpl
import com.tharuka.cognify.chat.domain.repository.ChatRepository
import com.tharuka.cognify.chat.domain.use_case.ChatUseCase
import com.tharuka.cognify.chat.domain.use_case.GetLastChatIdUseCase
import com.tharuka.cognify.core.data.local.realm_object.ChatItem
import com.tharuka.cognify.core.data.local.realm_object.Conversation
import com.tharuka.cognify.core.data.remote.HttpClientFactory
import io.ktor.client.*
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration

class AppModule {

    val chatUseCase: ChatUseCase by lazy {
        ChatUseCase(chatRepository)
    }

    val getLastChatIdUseCase: GetLastChatIdUseCase by lazy {
        GetLastChatIdUseCase(chatRepository)
    }

    private val chatRepository: ChatRepository by lazy {
        ChatRepositoryImpl(chatDataSource, chatClient)
    }

    private val chatDataSource: ChatDataSource by lazy {
        ChatDataSourceImpl(realm)
    }

    private val chatClient: ChatClient by lazy {
        ChatClientImpl(httpClient)
    }

    private val httpClient: HttpClient by lazy {
        HttpClientFactory().create()
    }

    private val realmConfiguration: RealmConfiguration by lazy {
        RealmConfiguration
            .create(
                schema = setOf(
                    ChatItem::class,
                    Conversation::class
                )
            )
    }

    private val realm: Realm by lazy {
        Realm.open(realmConfiguration)
    }

}