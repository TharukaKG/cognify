package com.tharuka.cognify.core.data.local

import io.realm.kotlin.Configuration
import io.realm.kotlin.Realm

abstract class RealmDatabase(override val configuration: Configuration) : Realm