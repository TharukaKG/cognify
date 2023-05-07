package com.tharuka.cognify.android.di

import com.tharuka.cognify.chat.data.remote.ChatClient
import com.tharuka.cognify.chat.data.remote.ChatClientImpl
import com.tharuka.cognify.core.data.local.realm_object.ChatItem
import com.tharuka.cognify.core.data.local.realm_object.Conversation
import com.tharuka.cognify.core.data.remote.HttpClientFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideChatClient(httpClient: HttpClient): ChatClient {
        return ChatClientImpl(httpClient)
    }

    @Provides
    @Singleton
    fun providesHttpClient(): HttpClient {
        return HttpClientFactory().create()
    }

}