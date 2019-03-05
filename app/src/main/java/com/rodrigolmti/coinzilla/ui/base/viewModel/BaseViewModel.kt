package com.rodrigolmti.coinzilla.ui.base.viewModel

import androidx.annotation.CallSuper
import androidx.databinding.BaseObservable
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : BaseObservable(), MvvmViewModel {

    protected val compositeDisposable = CompositeDisposable()

    @CallSuper
    override fun attachView() {

    }

    @CallSuper
    override fun detachView() {
        compositeDisposable.dispose()
    }
}