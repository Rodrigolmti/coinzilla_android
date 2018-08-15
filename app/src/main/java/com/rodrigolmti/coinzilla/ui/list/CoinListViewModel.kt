package com.rodrigolmti.coinzilla.ui.list

import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.content.res.Resources
import android.databinding.ObservableBoolean
import com.rodrigolmti.coinzilla.R
import com.rodrigolmti.coinzilla.data.IRepository
import com.rodrigolmti.coinzilla.data.model.api.WtmAsicResponse
import com.rodrigolmti.coinzilla.data.model.api.WtmGpuResponse
import com.rodrigolmti.coinzilla.data.model.api.WtmWarzResponse
import com.rodrigolmti.coinzilla.di.qualifier.AppContext
import com.rodrigolmti.coinzilla.di.scopes.PerActivity
import com.rodrigolmti.coinzilla.library.util.Action
import com.rodrigolmti.coinzilla.ui.base.view.MvvmView
import com.rodrigolmti.coinzilla.ui.base.viewModel.BaseViewModel
import com.rodrigolmti.coinzilla.ui.main.MainMvvm
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
    : BaseViewModel<MvvmView>(), MainMvvm {

    val mutableGpuLiveData: MutableLiveData<List<WtmGpuResponse>> = MutableLiveData()
    val mutableAsicLiveData: MutableLiveData<List<WtmAsicResponse>> = MutableLiveData()
    val mutableWarzLiveData: MutableLiveData<List<WtmWarzResponse>> = MutableLiveData()

    val loading: ObservableBoolean = ObservableBoolean(false)
    val error: ObservableBoolean = ObservableBoolean(false)

    fun getDataByAction(action: Action) {

        loading.set(true)
        when (action) {
            Action.GPU -> {

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
            Action.ASIC -> {

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
            Action.WARZ -> {
            }
            Action.CRYPTOCURRENCY -> {
            }
            else -> {
                error.set(true)
            }
        }
    }
}