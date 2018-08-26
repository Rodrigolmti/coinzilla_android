package com.rodrigolmti.coinzilla.ui.coinDetail

import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.content.res.Resources
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import com.rodrigolmti.coinzilla.R
import com.rodrigolmti.coinzilla.data.IRepository
import com.rodrigolmti.coinzilla.data.model.api.CryptoCurrencyResponse
import com.rodrigolmti.coinzilla.data.model.api.ExchangeResponse
import com.rodrigolmti.coinzilla.di.qualifier.AppContext
import com.rodrigolmti.coinzilla.di.scopes.PerActivity
import com.rodrigolmti.coinzilla.ui.base.view.MvvmView
import com.rodrigolmti.coinzilla.ui.base.viewModel.BaseViewModel
import com.rodrigolmti.coinzilla.util.extensions.formatCurrencyBRL
import com.rodrigolmti.coinzilla.util.extensions.formatCurrencyUSD
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

@PerActivity
class CoinDetailViewModel
@Inject constructor(
        @AppContext val context: Context,
        private val resources: Resources,
        private val iRepository: IRepository
) : BaseViewModel<MvvmView>(), CoinDetailMvvm {

    val priceBrl: ObservableField<String> = ObservableField()
    val priceUsd: ObservableField<String> = ObservableField()

    val name: ObservableField<String> = ObservableField()
    val symbol: ObservableField<String> = ObservableField()

    val mutableExchangeList: MutableLiveData<List<ExchangeResponse>> = MutableLiveData()
    val exchangeListVisible: ObservableBoolean = ObservableBoolean(false)
    val coinTag: MutableLiveData<String> = MutableLiveData()

    val loading: ObservableBoolean = ObservableBoolean(false)
    val error: ObservableBoolean = ObservableBoolean(false)

    fun getCoinDetailById(id: String) {
        compositeDisposable.add(iRepository.getMarketCapCoinDetail(id)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe { loading.set(true) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ it ->
                    setup(it)
                }, {
                    Timber.e(it, resources.getString(R.string.general_error))
                    error.set(true)
                }))
    }

    private fun setup(response: CryptoCurrencyResponse) {
        response.tag?.let { tag ->
            getCoinExchanges(tag)
            coinTag.value = tag
        }
        response.quoteBrl?.let {
            priceBrl.set("R$${it.price.formatCurrencyBRL()}")
        }
        response.quoteUsd?.let {
            priceUsd.set("$${it.price.formatCurrencyUSD()}")
        }
        response.name?.let {
            name.set(it)
        }
        response.tag?.let {
            symbol.set(it)
        }
    }

    private fun getCoinExchanges(tag: String) {
        compositeDisposable.add(iRepository.getExchanges(tag, resources.getString(R.string.activity_detail_usd))
                .subscribeOn(Schedulers.io())
                .doOnError { loading.set(false) }
                .doOnSubscribe { loading.set(false) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    exchangeListVisible.set(it.isNotEmpty())
                    mutableExchangeList.value = it
                }, {
                    Timber.e(it, resources.getString(R.string.general_error))
                    error.set(true)
                }))
    }
}