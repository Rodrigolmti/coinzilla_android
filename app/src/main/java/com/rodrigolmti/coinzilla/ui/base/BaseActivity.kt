package com.rodrigolmti.coinzilla.ui.base

import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.rodrigolmti.coinzilla.BR
import com.rodrigolmti.coinzilla.CZApplication
import com.rodrigolmti.coinzilla.di.components.ActivityComponent
import com.rodrigolmti.coinzilla.di.components.DaggerActivityComponent
import com.rodrigolmti.coinzilla.di.modules.ActivityModule
import com.rodrigolmti.coinzilla.ui.base.navigation.IActivityNavigator
import com.rodrigolmti.coinzilla.ui.base.viewModel.MvvmViewModel
import com.rodrigolmti.coinzilla.util.RtfmException
import javax.inject.Inject

abstract class BaseActivity<B : ViewDataBinding, VM : MvvmViewModel> : AppCompatActivity() {

    @Inject
    protected lateinit var activityNavigator: IActivityNavigator

    @Inject
    protected lateinit var viewModel: VM

    protected lateinit var binding: B

    internal val activityComponent: ActivityComponent by lazy {
        DaggerActivityComponent.builder()
                .activityModule(ActivityModule(this))
                .appComponent(CZApplication.appComponent)
                .build()
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
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }

    protected fun enableBackButton() {
        supportActionBar?.let {
            supportActionBar!!.setHomeButtonEnabled(true)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }
    }

    protected fun removeElevation() {
        supportActionBar?.let {
            supportActionBar!!.elevation = 0F
        }
    }

    protected fun setAndBindContentView(@LayoutRes layoutResID: Int) {
        binding = DataBindingUtil.setContentView(this, layoutResID)
        binding.setVariable(BR.viewModel, viewModel)
        viewModel.attachView()
    }
}
