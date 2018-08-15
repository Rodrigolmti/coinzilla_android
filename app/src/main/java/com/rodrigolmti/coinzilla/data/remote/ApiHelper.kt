package com.rodrigolmti.coinzilla.data.remote

import com.rodrigolmti.coinzilla.data.remote.endpoint.ICryptoCompareApi
import com.rodrigolmti.coinzilla.data.remote.endpoint.IMakertCapApi
import com.rodrigolmti.coinzilla.data.remote.endpoint.IWhatToMineApi
import com.rodrigolmti.coinzilla.di.scopes.PerApplication
import javax.inject.Inject

@PerApplication
class ApiHelper
@Inject constructor(
        private val iCryptoCompareApi: ICryptoCompareApi,
        private val iMakertCapApi: IMakertCapApi,
        private val iWhatToMineApi: IWhatToMineApi) : IApiHelper {

    override fun getWhatToMineGpu() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getWhatToMineAsic() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getWhatToMineWarz() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCryptoCurrency() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}