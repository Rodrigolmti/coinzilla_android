package com.rodrigolmti.coinzilla.ui.coinDetail

import android.content.Context
import android.content.res.Resources
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import com.rodrigolmti.coinzilla.R
import com.rodrigolmti.coinzilla.data.IRepository
import com.rodrigolmti.coinzilla.data.model.api.CryptoCurrencyResponse
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
@Inject constructor(@AppContext val context: Context, private val resources: Resources, private val iRepository: IRepository) : BaseViewModel<MvvmView>(), CoinDetailMvvm {

    val priceBrl: ObservableField<String> = ObservableField()
    val priceUsd: ObservableField<String> = ObservableField()

    val loading: ObservableBoolean = ObservableBoolean()
    val error: ObservableBoolean = ObservableBoolean()

    fun getCoinDetailById(id: String) {
        compositeDisposable.add(iRepository.getMarketCapCoinDetail(id)
                .doOnSubscribe {
                    loading.set(true)
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ it ->

                    init(it)
                    it.tag?.let { tag ->
                        getCoinHistorical(tag)
                        getCoinExchanges(tag)
                    }

                }, {
                    Timber.e(it, resources.getString(R.string.general_error))
                    loading.set(false)
                    error.set(true)
                }))
    }

    private fun init(response: CryptoCurrencyResponse) {
        response.quoteBrl?.let {
            priceBrl.set(it.price.formatCurrencyBRL())
        }
        response.quoteUsd?.let {
            priceUsd.set(it.price.formatCurrencyUSD())
        }
    }

    private fun getCoinHistorical(tag: String) {
        compositeDisposable.add(iRepository.getHistoric(tag, resources.getString(R.string.activity_detail_usd))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({

                    loading.set(false)

                }, {
                    Timber.e(it, resources.getString(R.string.general_error))
                    loading.set(false)
                    error.set(true)
                }))
    }

    private fun getCoinExchanges(tag : String) {
        compositeDisposable.add(iRepository.getExchanges(tag, resources.getString(R.string.activity_detail_usd))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({

                    loading.set(false)

                }, {
                    Timber.e(it, resources.getString(R.string.general_error))
                    loading.set(false)
                    error.set(true)
                }))
    }
}