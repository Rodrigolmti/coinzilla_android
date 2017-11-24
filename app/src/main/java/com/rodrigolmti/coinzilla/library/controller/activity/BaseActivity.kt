package com.rodrigolmti.coinzilla.library.controller.activity

import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.rodrigolmti.coinzilla.R

abstract class BaseActivity : AppCompatActivity() {

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }

    protected fun enableBackButton() {
        supportActionBar?.let {
            supportActionBar!!.setHomeButtonEnabled(true)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }
    }

    protected fun removeElevation() {
        supportActionBar?.let {
            supportActionBar!!.elevation = 0F
        }
    }

    protected fun initAds(adView: AdView) {
        adView.loadAd(AdRequest.Builder()
                .addTestDevice(getString(R.string.admob_test_device_genymotion))
                .addTestDevice(getString(R.string.admob_test_device_one_plus))
                .addTestDevice(getString(R.string.admob_test_device_lenovo))
                .build())
    }
}
