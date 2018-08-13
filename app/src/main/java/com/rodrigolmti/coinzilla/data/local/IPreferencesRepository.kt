package com.rodrigolmti.coinzilla.data.local

interface IPreferencesRepository {

    var realmEncryptionKey: ByteArray?
    var authenticationToken: String
    var authenticationTokenTime: Long
    var gpuUpdateTime: Long
    var asicUpdateTime: Long
    var altcoinUpdateTime: Long
    var cryptocurrencyUpdateTime: Long
}