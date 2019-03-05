package com.rodrigolmti.coinzilla.ui.base.viewModel

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.databinding.BaseObservable
import com.rodrigolmti.coinzilla.ui.base.view.MvvmView
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel<V : MvvmView> : BaseObservable(), MvvmViewModel<V> {

    protected val compositeDisposable = CompositeDisposable()

    var view: V? = null
        private set

    @CallSuper
    override fun attachView(view: V, savedInstanceState: Bundle?) {
        this.view = view
        if (savedInstanceState != null) {
            restoreInstanceState(savedInstanceState)
        }
    }

    @CallSuper
    override fun detachView() {
        compositeDisposable.dispose()
        view = null
    }

    protected open fun restoreInstanceState(savedInstanceState: Bundle) {}

    override fun saveInstanceState(outState: Bundle) {}
}