package com.tharuka.cognify.android.di

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.tharuka.cognify.core.data.local.realm_object.ChatItem
import com.tharuka.cognify.core.data.local.realm_object.Conversation
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Provides
    @Singleton
    fun providesRealm(
        realmConfiguration: RealmConfiguration,
        application: Application
    ): Realm {

        val realm = Realm.open(realmConfiguration)

        application.registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {}
            override fun onActivityStarted(activity: Activity) {}
            override fun onActivityResumed(activity: Activity) {}
            override fun onActivityPaused(activity: Activity) {}
            override fun onActivityStopped(activity: Activity) {}
            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}
            override fun onActivityDestroyed(activity: Activity) {
                if (activity.isFinishing) {
                    realm.close()
                }
            }
        })

        return realm
    }

    @Provides
    @Singleton
    fun providesRealmConfiguration(): RealmConfiguration {
        return RealmConfiguration
            .create(
                schema = setOf(
                    ChatItem::class,
                    Conversation::class
                )
            )
    }

}