package com.rodrigolmti.coinzilla.di.modules

import com.rodrigolmti.coinzilla.ui.coinDetail.CoinDetailMvvm
import com.rodrigolmti.coinzilla.ui.coinDetail.CoinDetailViewModel
import com.rodrigolmti.coinzilla.ui.coinDetail.chart.CoinChartDataMvvm
import com.rodrigolmti.coinzilla.ui.coinDetail.chart.CoinChartDataViewModel
import com.rodrigolmti.coinzilla.ui.info.InfoMvvm
import com.rodrigolmti.coinzilla.ui.info.InfoViewModel
import com.rodrigolmti.coinzilla.ui.list.CoinListMvvm
import com.rodrigolmti.coinzilla.ui.list.CoinListViewModel
import com.rodrigolmti.coinzilla.ui.main.MainActivityViewModel
import com.rodrigolmti.coinzilla.ui.main.MainMvvm
import com.rodrigolmti.coinzilla.ui.profitability.ProfitabilityMvvm
import com.rodrigolmti.coinzilla.ui.profitability.ProfitabilityViewModel
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindMainActivityViewModel(mainActivityViewModel: MainActivityViewModel): MainMvvm

    @Binds
    internal abstract fun bindCoinListActivityViewModel(coinListViewModel: CoinListViewModel): CoinListMvvm

    @Binds
    internal abstract fun bindInfoActivityViewModel(infoViewModel: InfoViewModel): InfoMvvm

    @Binds
    internal abstract fun bindProfitabilityActivityViewModel(profitabilityViewModel: ProfitabilityViewModel): ProfitabilityMvvm

    @Binds
    internal abstract fun bindCoinDetailyActivityViewModel(coinDetailViewModel: CoinDetailViewModel): CoinDetailMvvm

    @Binds
    internal abstract fun bindCoinChartFragmentViewModel(coinChartDataViewModel: CoinChartDataViewModel): CoinChartDataMvvm
}
