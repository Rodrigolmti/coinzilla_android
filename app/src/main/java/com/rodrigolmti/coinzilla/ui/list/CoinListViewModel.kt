package com.rodrigolmti.coinzilla.ui.list

import android.content.Context
import android.content.res.Resources
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import com.rodrigolmti.coinzilla.R
import com.rodrigolmti.coinzilla.data.IRepository
import com.rodrigolmti.coinzilla.data.model.api.CryptoCurrencyResponse
import com.rodrigolmti.coinzilla.data.model.api.WtmAltcoinResponse
import com.rodrigolmti.coinzilla.data.model.api.WtmAsicResponse
import com.rodrigolmti.coinzilla.data.model.api.WtmGpuResponse
import com.rodrigolmti.coinzilla.di.qualifier.AppContext
import com.rodrigolmti.coinzilla.di.scopes.PerActivity
import com.rodrigolmti.coinzilla.ui.base.viewModel.BaseViewModel
import com.rodrigolmti.coinzilla.util.MenuActionEnum
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

@PerActivity
class CoinListViewModel
@Inject constructor(
        @AppContext val context: Context,
        private val resources: Resources,
        private val iRepository: IRepository)
    : BaseViewModel() {

    val mutableGpuLiveData: MutableLiveData<List<WtmGpuResponse>> = MutableLiveData()
    val mutableAsicLiveData: MutableLiveData<List<WtmAsicResponse>> = MutableLiveData()
    val mutableAltcoinLiveData: MutableLiveData<List<WtmAltcoinResponse>> = MutableLiveData()
    val mutableCryptoCurrencyLiveData: MutableLiveData<List<CryptoCurrencyResponse>> = MutableLiveData()

    val loading: ObservableBoolean = ObservableBoolean(false)
    val error: ObservableBoolean = ObservableBoolean(false)

    fun getDataByAction(action: MenuActionEnum) {
        when (action) {
            MenuActionEnum.GPU -> fetchGpuCoins()
            MenuActionEnum.ASIC -> fetchAsicCoins()
            MenuActionEnum.ALTCOIN -> fetchAltcoins()
            MenuActionEnum.CRYPTOCURRENCY -> fetchCryptoCurrency()
        }
    }

    private fun fetchGpuCoins() {
        compositeDisposable.add(iRepository.getWhatToMineGpu()
                .doOnSubscribe {
                    loading.set(true)
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    mutableGpuLiveData.value = it
                    loading.set(false)
                }, {
                    Timber.e(it, resources.getString(R.string.general_error))
                    loading.set(false)
                    error.set(true)
                }))
    }

    private fun fetchAsicCoins() {
        compositeDisposable.add(iRepository.getWhatToMineAsic()
                .doOnSubscribe {
                    loading.set(true)
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    mutableAsicLiveData.value = it
                    loading.set(false)
                }, {
                    Timber.e(it, resources.getString(R.string.general_error))
                    loading.set(false)
                    error.set(true)
                }))
    }

    private fun fetchAltcoins() {
        compositeDisposable.add(iRepository.getWhatToMineAltcoins()
                .doOnSubscribe {
                    loading.set(true)
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    mutableAltcoinLiveData.value = it
                    loading.set(false)
                }, {
                    Timber.e(it, resources.getString(R.string.general_error))
                    loading.set(false)
                    error.set(true)
                }))
    }

    private fun fetchCryptoCurrency() {
        compositeDisposable.add(iRepository.getMarketCapList()
                .doOnSubscribe {
                    loading.set(true)
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    mutableCryptoCurrencyLiveData.value = it
                    loading.set(false)
                }, {
                    Timber.e(it, resources.getString(R.string.general_error))
                    loading.set(false)
                    error.set(true)
                }))
    }
}