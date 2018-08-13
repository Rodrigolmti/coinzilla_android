package com.rodrigolmti.coinzilla.di.modules

import android.content.Context
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import com.rodrigolmti.coinzilla.di.qualifier.ActivityContext
import com.rodrigolmti.coinzilla.di.qualifier.ActivityFragmentManager
import com.rodrigolmti.coinzilla.di.scopes.PerActivity
import com.rodrigolmti.coinzilla.ui.base.navigation.ActivityNavigator
import com.rodrigolmti.coinzilla.ui.base.navigation.IActivityNavigator
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: AppCompatActivity) {

    @Provides
    @PerActivity
    @ActivityContext
    internal fun provideActivityContext(): Context = activity

    @Provides
    @PerActivity
    @ActivityFragmentManager
    internal fun provideFragmentManager(): FragmentManager = activity.supportFragmentManager

    @Provides
    @PerActivity
    internal fun provideNavigator(): IActivityNavigator = ActivityNavigator(activity)
}
