package com.tharuka.cognify.history.data.local

import com.tharuka.cognify.core.data.local.realm_object.ChatItem
import com.tharuka.cognify.history.domain.model.History
import io.realm.kotlin.query.RealmResults

fun RealmResults<ChatItem>.toHistory(): List<History>{
    return map {chatItem->
        History(
            chatId = chatItem.id.toString(),
            text = chatItem.conversations.first().answer?:""
        )
    }
}