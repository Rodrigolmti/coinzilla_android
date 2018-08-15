package com.rodrigolmti.coinzilla.data

import com.rodrigolmti.coinzilla.data.local.db.IDatabaseHelper
import com.rodrigolmti.coinzilla.data.local.prefs.IPreferencesHelper
import com.rodrigolmti.coinzilla.data.model.api.WtmAsicResponse
import com.rodrigolmti.coinzilla.data.model.api.WtmGpuResponse
import com.rodrigolmti.coinzilla.data.model.api.WtmWarzResponse
import com.rodrigolmti.coinzilla.data.remote.IApiHelper
import com.rodrigolmti.coinzilla.di.scopes.PerApplication
import io.reactivex.Single
import javax.inject.Inject

@PerApplication
class Repository
@Inject constructor(
        private val iApiHelper: IApiHelper,
        private val iDatabaseHelper: IDatabaseHelper,
        private val iPreferencesHelper: IPreferencesHelper) : IRepository {

    override fun getToken() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAuthenticationToken(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setAuthenticationToken(token: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAuthenticationTokenTime(): Long {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setAuthenticationTokenTime(time: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getRealmEncryptionKey(): ByteArray? {
        return iPreferencesHelper.getRealmEncryptionKey()
    }

    override fun setRealmEncryptionKey(bytes: ByteArray?) {
        iPreferencesHelper.setRealmEncryptionKey(bytes)
    }

    override fun getGpuUpdateTime(): Long {
        return iPreferencesHelper.getGpuUpdateTime()
    }

    override fun setGpuUpdateTime(time: Long) {
        iPreferencesHelper.setGpuUpdateTime(time)
    }

    override fun getAsicUpdateTime(): Long {
        return iPreferencesHelper.getAsicUpdateTime()
    }

    override fun setAsicUpdateTime(time: Long) {
        iPreferencesHelper.setAsicUpdateTime(time)
    }

    override fun getAltcoinUpdateTime(): Long {
        return iPreferencesHelper.getAltcoinUpdateTime()
    }

    override fun setAltcoinUpdateTime(time: Long) {
        iPreferencesHelper.setAltcoinUpdateTime(time)
    }

    override fun getCryptocurrencyUpdateTime(): Long {
        return iPreferencesHelper.getCryptocurrencyUpdateTime()
    }

    override fun setCryptocurrencyUpdateTime(time: Long) {
        iPreferencesHelper.setCryptocurrencyUpdateTime(time)
    }

    override fun getWhatToMineGpu(): Single<List<WtmGpuResponse>> {
        return iApiHelper.getWhatToMineGpu()
    }

    override fun getWhatToMineAsic(): Single<List<WtmAsicResponse>> {
        return iApiHelper.getWhatToMineAsic()
    }

    override fun getWhatToMineWarz(): Single<List<WtmWarzResponse>> {
        return iApiHelper.getWhatToMineWarz()
    }

    override fun getCryptoCurrency() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}