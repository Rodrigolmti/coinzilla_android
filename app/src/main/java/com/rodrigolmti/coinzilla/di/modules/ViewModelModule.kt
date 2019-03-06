package com.rodrigolmti.coinzilla.di.modules

import com.rodrigolmti.coinzilla.ui.balance.BalanceViewModel
import com.rodrigolmti.coinzilla.ui.base.viewModel.BaseViewModel
import com.rodrigolmti.coinzilla.ui.coinDetail.CoinDetailViewModel
import com.rodrigolmti.coinzilla.ui.coinDetail.chart.CoinChartViewModel
import com.rodrigolmti.coinzilla.ui.info.InfoViewModel
import com.rodrigolmti.coinzilla.ui.list.CoinListViewModel
import com.rodrigolmti.coinzilla.ui.main.MainActivityViewModel
import com.rodrigolmti.coinzilla.ui.profitability.ProfitabilityViewModel
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindMainActivityViewModel(mainActivityViewModel: MainActivityViewModel): BaseViewModel

    @Binds
    internal abstract fun bindCoinListActivityViewModel(coinListViewModel: CoinListViewModel): BaseViewModel

    @Binds
    internal abstract fun bindInfoActivityViewModel(infoViewModel: InfoViewModel): BaseViewModel

    @Binds
    internal abstract fun bindProfitabilityActivityViewModel(profitabilityViewModel: ProfitabilityViewModel): BaseViewModel

    @Binds
    internal abstract fun bindCoinDetailyActivityViewModel(coinDetailViewModel: CoinDetailViewModel): BaseViewModel

    @Binds
    internal abstract fun bindCoinChartFragmentViewModel(coinChartDataViewModel: CoinChartViewModel): BaseViewModel

    @Binds
    internal abstract fun bindBalanceActivityViewModel(balanceViewModel: BalanceViewModel): BaseViewModel
}
