package com.rodrigolmti.coinzilla.ui.main

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import com.rodrigolmti.coinzilla.R
import com.rodrigolmti.coinzilla.coinzilla.view.activity.InfoActivity
import com.rodrigolmti.coinzilla.ui.list.CoinListActivity
import com.rodrigolmti.coinzilla.coinzilla.view.activity.ProfitabilityActivity
import com.rodrigolmti.coinzilla.data.local.IPreferencesRepository
import com.rodrigolmti.coinzilla.data.remote.INodeApi
import com.rodrigolmti.coinzilla.di.qualifier.AppContext
import com.rodrigolmti.coinzilla.di.scopes.PerActivity
import com.rodrigolmti.coinzilla.library.util.Action
import com.rodrigolmti.coinzilla.library.util.Utils
import com.rodrigolmti.coinzilla.ui.base.navigation.IActivityNavigator
import com.rodrigolmti.coinzilla.ui.base.view.MvvmView
import com.rodrigolmti.coinzilla.ui.base.viewModel.BaseViewModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import timber.log.Timber
import java.util.*
import javax.inject.Inject

@PerActivity
class MainActivityViewModel
@Inject constructor(@AppContext val context: Context,
                    private val resources: Resources,
                    private val iNodeApi: INodeApi,
                    private val activityNavigator: IActivityNavigator,
                    private val iPreferencesRepository: IPreferencesRepository)
    : BaseViewModel<MvvmView>(), MainMvvm {

    val gpuUpdateTime: ObservableField<String> = ObservableField("")
    val asicUpdateTime: ObservableField<String> = ObservableField("")
    val altcoinUpdateTime: ObservableField<String> = ObservableField("")
    val cryptocurrencyUpdateTime: ObservableField<String> = ObservableField("")

    val loading: ObservableBoolean = ObservableBoolean(false)
    val error: ObservableBoolean = ObservableBoolean(false)

    init {
        getToken()
    }

    private fun getToken() {
        if (checkTime()) return
        if (Utils().isDeviceOnline(context)) {
            loading.set(true)
            compositeDisposable.add(iNodeApi.getToken(UUID.randomUUID().toString())
                    .observeOn(AndroidSchedulers.mainThread())
                    .flatMap {
                        if (it.success) {
                            iPreferencesRepository.authenticationToken = it.token
                            iPreferencesRepository.authenticationTokenTime = Date().time
                        }
                        Single.just(it)
                    }
                    .subscribe({
                        loading.set(false)
                    }, {
                        Timber.e(it, resources.getString(R.string.general_error))
                        loading.set(false)
                        error.set(true)
                    }))
        }
    }

    private fun checkTime(): Boolean {
        if (iPreferencesRepository.authenticationTokenTime > 0) {
            val calendar: Calendar = Calendar.getInstance()
            calendar.time = Date(iPreferencesRepository.authenticationTokenTime)
            calendar.add(Calendar.MINUTE, +1000)
            val dateAfter: Date = calendar.time
            return Date().after(dateAfter)
        }
        return false
    }

    fun updateTimeLabel() {
        if (iPreferencesRepository.gpuUpdateTime > 0) {
            gpuUpdateTime.set(resources.getString(R.string.activity_list_update,
                    Utils.formatCustomDate(Date(iPreferencesRepository.gpuUpdateTime))))
        }
        if (iPreferencesRepository.asicUpdateTime > 0) {
            asicUpdateTime.set(resources.getString(R.string.activity_list_update,
                    Utils.formatCustomDate(Date(iPreferencesRepository.asicUpdateTime))))
        }
        if (iPreferencesRepository.altcoinUpdateTime > 0) {
            altcoinUpdateTime.set(resources.getString(R.string.activity_list_update,
                    Utils.formatCustomDate(Date(iPreferencesRepository.altcoinUpdateTime))))
        }
        if (iPreferencesRepository.cryptocurrencyUpdateTime > 0) {
            cryptocurrencyUpdateTime.set(resources.getString(R.string.activity_list_update,
                    Utils.formatCustomDate(Date(iPreferencesRepository.cryptocurrencyUpdateTime))))
        }
    }

    fun clickInfo() {
        startActivity(Intent(context, InfoActivity::class.java))
    }

    fun clickGpuMining() {
        val intent = Intent(context, CoinListActivity::class.java)
        intent.putExtra("action.type", Action.GPU.name)
        startActivity(intent)
    }

    fun clickAsicMining() {
        val intent = Intent(context, CoinListActivity::class.java)
        intent.putExtra("action.type", Action.ASIC.name)
        startActivity(intent)
    }

    fun clickAltCoin() {
        val intent = Intent(context, CoinListActivity::class.java)
        intent.putExtra("action.type", Action.WARZ.name)
        startActivity(intent)
    }

    fun clickCryptoCurrency() {
        val intent = Intent(context, CoinListActivity::class.java)
        intent.putExtra("action.type", Action.CRYPTOCURRENCY)
        startActivity(intent)
    }

    fun clickProfitability() {
        startActivity(Intent(context, ProfitabilityActivity::class.java))
    }

    private fun startActivity(intent: Intent) {
        activityNavigator.startActivity(intent)
    }
}