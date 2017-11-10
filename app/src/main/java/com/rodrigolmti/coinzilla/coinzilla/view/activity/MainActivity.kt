package com.rodrigolmti.coinzilla.coinzilla.view.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
import com.google.android.gms.ads.AdRequest
import com.rodrigolmti.coinzilla.R
import com.rodrigolmti.coinzilla.coinzilla.model.dao.Preferences
import com.rodrigolmti.coinzilla.coinzilla.model.presenter.Presenter
import com.rodrigolmti.coinzilla.library.app.CZApplication
import com.rodrigolmti.coinzilla.library.controller.mvp.BasePresenter
import com.rodrigolmti.coinzilla.library.controller.mvp.BaseView
import com.rodrigolmti.coinzilla.library.util.Action
import com.rodrigolmti.coinzilla.library.util.Utils
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_error.*
import java.util.*

class MainActivity : Activity(), View.OnClickListener, BaseView {

    private val presenter: BasePresenter = Presenter(this, this)
    private val czPreferences: Preferences? = CZApplication.preferences
    private lateinit var token: String
    private val utils: Utils = Utils()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initAds()

        val dateString: String = czPreferences!!.tokenDate
        token = czPreferences.token

        if (token == "noData" || dateString == "noData") {
            content.visibility = View.GONE
            presenter.getToken()
        }

        containerProfitability.setOnClickListener(this)
        containerCryptocurrency.setOnClickListener(this)
        containerBalance.setOnClickListener(this)
        containerAsic.setOnClickListener(this)
        containerWarz.setOnClickListener(this)
        containerGpu.setOnClickListener(this)
        containeInfo.setOnClickListener(this)
    }

    override fun onResume() {
        super.onResume()

        if (CZApplication.preferences!!.updateDateGpu != "noData") {
            textViewUpdateTimeGpu.text = getString(R.string.activity_list_update, utils.formatCustomDate(utils.stringToDate(CZApplication.preferences!!.updateDateGpu)!!))
            circle1.visibility = View.VISIBLE
        }

        if (CZApplication.preferences!!.updateDateAsic != "noData") {
            textViewUpdateTimeAsic.text = getString(R.string.activity_list_update, utils.formatCustomDate(utils.stringToDate(CZApplication.preferences!!.updateDateAsic)!!))
            circle2.visibility = View.VISIBLE
        }

        if (CZApplication.preferences!!.updateDateWarz != "noData") {
            textViewUpdateTimeWarz.text = getString(R.string.activity_list_update, utils.formatCustomDate(utils.stringToDate(CZApplication.preferences!!.updateDateWarz)!!))
            circle3.visibility = View.VISIBLE
        }

        if (CZApplication.preferences!!.updateDateCryptoCurrency != "noData") {
            textViewUpdateTimeCryptoCurrency.text = getString(R.string.activity_list_update, utils.formatCustomDate(utils.stringToDate(CZApplication.preferences!!.updateDateCryptoCurrency)!!))
            circle4.visibility = View.VISIBLE
        }

        if (checkTime()) {
            content.visibility = View.GONE
            presenter.getToken()
        }
    }

    override fun showProgressBar(visibility: Int) {
        progressBar.visibility = visibility
    }

    override fun success(action: Action) {
        content.visibility = View.VISIBLE
    }

    override fun success(result: List<Any>) {
    }

    override fun error(action: Action) {
    }

    override fun error(message: String) {
        if (token == "noData") {
            contentError.visibility = View.VISIBLE
            content.visibility = View.GONE
        }

        if (!Utils().isDeviceOnline(this)) {
            textViewErro.text = getString(R.string.general_error_connection)
            imageViewErro.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_offline))
        }
    }

    override fun onClick(view: View?) {
        if (view != null) {
            var intent = Intent(this, ListActivity::class.java)
            when (view) {
                containerCryptocurrency -> intent.putExtra("action.type", Action.CRYPTOCURRENCY.name)
                containerProfitability -> intent = Intent(this, ProfitabilityActivity::class.java)
                containerBalance -> intent = Intent(this, BalanceActivity::class.java)
                containerAsic -> intent.putExtra("action.type", Action.ASIC.name)
                containerWarz -> intent.putExtra("action.type", Action.WARZ.name)
                containerGpu -> intent.putExtra("action.type", Action.GPU.name)
                containeInfo -> intent = Intent(this, InfoActivity::class.java)
                clickViewBack -> intent = Intent(this, InfoActivity::class.java)
            }
            startActivity(intent)
        }
    }

    private fun checkTime(): Boolean {
        if (czPreferences!!.tokenDate != "noData") {
            val calendar: Calendar = Calendar.getInstance()
            calendar.time = utils.stringToDate(czPreferences.tokenDate)
            calendar.add(Calendar.MINUTE, +1000)
            val dateAfter: Date = calendar.time
            return Date().after(dateAfter)
        }
        return false
    }

    private fun initAds() {
        val adRequest = AdRequest.Builder()
                .addTestDevice(getString(R.string.admob_test_device_genymotion))
                .addTestDevice(getString(R.string.admob_test_device_one_plus))
                .addTestDevice(getString(R.string.admob_test_device_s7))
                .build()
        adView.loadAd(adRequest)
    }
}
