package com.rodrigolmti.coinzilla.coinzilla.model.dao

import com.rodrigolmti.coinzilla.coinzilla.model.entity.coin.CryptoCurrency
import com.rodrigolmti.coinzilla.data.model.api.WtmAsicResponse
import com.rodrigolmti.coinzilla.data.model.api.WtmGpuResponse
import com.rodrigolmti.coinzilla.data.model.api.WtmWarzResponse
import io.realm.Case
import io.realm.Realm

class Database {

    val realm: Realm = Realm.getDefaultInstance()

    fun insertWhatToMineGpu(data: ArrayList<WtmGpuResponse>) {
        realm.executeTransaction {
//            realm.delete(WtmGpuResponse::class.java)
        }

        for ((id, obj) in data.withIndex()) {
            realm.executeTransaction {
//                val whatToMine = realm.createObject(WtmGpuResponse::class.java, id)
//                whatToMine.tag = obj.tag
//                whatToMine.algorithm = obj.algorithm
//                whatToMine.difficulty = obj.difficulty
//                whatToMine.netHash = obj.netHash
//                whatToMine.estReward = obj.estReward
//                whatToMine.estReward24 = obj.estReward24
//                whatToMine.marketCap = obj.marketCap
//                whatToMine.updateDate = obj.updateDate
            }
        }
    }

    fun getAllWhatToMineGpu(): List<WtmGpuResponse> {
        var data: List<WtmGpuResponse> = listOf()
        realm.executeTransaction {
//            data = realm.where(WtmGpuResponse::class.java).findAll()
        }
        return data
    }

    fun getWhatToMineGpuByFilter(filter: String): List<WtmGpuResponse> {
        var data: List<WtmGpuResponse> = listOf()
        realm.executeTransaction {
//            data = realm.where(WtmGpuResponse::class.java)
//                    .contains("tag", filter, Case.INSENSITIVE)
//                    .or()
//                    .contains("algorithm", filter, Case.INSENSITIVE)
//                    .findAll()
        }
        return data
    }

    fun insertWhatToMineAsic(data: ArrayList<WtmAsicResponse>) {
        realm.executeTransaction {
//            realm.delete(WtmAsicResponse::class.java)
        }

        for ((id, obj) in data.withIndex()) {
            realm.executeTransaction {
//                val whatToMine = realm.createObject(WtmAsicResponse::class.java, id)
//                whatToMine.tag = obj.tag
//                whatToMine.algorithm = obj.algorithm
//                whatToMine.difficulty = obj.difficulty
//                whatToMine.netHash = obj.netHash
//                whatToMine.estReward = obj.estReward
//                whatToMine.estReward24 = obj.estReward24
//                whatToMine.marketCap = obj.marketCap
//                whatToMine.updateDate = obj.updateDate
            }
        }
    }

    fun getAllWhatToMineAsic(): List<WtmAsicResponse> {
        var data: List<WtmAsicResponse> = listOf()
        realm.executeTransaction {
//            data = realm.where(WtmAsicResponse::class.java).findAll()
        }
        return data
    }

    fun getWhatToMineAsicByFilter(filter: String): List<WtmAsicResponse> {
        var data: List<WtmAsicResponse> = listOf()
        realm.executeTransaction {
//            data = realm.where(WtmAsicResponse::class.java)
//                    .contains("tag", filter, Case.INSENSITIVE)
//                    .or()
//                    .contains("algorithm", filter, Case.INSENSITIVE)
//                    .findAll()
        }
        return data
    }

    fun insertWhatToMineWarz(data: List<WtmWarzResponse>) {
        realm.executeTransaction {
//            realm.delete(WtmWarzResponse::class.java)
        }

        for (obj in data) {
            realm.executeTransaction {
//                val whatToMine = realm.createObject(WtmWarzResponse::class.java, obj.id)
//                whatToMine.tag = obj.tag
//                whatToMine.algorithm = obj.algorithm
//                whatToMine.difficulty = obj.difficulty
//                whatToMine.netHash = obj.netHash
//                whatToMine.estReward = obj.estReward
//                whatToMine.estReward24 = obj.estReward24
//                whatToMine.marketCap = obj.marketCap
//                whatToMine.volume = obj.volume
//                whatToMine.updateDate = obj.updateDate
            }
        }
    }

    fun getAllWhatToMineWarz(): List<WtmWarzResponse> {
        var data: List<WtmWarzResponse> = listOf()
        realm.executeTransaction {
//            data = realm.where(WtmWarzResponse::class.java).findAll()
        }
        return data
    }

    fun getWhatToMineWarzByFilter(filter: String): List<WtmWarzResponse> {
        var data: List<WtmWarzResponse> = listOf()
        realm.executeTransaction {
//            data = realm.where(WtmWarzResponse::class.java)
//                    .contains("tag", filter, Case.INSENSITIVE)
//                    .or()
//                    .contains("algorithm", filter, Case.INSENSITIVE)
//                    .findAll()
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