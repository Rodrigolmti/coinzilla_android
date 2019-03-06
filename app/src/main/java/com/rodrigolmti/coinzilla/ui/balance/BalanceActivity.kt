package com.rodrigolmti.coinzilla.ui.balance

import android.os.Bundle
import com.rodrigolmti.coinzilla.R
import com.rodrigolmti.coinzilla.databinding.ActivityBalanceBinding
import com.rodrigolmti.coinzilla.ui.base.BaseActivity

class BalanceActivity : BaseActivity<ActivityBalanceBinding, BalanceViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setAndBindContentView(R.layout.activity_balance)
    }
}