package com.tharuka.cognify.android.di

import com.tharuka.cognify.chat.data.ChatRepositoryImpl
import com.tharuka.cognify.chat.data.local.ChatDataSource
import com.tharuka.cognify.chat.data.local.ChatDataSourceImpl
import com.tharuka.cognify.chat.data.remote.ChatClient
import com.tharuka.cognify.chat.domain.repository.ChatRepository
import com.tharuka.cognify.chat.domain.use_case.ChatUseCase
import com.tharuka.cognify.chat.domain.use_case.GetLastChatIdUseCase
import com.tharuka.cognify.history.data.HistoryRepositoryImpl
import com.tharuka.cognify.history.data.local.HistoryDataSourceImpl
import com.tharuka.cognify.history.data.local.HistoryDataSource
import com.tharuka.cognify.history.domain.HistoryRepository
import com.tharuka.cognify.history.domain.use_case.GetHistoryUseCase
import com.tharuka.cognify.result.data.ResultRepositoryImpl
import com.tharuka.cognify.result.data.local.ResultDataSource
import com.tharuka.cognify.result.data.local.ResultDataSourceImpl
import com.tharuka.cognify.result.domain.ResultsRepository
import com.tharuka.cognify.result.domain.use_case.GetResultsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.realm.kotlin.Realm
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    /**
     * chat use_case
     */
    @Provides
    @Singleton
    fun providesChatUseCase(
        chatRepository: ChatRepository
    ): ChatUseCase {
        return ChatUseCase(chatRepository)
    }

    @Provides
    @Singleton
    fun providesGetLastChatIdUseCase(
        chatRepository: ChatRepository
    ): GetLastChatIdUseCase {
        return GetLastChatIdUseCase(chatRepository)
    }

    @Provides
    @Singleton
    fun providesChatRepository(
        chatDataSource: ChatDataSource,
        chatClient: ChatClient
    ): ChatRepository {
        return ChatRepositoryImpl(chatDataSource, chatClient)
    }

    @Provides
    @Singleton
    fun providesChatDatSource(realm: Realm): ChatDataSource {
        return ChatDataSourceImpl(realm)
    }

    /**
     * results use_case
     * */
    @Provides
    @Singleton
    fun providesResultUseCase(
        resultsRepository: ResultsRepository
    ): GetResultsUseCase {
        return GetResultsUseCase(resultsRepository)
    }

    @Provides
    @Singleton
    fun providesResultsRepository(
        resultDataSource: ResultDataSource
    ): ResultsRepository {
        return ResultRepositoryImpl(resultDataSource)
    }

    @Provides
    @Singleton
    fun providesResultDataSource(
        realm: Realm
    ): ResultDataSource {
        return ResultDataSourceImpl(realm)
    }

    /**
     * history use case
     * */

    @Provides
    @Singleton
    fun providesHistoryUseCase(
        historyRepository: HistoryRepository
    ): GetHistoryUseCase {
        return GetHistoryUseCase(historyRepository)
    }

    @Provides
    @Singleton
    fun providesHistoryRepository(
        historyDataSource: HistoryDataSource
    ): HistoryRepository {
        return HistoryRepositoryImpl(historyDataSource)
    }

    @Provides
    @Singleton
    fun providesHistoryDataSource(
        realm: Realm
    ): HistoryDataSource {
        return HistoryDataSourceImpl(realm)
    }

}
