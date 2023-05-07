package com.tharuka.cognify.android.chat.di

import android.app.Application
import com.tharuka.cognify.android.chat.data.AndroidVoiceToTextParser
import com.tharuka.cognify.chat.domain.VoiceToTextParser
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object VoiceToTextModule {

    @Provides
    fun provideVoiceTiTextParser(
        application: Application
    ): VoiceToTextParser{
        return AndroidVoiceToTextParser(application)
    }

}