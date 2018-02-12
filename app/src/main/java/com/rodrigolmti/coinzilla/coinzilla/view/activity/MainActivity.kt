package com.rodrigolmti.coinzilla.coinzilla.view.activity

import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
import com.rodrigolmti.coinzilla.R
import com.rodrigolmti.coinzilla.coinzilla.model.api.service.CoinZillaService
import com.rodrigolmti.coinzilla.coinzilla.model.callback.BaseCallBack
import com.rodrigolmti.coinzilla.coinzilla.model.dao.Preferences
import com.rodrigolmti.coinzilla.coinzilla.view.extensions.gone
import com.rodrigolmti.coinzilla.coinzilla.view.extensions.visible
import com.rodrigolmti.coinzilla.library.app.CZApplication
import com.rodrigolmti.coinzilla.library.controller.activity.BaseActivity
import com.rodrigolmti.coinzilla.library.util.Action
import com.rodrigolmti.coinzilla.library.util.Utils
import kotlinx.android.synthetic.main.activity_main.adView
import kotlinx.android.synthetic.main.activity_main.circle1
import kotlinx.android.synthetic.main.activity_main.circle2
import kotlinx.android.synthetic.main.activity_main.circle3
import kotlinx.android.synthetic.main.activity_main.circle4
import kotlinx.android.synthetic.main.activity_main.clickViewBack
import kotlinx.android.synthetic.main.activity_main.containeInfo
import kotlinx.android.synthetic.main.activity_main.containerAsic
import kotlinx.android.synthetic.main.activity_main.containerBalance
import kotlinx.android.synthetic.main.activity_main.containerCryptocurrency
import kotlinx.android.synthetic.main.activity_main.containerGpu
import kotlinx.android.synthetic.main.activity_main.containerProfitability
import kotlinx.android.synthetic.main.activity_main.containerWarz
import kotlinx.android.synthetic.main.activity_main.content
import kotlinx.android.synthetic.main.activity_main.contentError
import kotlinx.android.synthetic.main.activity_main.progressBar
import kotlinx.android.synthetic.main.activity_main.textViewUpdateTimeAsic
import kotlinx.android.synthetic.main.activity_main.textViewUpdateTimeCryptoCurrency
import kotlinx.android.synthetic.main.activity_main.textViewUpdateTimeGpu
import kotlinx.android.synthetic.main.activity_main.textViewUpdateTimeWarz
import kotlinx.android.synthetic.main.layout_error.imageViewErro
import kotlinx.android.synthetic.main.layout_error.textViewErro
import java.util.Calendar
import java.util.Date

class MainActivity : BaseActivity(), View.OnClickListener {

    private val czPreferences: Preferences? = CZApplication.preferences
    private lateinit var token: String
    private val utils: Utils = Utils()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        token = czPreferences!!.token

        getToken()

        containerProfitability.setOnClickListener(this)
        containerCryptocurrency.setOnClickListener(this)
        containerBalance.setOnClickListener(this)
        containerAsic.setOnClickListener(this)
        containerWarz.setOnClickListener(this)
        containerGpu.setOnClickListener(this)
        containeInfo.setOnClickListener(this)
        initAds(adView)
    }

    override fun onResume() {
        super.onResume()

        if (CZApplication.preferences!!.updateDateGpu != "noData") {
            textViewUpdateTimeGpu.text = getString(R.string.activity_list_update, utils.formatCustomDate(utils.stringToDate(CZApplication.preferences!!.updateDateGpu)!!))
            circle1.visible()
        }

        if (CZApplication.preferences!!.updateDateAsic != "noData") {
            textViewUpdateTimeAsic.text = getString(R.string.activity_list_update, utils.formatCustomDate(utils.stringToDate(CZApplication.preferences!!.updateDateAsic)!!))
            circle2.visible()
        }

        if (CZApplication.preferences!!.updateDateWarz != "noData") {
            textViewUpdateTimeWarz.text = getString(R.string.activity_list_update, utils.formatCustomDate(utils.stringToDate(CZApplication.preferences!!.updateDateWarz)!!))
            circle3.visible()
        }

        if (CZApplication.preferences!!.updateDateCryptoCurrency != "noData") {
            textViewUpdateTimeCryptoCurrency.text = getString(R.string.activity_list_update, utils.formatCustomDate(utils.stringToDate(CZApplication.preferences!!.updateDateCryptoCurrency)!!))
            circle4.visible()
        }

        getToken()
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

    private fun getToken() {
        if (!checkTime()) return
        if ((token != "noData" || czPreferences!!.tokenDate != "noData")) return
        if (Utils().isDeviceOnline(this)) {

            progressBar.visible()
            content.gone()

            CoinZillaService(this).getToken(object : BaseCallBack() {
                override fun onSuccess() {
                    content.visible()
                }

                override fun onError() {
                    contentError.visible()
                    content.gone()
                }
            })
        } else {
            imageViewErro.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_offline))
            textViewErro.text = getString(R.string.general_error_connection)
            contentError.visible()
            content.gone()
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
}
