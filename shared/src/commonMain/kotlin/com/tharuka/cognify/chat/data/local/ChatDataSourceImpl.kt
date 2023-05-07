package com.tharuka.cognify.chat.data.local

import com.tharuka.cognify.core.data.local.realm_object.ChatItem
import com.tharuka.cognify.core.data.local.realm_object.Conversation
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import io.realm.kotlin.query.Sort
import io.realm.kotlin.types.RealmUUID
import kotlinx.datetime.Clock

class ChatDataSourceImpl(
    private val realm: Realm
) : ChatDataSource {

    override suspend fun createNewChat(answer: String, question: String): RealmUUID {

        val id: RealmUUID
        val chat = realm.write {
            this.copyToRealm(ChatItem().apply {
                conversations.add(
                    Conversation().apply {
                        this.answer = answer
                        this.question = question
                    }
                )
            })
        }
        id = chat.id
        return id
    }

    override suspend fun updateChat(id: RealmUUID, answer: String, question: String): Boolean? {

        val success = realm.write {
            val chat: ChatItem? = this.query<ChatItem>("id == $0", id).first().find()
            //update time
            chat?.updatedAt = Clock.System.now().epochSeconds
            //update with new item
            chat?.conversations?.add(
                Conversation().apply {
                    this.answer = answer
                    this.question = question
                }
            )
        }
        return success
    }

    override suspend fun getLastChatId(): RealmUUID? {
        return try {
            return realm.query<ChatItem>().sort("updatedAt", Sort.DESCENDING).limit(1).find()
                .first().id

        } catch (exception: NullPointerException) {
            null
        }
    }
}