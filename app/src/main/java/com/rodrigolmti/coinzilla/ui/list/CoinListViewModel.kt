package com.rodrigolmti.coinzilla.ui.list

import android.content.Context
import android.content.res.Resources
import android.databinding.ObservableBoolean
import com.rodrigolmti.coinzilla.R
import com.rodrigolmti.coinzilla.data.local.IPreferencesRepository
import com.rodrigolmti.coinzilla.data.remote.INodeApi
import com.rodrigolmti.coinzilla.di.qualifier.AppContext
import com.rodrigolmti.coinzilla.di.scopes.PerActivity
import com.rodrigolmti.coinzilla.library.util.Action
import com.rodrigolmti.coinzilla.ui.base.navigation.IActivityNavigator
import com.rodrigolmti.coinzilla.ui.base.view.MvvmView
import com.rodrigolmti.coinzilla.ui.base.viewModel.BaseViewModel
import com.rodrigolmti.coinzilla.ui.main.MainMvvm
import javax.inject.Inject

@PerActivity
class CoinListViewModel @Inject constructor(@AppContext val context: Context,
                                            private val resources: Resources,
                                            private val iRemoteApi: INodeApi,
                                            private val activityNavigator: IActivityNavigator,
                                            private val iPreferencesRepository: IPreferencesRepository)
    : BaseViewModel<MvvmView>(), MainMvvm {

    val loading: ObservableBoolean = ObservableBoolean(false)

    init {

    }

    fun getDataByAction(action: Action) {

        loading.set(true)

//        when (action) {
//            Action.GPU -> {
//                coinZillaService.getWhatToMineGpu(callBack)
//                title = getString(R.string.activity_list_gpu_title)
//            }
//            Action.ASIC -> {
//                coinZillaService.getWhatToMineAsic(callBack)
//                title = getString(R.string.activity_list_asic_title)
//            }
//            Action.WARZ -> {
//                coinZillaService.getWhatToMineWarz(callBack)
//                title = getString(R.string.activity_main_warz_title)
//            }
//            Action.CRYPTOCURRENCY -> {
//                coinZillaService.getCryptoCurrency(callBack)
//                title = getString(R.string.activity_main_cryptocurrency_title)
//            }
//            else -> {
//                contentError.visible()
//                recyclerView.gone()
//            }
//        }
    }
}