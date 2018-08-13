package com.rodrigolmti.coinzilla.ui.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.rodrigolmti.coinzilla.BR
import com.rodrigolmti.coinzilla.CZApplication
import com.rodrigolmti.coinzilla.R
import com.rodrigolmti.coinzilla.di.components.ActivityComponent
import com.rodrigolmti.coinzilla.di.components.DaggerActivityComponent
import com.rodrigolmti.coinzilla.di.modules.ActivityModule
import com.rodrigolmti.coinzilla.ui.base.view.MvvmView
import com.rodrigolmti.coinzilla.ui.base.viewModel.MvvmViewModel
import com.rodrigolmti.coinzilla.util.exceptions.RtfmException
import com.rodrigolmti.coinzilla.util.extensions.attachViewOrThrowRuntimeException
import com.squareup.leakcanary.RefWatcher
import io.realm.Realm
import javax.inject.Inject

abstract class BaseActivity<B : ViewDataBinding, VM : MvvmViewModel<*>> : AppCompatActivity(), MvvmView {

    @Inject
    protected lateinit var realm: Realm

    protected lateinit var binding: B
    @Inject
    protected lateinit var viewModel: VM

    @Inject
    protected lateinit var refWatcher: RefWatcher

    internal val activityComponent: ActivityComponent by lazy {
        DaggerActivityComponent.builder()
                .activityModule(ActivityModule(this))
                .appComponent(CZApplication.appComponent)
                .build()
    }

    @CallSuper
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        viewModel.saveInstanceState(outState)
    }

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            ActivityComponent::class.java.getDeclaredMethod("inject", this::class.java).invoke(activityComponent, this)
        } catch (e: NoSuchMethodException) {
            throw RtfmException("You forgot to add \"fun inject(activity: ${this::class.java.simpleName})\" in ActivityComponent")
        }
    }

    @CallSuper
    override fun onDestroy() {
        super.onDestroy()
        viewModel.detachView()
        refWatcher.watch(activityComponent)
        refWatcher.watch(viewModel)
        realm.close()
    }

    protected fun initAds(adView: AdView) {
        adView.loadAd(AdRequest.Builder()
                .addTestDevice(getString(R.string.admob_test_device_genymotion))
                .addTestDevice(getString(R.string.admob_test_device_one_plus))
                .addTestDevice(getString(R.string.admob_test_device_lenovo))
                .build())
    }

    protected fun setAndBindContentView(savedInstanceState: Bundle?, @LayoutRes layoutResID: Int) {
        binding = DataBindingUtil.setContentView(this, layoutResID)
        binding.setVariable(BR.viewModel, viewModel)
        viewModel.attachViewOrThrowRuntimeException(this, savedInstanceState)
    }
}
