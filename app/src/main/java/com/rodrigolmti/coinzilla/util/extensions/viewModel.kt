package com.rodrigolmti.coinzilla.util.extensions

import android.os.Bundle
import com.rodrigolmti.coinzilla.ui.base.view.MvvmView
import com.rodrigolmti.coinzilla.ui.base.viewModel.MvvmViewModel

fun <V : MvvmView> MvvmViewModel<V>.attachViewOrThrowRuntimeException(view: MvvmView, savedInstanceState: Bundle?) {
    try {
        @Suppress("UNCHECKED_CAST")
        this.attachView(view as V, savedInstanceState)
    } catch (e: ClassCastException) {
        throw RuntimeException(javaClass.simpleName + " must implement MvvmView subclass as declared in " + this.javaClass.simpleName)
    }
}
