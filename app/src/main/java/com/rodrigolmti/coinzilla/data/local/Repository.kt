package com.rodrigolmti.coinzilla.data.local

import com.rodrigolmti.coinzilla.di.scopes.PerApplication
import io.realm.Realm
import javax.inject.Inject
import javax.inject.Provider

@PerApplication
class Repository
@Inject
constructor(private val realmProvider: Provider<Realm>) : IRepository {
}