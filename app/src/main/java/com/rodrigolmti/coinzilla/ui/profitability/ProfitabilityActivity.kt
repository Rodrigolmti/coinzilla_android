package com.rodrigolmti.coinzilla.ui.profitability

import android.os.Bundle
import com.rodrigolmti.coinzilla.R
import com.rodrigolmti.coinzilla.databinding.ActivityProfitabilityBinding
import com.rodrigolmti.coinzilla.ui.base.BaseActivity

class ProfitabilityActivity : BaseActivity<ActivityProfitabilityBinding, ProfitabilityViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setAndBindContentView(savedInstanceState, R.layout.activity_profitability)
        title = getString(R.string.activity_profitability_title)
        enableBackButton()
    }
}