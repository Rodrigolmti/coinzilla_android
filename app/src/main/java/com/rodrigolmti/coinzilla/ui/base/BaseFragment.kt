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
import com.rodrigolmti.coinzilla.ui.base.viewModel.MvvmViewModel
import com.rodrigolmti.coinzilla.util.RtfmException
import javax.inject.Inject

abstract class BaseFragment<B : ViewDataBinding, VM : MvvmViewModel> : androidx.fragment.app.Fragment() {

    protected lateinit var binding: B

    @Inject
    protected lateinit var viewModel: VM

    internal val fragmentComponent: FragmentComponent by lazy {
        DaggerFragmentComponent.builder()
                .fragmentModule(FragmentModule(this))
                .activityComponent((activity as BaseActivity<*, *>).activityComponent)
                .build()
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
    }

    protected fun setAndBindContentView(inflater: LayoutInflater?, container: ViewGroup?, @LayoutRes layoutResID: Int): View {
        binding = DataBindingUtil.inflate(inflater!!, layoutResID, container, false)
        binding.setVariable(BR.viewModel, viewModel)
        viewModel.attachView()
        return binding.root
    }
}
