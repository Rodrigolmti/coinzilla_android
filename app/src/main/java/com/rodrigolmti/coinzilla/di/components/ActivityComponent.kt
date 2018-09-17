package com.rodrigolmti.coinzilla.di.components

import android.content.Context
import androidx.fragment.app.FragmentManager
import com.rodrigolmti.coinzilla.di.modules.ActivityModule
import com.rodrigolmti.coinzilla.di.modules.ViewModelModule
import com.rodrigolmti.coinzilla.di.qualifier.ActivityContext
import com.rodrigolmti.coinzilla.di.qualifier.ActivityFragmentManager
import com.rodrigolmti.coinzilla.di.scopes.PerActivity
import com.rodrigolmti.coinzilla.ui.base.navigation.IActivityNavigator
import com.rodrigolmti.coinzilla.ui.coinDetail.CoinDetailActivity
import com.rodrigolmti.coinzilla.ui.info.InfoActivity
import com.rodrigolmti.coinzilla.ui.list.CoinListActivity
import com.rodrigolmti.coinzilla.ui.main.MainActivity
import com.rodrigolmti.coinzilla.ui.profitability.ProfitabilityActivity
import dagger.Component

@PerActivity
@Component(dependencies = [AppComponent::class], modules = [ActivityModule::class, ViewModelModule::class])
interface ActivityComponent : ActivityComponentProvides {

    fun inject(activity: MainActivity)

    fun inject(activity: CoinDetailActivity)

    fun inject(activity: InfoActivity)

    fun inject(activity: CoinListActivity)

    fun inject(activity: ProfitabilityActivity)
}

interface ActivityComponentProvides : AppComponentProvides {

    @ActivityContext
    fun activityContext(): Context

    @ActivityFragmentManager
    fun defaultFragmentManager(): androidx.fragment.app.FragmentManager

    fun navigator(): IActivityNavigator
}