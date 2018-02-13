package com.rodrigolmti.coinzilla.coinzilla.model.dao

import com.rodrigolmti.coinzilla.coinzilla.model.entity.CryptoCurrency
import com.rodrigolmti.coinzilla.coinzilla.model.entity.WhatToMineAsic
import com.rodrigolmti.coinzilla.coinzilla.model.entity.WhatToMineGpu
import com.rodrigolmti.coinzilla.coinzilla.model.entity.WhatToMineWarz
import io.realm.Case
import io.realm.Realm

class Database {

    val realm: Realm = Realm.getDefaultInstance()

    fun insertWhatToMineGpu(data: ArrayList<WhatToMineGpu>) {
        realm.executeTransaction {
            realm.delete(WhatToMineGpu::class.java)
        }

        for ((id, obj) in data.withIndex()) {
            realm.executeTransaction {
                val whatToMine = realm.createObject(WhatToMineGpu::class.java, id)
                whatToMine.tag = obj.tag
                whatToMine.algorithm = obj.algorithm
                whatToMine.difficulty = obj.difficulty
                whatToMine.netHash = obj.netHash
                whatToMine.estReward = obj.estReward
                whatToMine.estReward24 = obj.estReward24
                whatToMine.marketCap = obj.marketCap
                whatToMine.updateDate = obj.updateDate
            }
        }
    }

    fun getAllWhatToMineGpu(): List<WhatToMineGpu> {
        var data: List<WhatToMineGpu> = listOf()
        realm.executeTransaction {
            data = realm.where(WhatToMineGpu::class.java).findAll()
        }
        return data
    }

    fun getWhatToMineGpuByFilter(filter: String): List<WhatToMineGpu> {
        var data: List<WhatToMineGpu> = listOf()
        realm.executeTransaction {
            data = realm.where(WhatToMineGpu::class.java)
                    .contains("tag", filter, Case.INSENSITIVE)
                    .or()
                    .contains("algorithm", filter, Case.INSENSITIVE)
                    .findAll()
        }
        return data
    }

    fun insertWhatToMineAsic(data: ArrayList<WhatToMineAsic>) {
        realm.executeTransaction {
            realm.delete(WhatToMineAsic::class.java)
        }

        for ((id, obj) in data.withIndex()) {
            realm.executeTransaction {
                val whatToMine = realm.createObject(WhatToMineAsic::class.java, id)
                whatToMine.tag = obj.tag
                whatToMine.algorithm = obj.algorithm
                whatToMine.difficulty = obj.difficulty
                whatToMine.netHash = obj.netHash
                whatToMine.estReward = obj.estReward
                whatToMine.estReward24 = obj.estReward24
                whatToMine.marketCap = obj.marketCap
                whatToMine.updateDate = obj.updateDate
            }
        }
    }

    fun getAllWhatToMineAsic(): List<WhatToMineAsic> {
        var data: List<WhatToMineAsic> = listOf()
        realm.executeTransaction {
            data = realm.where(WhatToMineAsic::class.java).findAll()
        }
        return data
    }

    fun getWhatToMineAsicByFilter(filter: String): List<WhatToMineAsic> {
        var data: List<WhatToMineAsic> = listOf()
        realm.executeTransaction {
            data = realm.where(WhatToMineAsic::class.java)
                    .contains("tag", filter, Case.INSENSITIVE)
                    .or()
                    .contains("algorithm", filter, Case.INSENSITIVE)
                    .findAll()
        }
        return data
    }

    fun insertWhatToMineWarz(data: List<WhatToMineWarz>) {
        realm.executeTransaction {
            realm.delete(WhatToMineWarz::class.java)
        }

        for (obj in data) {
            realm.executeTransaction {
                val whatToMine = realm.createObject(WhatToMineWarz::class.java, obj.id)
                whatToMine.tag = obj.tag
                whatToMine.algorithm = obj.algorithm
                whatToMine.difficulty = obj.difficulty
                whatToMine.netHash = obj.netHash
                whatToMine.estReward = obj.estReward
                whatToMine.estReward24 = obj.estReward24
                whatToMine.marketCap = obj.marketCap
                whatToMine.volume = obj.volume
                whatToMine.updateDate = obj.updateDate
            }
        }
    }

    fun getAllWhatToMineWarz(): List<WhatToMineWarz> {
        var data: List<WhatToMineWarz> = listOf()
        realm.executeTransaction {
            data = realm.where(WhatToMineWarz::class.java).findAll()
        }
        return data
    }

    fun getWhatToMineWarzByFilter(filter: String): List<WhatToMineWarz> {
        var data: List<WhatToMineWarz> = listOf()
        realm.executeTransaction {
            data = realm.where(WhatToMineWarz::class.java)
                    .contains("tag", filter, Case.INSENSITIVE)
                    .or()
                    .contains("algorithm", filter, Case.INSENSITIVE)
                    .findAll()
        }
        return data
    }

    fun insertCryptoCurrency(data: List<CryptoCurrency>) {
        realm.executeTransaction {
            realm.delete(CryptoCurrency::class.java)
        }

        for (obj in data) {

            obj.checkNullValue()

            realm.executeTransaction {
                val cryptoCurrency = realm.createObject(CryptoCurrency::class.java, obj.id)
                cryptoCurrency.name = obj.name
                cryptoCurrency.rank = obj.rank
                cryptoCurrency.symbol = obj.symbol
                cryptoCurrency.priceUsd = obj.priceUsd
                cryptoCurrency.priceBtc = obj.priceBtc
                cryptoCurrency.priceBrl = obj.priceBrl
                cryptoCurrency.volumeUsd = obj.volumeUsd
                cryptoCurrency.volumeBrl = obj.volumeBrl
                cryptoCurrency.marketCapUsd = obj.marketCapUsd
                cryptoCurrency.availableSupply = obj.availableSupply
                cryptoCurrency.totalSupply = obj.totalSupply
                cryptoCurrency.percentChange1H = obj.percentChange1H
                cryptoCurrency.percentChange24H = obj.percentChange24H
                cryptoCurrency.percentChange7D = obj.percentChange7D
                cryptoCurrency.marketCapBrl = obj.marketCapBrl
            }
        }
    }

    fun getAllCryptoCurrency(): List<CryptoCurrency> {
        var data: List<CryptoCurrency> = listOf()
        realm.executeTransaction {
            data = realm.where(CryptoCurrency::class.java).findAll()
        }
        return data
    }

    fun updateCryptoCurrencyFavorite(item: CryptoCurrency) {
        realm.executeTransaction {
            realm.insertOrUpdate(item)
        }
    }

    fun getAllFavorites(): List<CryptoCurrency> {
        var data: List<CryptoCurrency> = listOf()
        realm.executeTransaction {
            data = realm.where(CryptoCurrency::class.java).equalTo("favorite", true).findAll()
        }
        return data
    }
}