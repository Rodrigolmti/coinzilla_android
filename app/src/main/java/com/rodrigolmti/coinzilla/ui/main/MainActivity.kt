package com.rodrigolmti.coinzilla.ui.main

import android.os.Bundle
import com.rodrigolmti.coinzilla.R
import com.rodrigolmti.coinzilla.databinding.ActivityMainBinding
import com.rodrigolmti.coinzilla.ui.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding, MainActivityViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setAndBindContentView(savedInstanceState, R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        viewModel.updateTimeLabel()
    }
}
