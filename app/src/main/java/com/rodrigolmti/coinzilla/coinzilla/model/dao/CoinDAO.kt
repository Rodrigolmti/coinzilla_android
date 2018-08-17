package com.rodrigolmti.coinzilla.coinzilla.model.dao

import com.rodrigolmti.coinzilla.coinzilla.model.entity.coin.CryptoCurrency
import com.rodrigolmti.coinzilla.data.model.api.WtmAsicResponse
import com.rodrigolmti.coinzilla.data.model.api.WtmGpuResponse
import com.rodrigolmti.coinzilla.data.model.api.WtmAltcoinResponse

/**
* Created by rodrigolmti on 12/02/18.
*/

class CoinDAO {

    private val database: Database = Database()

    fun insertWhatToMineGpu(list: ArrayList<WtmGpuResponse>) {
        database.insertWhatToMineGpu(list)
    }

    fun insertWhatToMineAsic(list: ArrayList<WtmAsicResponse>) {
        database.insertWhatToMineAsic(list)
    }

    fun insertWhatToMineWarz(list: List<WtmAltcoinResponse>) {
        database.insertWhatToMineWarz(list)
    }

    fun insertCryptoCurrency(list: List<CryptoCurrency>) {
        database.insertCryptoCurrency(list)
    }

    fun updateCryptoCurrencyFavorite(item: CryptoCurrency) {
        database.updateCryptoCurrencyFavorite(item)
    }

    fun getAllFavorites(): List<CryptoCurrency> {
        return database.getAllFavorites()
    }

    fun getAllWhatToMineGpu(): List<WtmGpuResponse> {
        return database.getAllWhatToMineGpu()
    }

    fun getWhatToMineGpuByFilter(filter: String): List<WtmGpuResponse> {
        return database.getWhatToMineGpuByFilter(filter)
    }

    fun getAllWhatToMineAsic(): List<WtmAsicResponse> {
        return database.getAllWhatToMineAsic()
    }

    fun getWhatToMineAsicByFilter(filter: String): List<WtmAsicResponse> {
        return database.getWhatToMineAsicByFilter(filter)
    }

    fun getAllWhatToMineWarz(): List<WtmAltcoinResponse> {
        return database.getAllWhatToMineWarz()
    }

    fun getWhatToMineWarzByFilter(filter: String): List<WtmAltcoinResponse> {
        return database.getWhatToMineWarzByFilter(filter)
    }

    fun getAllCryptoCurrency(): List<CryptoCurrency> {
        return database.getAllCryptoCurrency()
    }
}