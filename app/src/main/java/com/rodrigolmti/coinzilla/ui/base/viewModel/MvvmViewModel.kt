package com.rodrigolmti.coinzilla.ui.base.viewModel

import android.databinding.Observable
import android.os.Bundle
import com.rodrigolmti.coinzilla.ui.base.view.MvvmView

interface MvvmViewModel<V : MvvmView> : Observable {

    fun attachView(view: V, savedInstanceState: Bundle?)

    fun saveInstanceState(outState: Bundle)

    fun detachView()
}
