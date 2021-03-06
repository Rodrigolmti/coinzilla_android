package com.rodrigolmti.coinzilla.ui.coinDetail.chart

import android.content.Context
import android.content.res.Resources
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.rodrigolmti.coinzilla.R
import com.rodrigolmti.coinzilla.data.IRepository
import com.rodrigolmti.coinzilla.data.model.api.HistoricResponse
import com.rodrigolmti.coinzilla.di.qualifier.AppContext
import com.rodrigolmti.coinzilla.di.scopes.PerFragment
import com.rodrigolmti.coinzilla.ui.base.viewModel.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

@PerFragment
class CoinChartViewModel
@Inject constructor(
        @AppContext val context: Context,
        private val resources: Resources,
        private val iRepository: IRepository
) : BaseViewModel() {

    val historicList: ObservableField<List<HistoricResponse>> = ObservableField(ArrayList())
    val historicChartVisible: ObservableBoolean = ObservableBoolean(false)

    val coinCurrency: ObservableField<String> = ObservableField()

    val loading: ObservableBoolean = ObservableBoolean(false)
    val error: ObservableBoolean = ObservableBoolean(false)

    fun getCoinHistorical(tag: String, currency: String) {
        coinCurrency.set(currency)
        compositeDisposable.add(iRepository.getHistoric(tag, currency)
                .subscribeOn(Schedulers.io())
                .doOnError { loading.set(false) }
                .doOnSubscribe { loading.set(false) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    historicChartVisible.set(it.isNotEmpty())
                    historicList.set(it)
                }, {
                    Timber.e(it, resources.getString(R.string.general_error))
                    error.set(true)
                }))
    }
}