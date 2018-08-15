package com.rodrigolmti.coinzilla.data.local.db

import com.rodrigolmti.coinzilla.di.scopes.PerApplication
import io.realm.Realm
import javax.inject.Inject
import javax.inject.Provider

@PerApplication
class DatabaseHelper
@Inject
constructor(private val realmProvider: Provider<Realm>) : IDatabaseHelper {
}