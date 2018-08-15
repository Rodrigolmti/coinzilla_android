package com.rodrigolmti.coinzilla.ui.info

import android.os.Bundle
import com.rodrigolmti.coinzilla.R
import com.rodrigolmti.coinzilla.databinding.ActivityInfoBinding
import com.rodrigolmti.coinzilla.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_info.*

class InfoActivity : BaseActivity<ActivityInfoBinding, InfoViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setAndBindContentView(savedInstanceState, R.layout.activity_info)
        initAds(adView)
    }
}