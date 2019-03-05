package com.rodrigolmti.coinzilla.ui.main

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.rodrigolmti.coinzilla.R
import com.rodrigolmti.coinzilla.data.IRepository
import com.rodrigolmti.coinzilla.di.qualifier.AppContext
import com.rodrigolmti.coinzilla.di.scopes.PerActivity
import com.rodrigolmti.coinzilla.ui.base.navigation.IActivityNavigator
import com.rodrigolmti.coinzilla.ui.base.viewModel.BaseViewModel
import com.rodrigolmti.coinzilla.ui.info.InfoActivity
import com.rodrigolmti.coinzilla.ui.list.CoinListActivity
import com.rodrigolmti.coinzilla.ui.profitability.ProfitabilityActivity
import com.rodrigolmti.coinzilla.util.MenuActionEnum
import com.rodrigolmti.coinzilla.util.Utils
import com.rodrigolmti.coinzilla.util.exceptions.TokenValid
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.util.*
import javax.inject.Inject

@PerActivity
class MainActivityViewModel
@Inject constructor(
        @AppContext val context: Context,
        private val resources: Resources,
        private val iRepository: IRepository,
        private val activityNavigator: IActivityNavigator)
    : BaseViewModel() {

    val gpuUpdateTime: ObservableField<String> = ObservableField("")
    val asicUpdateTime: ObservableField<String> = ObservableField("")
    val altcoinUpdateTime: ObservableField<String> = ObservableField("")
    val cryptocurrencyUpdateTime: ObservableField<String> = ObservableField("")

    val loading: ObservableBoolean = ObservableBoolean(false)
    val error: ObservableBoolean = ObservableBoolean(false)

    init {
        compositeDisposable.add(iRepository.getToken()
                .doOnSubscribe { loading.set(true) }
                .doOnError { loading.set(false) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    updateTimeLabel()
                }, {
                    if (it !is TokenValid) {
                        error.set(true)
                        Timber.e(it)
                    }
                }))
    }

    fun updateTimeLabel() {
        if (iRepository.getGpuUpdateTime() > 0) {
            gpuUpdateTime.set(resources.getString(R.string.activity_list_update,
                    Utils.formatCustomDate(Date(iRepository.getGpuUpdateTime()))))
        }
        if (iRepository.getAsicUpdateTime() > 0) {
            asicUpdateTime.set(resources.getString(R.string.activity_list_update,
                    Utils.formatCustomDate(Date(iRepository.getAsicUpdateTime()))))
        }
        if (iRepository.getAltcoinUpdateTime() > 0) {
            altcoinUpdateTime.set(resources.getString(R.string.activity_list_update,
                    Utils.formatCustomDate(Date(iRepository.getAltcoinUpdateTime()))))
        }
        if (iRepository.getCryptocurrencyUpdateTime() > 0) {
            cryptocurrencyUpdateTime.set(resources.getString(R.string.activity_list_update,
                    Utils.formatCustomDate(Date(iRepository.getCryptocurrencyUpdateTime()))))
        }
    }

    fun clickInfo() {
        startActivity(Intent(context, InfoActivity::class.java))
    }

    fun clickGpuMining() {
        startActivity(Intent(context, CoinListActivity::class.java), MenuActionEnum.GPU)
    }

    fun clickAsicMining() {
        startActivity(Intent(context, CoinListActivity::class.java), MenuActionEnum.ASIC)
    }

    fun clickAltCoin() {
        startActivity(Intent(context, CoinListActivity::class.java), MenuActionEnum.ALTCOIN)
    }

    fun clickCryptoCurrency() {
        startActivity(Intent(context, CoinListActivity::class.java), MenuActionEnum.CRYPTOCURRENCY)
    }

    fun clickProfitability() {
        startActivity(Intent(context, ProfitabilityActivity::class.java))
    }

    private fun startActivity(intent: Intent, action: MenuActionEnum? = null) {
        if (action != null) {
            intent.putExtra("action.type", action.name)
        }
        activityNavigator.startActivity(intent)
    }
}