package com.rodrigolmti.coinzilla.coinzilla.view.activity

import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.BaseAdapter
import com.crashlytics.android.Crashlytics
import com.google.android.gms.ads.AdRequest
import com.rodrigolmti.coinzilla.R
import com.rodrigolmti.coinzilla.coinzilla.view.extensions.visible
import com.rodrigolmti.coinzilla.library.controller.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_profitability.*
import java.text.DecimalFormat

class ProfitabilityActivity : BaseActivity(), View.OnClickListener {

    var difficultyMultiplier = 1F
    var hashMultiplier = 1F

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profitability)

        title = getString(R.string.activity_profitability_title)
        enableBackButton()
        initAds(adView)

        spinnerCalcHash.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
                val type = parent.getItemAtPosition(pos).toString()
                hashMultiplier = when (type) {
                    getString(R.string.activity_profitability_hs) -> 1F
                    getString(R.string.activity_profitability_khs) -> 1000F
                    getString(R.string.activity_profitability_mhs) -> 1000000F
                    getString(R.string.activity_profitability_ghs) -> 1000000000F
                    else -> 1F
                }
            }

            override fun onNothingSelected(parent: AdapterView<out Adapter>?) {
                hashMultiplier = 1F
            }
        }

        spinnerCalcType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
                val type = parent.getItemAtPosition(pos).toString()
                hashMultiplier = when (type) {
                    getString(R.string.activity_profitability_ethereum) -> 1F
                    getString(R.string.activity_profitability_cryptonode) -> 1F
                    getString(R.string.activity_profitability_bitcoin) -> Math.pow(2.0, 32.0).toFloat()
                    getString(R.string.activity_profitability_zcash) -> Math.pow(2.0, 13.0).toFloat()
                    else -> 1F
                }
            }

            override fun onNothingSelected(parent: AdapterView<out Adapter>?) {
                difficultyMultiplier = 1F
            }
        }

        buttonCalc.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        try {
            var error = 0
            var fee = 1F
            if (editTextHash.text.isNullOrBlank()) {
                editTextHash.error = getString(R.string.activity_profitability_message)
                error++
            }
            if (editTextReward.text.isNullOrBlank()) {
                editTextReward.error = getString(R.string.activity_profitability_message)
                error++
            }
            if (editTextDifficulty.text.isNullOrBlank()) {
                editTextDifficulty.error = getString(R.string.activity_profitability_message)
                error++
            }
            if (!editTextFee.text.isNullOrBlank()) {
                fee = editTextFee.text.toString().toFloat()
            }

            if (error == 0) {
                val hash = editTextHash.text.toString().toFloat()
                val reward = editTextReward.text.toString().toFloat()
                val difficulty = editTextDifficulty.text.toString().toFloat()
                layerResult.visible()

                val decimalFormat = DecimalFormat("0.00000000")
                val blockMined = ((hash * hashMultiplier) * reward) / (difficulty * difficultyMultiplier) * fee

                val day = blockMined * 86400
                val month = blockMined * 2.628e+6
                val year = blockMined * 31535965.4396976

                textViewResultDay.text = decimalFormat.format(day).toString()
                textViewResultMonth.text = decimalFormat.format(month).toString()
                textViewResultYear.text = decimalFormat.format(year).toString()
            }
        } catch (error: Exception) {
            Crashlytics.logException(error)
            error.stackTrace
        }
    }
}