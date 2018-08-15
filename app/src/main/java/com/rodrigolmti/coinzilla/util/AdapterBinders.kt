package com.rodrigolmti.coinzilla.util

import android.databinding.BindingAdapter
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner

@BindingAdapter("android:visibility")
fun setVisibility(view: View, isVisible: Boolean) {
    view.visibility = if (isVisible) View.VISIBLE else View.GONE
}

@BindingAdapter("itemSelectedListener")
fun addScrollStateChangeListener(spinner: Spinner, onItemSelectedListener: AdapterView.OnItemSelectedListener) {
    spinner.onItemSelectedListener = onItemSelectedListener
}