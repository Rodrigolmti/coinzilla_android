package com.rodrigolmti.coinzilla.ui.base.viewModel

import androidx.databinding.Observable

interface MvvmViewModel : Observable {

    fun attachView()

    fun detachView()
}
