package com.tharuka.cognify.core.data.local.realm_object

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.RealmUUID
import io.realm.kotlin.types.annotations.PrimaryKey
import kotlinx.datetime.Clock

class ChatItem: RealmObject {
    @PrimaryKey
    var id: RealmUUID = RealmUUID.random()
    var conversations: RealmList<Conversation> = realmListOf()
    var createdAt: Long = Clock.System.now().epochSeconds
    var updatedAt: Long = Clock.System.now().epochSeconds
}

class Conversation: EmbeddedRealmObject{
    var id:RealmUUID = RealmUUID.random()
    var answer:String? = null
    var question:String? = null
}