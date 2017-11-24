package com.rodrigolmti.coinzilla.library.controller.mvp

import com.rodrigolmti.coinzilla.library.util.Action

interface BaseView {

    fun showProgressBar(visibility: Int) {}
    fun success(action: Action) {}
    fun success(result: List<Any>) {}
    fun error(action: Action) {}
    fun error(message: String) {}
}