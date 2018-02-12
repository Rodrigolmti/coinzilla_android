package com.rodrigolmti.coinzilla.coinzilla.model.dao

import com.rodrigolmti.coinzilla.coinzilla.model.entity.CryptoCurrency
import com.rodrigolmti.coinzilla.coinzilla.model.entity.WhatToMineAsic
import com.rodrigolmti.coinzilla.coinzilla.model.entity.WhatToMineGpu
import com.rodrigolmti.coinzilla.coinzilla.model.entity.WhatToMineWarz

/**
* Created by rodrigolmti on 12/02/18.
*/

class CoinDAO {

    private val database: Database = Database()

    fun insertWhatToMineGpu(list: ArrayList<WhatToMineGpu>) {
        database.insertWhatToMineGpu(list)
    }

    fun insertWhatToMineAsic(list: ArrayList<WhatToMineAsic>) {
        database.insertWhatToMineAsic(list)
    }

    fun insertWhatToMineWarz(list: List<WhatToMineWarz>) {
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

    fun getAllWhatToMineGpu(): List<WhatToMineGpu> {
        return database.getAllWhatToMineGpu()
    }

    fun getAllWhatToMineAsic(): List<WhatToMineAsic> {
        return database.getAllWhatToMineAsic()
    }

    fun getAllWhatToMineWarz(): List<WhatToMineWarz> {
        return database.getAllWhatToMineWarz()
    }

    fun getAllCryptoCurrency(): List<CryptoCurrency> {
        return database.getAllCryptoCurrency()
    }
}