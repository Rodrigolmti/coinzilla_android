package com.rodrigolmti.coinzilla.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.rodrigolmti.coinzilla.BR
import com.rodrigolmti.coinzilla.di.components.DaggerFragmentComponent
import com.rodrigolmti.coinzilla.di.components.FragmentComponent
import com.rodrigolmti.coinzilla.di.modules.FragmentModule
import com.rodrigolmti.coinzilla.di.scopes.PerFragment
import com.rodrigolmti.coinzilla.ui.base.view.MvvmView
import com.rodrigolmti.coinzilla.ui.base.viewModel.MvvmViewModel
import com.rodrigolmti.coinzilla.util.exceptions.RtfmException
import com.rodrigolmti.coinzilla.util.extensions.attachViewOrThrowRuntimeException
import com.squareup.leakcanary.RefWatcher
import javax.inject.Inject

abstract class BaseFragment<B : ViewDataBinding, VM : MvvmViewModel<*>> : androidx.fragment.app.Fragment(), MvvmView {

    protected lateinit var binding: B

    @Inject
    protected lateinit var viewModel: VM

    @Inject
    protected lateinit var refWatcher: RefWatcher

    internal val fragmentComponent: FragmentComponent by lazy {
        DaggerFragmentComponent.builder()
                .fragmentModule(FragmentModule(this))
                .activityComponent((activity as BaseActivity<*, *>).activityComponent)
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
            FragmentComponent::class.java.getDeclaredMethod("inject", this::class.java).invoke(fragmentComponent, this)
        } catch (e: NoSuchMethodException) {
            throw RtfmException("You forgot to add \"fun inject(fragment: ${this::class.java.simpleName})\" in FragmentComponent")
        }
    }

    @CallSuper
    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.detachView()
        if (!viewModel.javaClass.isAnnotationPresent(PerFragment::class.java)) {
            refWatcher.watch(viewModel)
        }
    }

    @CallSuper
    override fun onDestroy() {
        super.onDestroy()
        refWatcher.watch(this)
        refWatcher.watch(fragmentComponent)
    }

    protected fun setAndBindContentView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?, @LayoutRes layoutResID: Int): View {
        binding = DataBindingUtil.inflate<B>(inflater!!, layoutResID, container, false)
        binding.setVariable(BR.viewModel, viewModel)
        viewModel.attachViewOrThrowRuntimeException(this, savedInstanceState)
        return binding.root
    }
}
