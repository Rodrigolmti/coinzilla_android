package com.rodrigolmti.coinzilla.coinzilla.view.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import com.rodrigolmti.coinzilla.R
import com.rodrigolmti.coinzilla.coinzilla.model.api.service.CoinZillaService
import com.rodrigolmti.coinzilla.coinzilla.model.callback.BaseCallBack
import com.rodrigolmti.coinzilla.coinzilla.model.entity.CryptoCurrency
import com.rodrigolmti.coinzilla.coinzilla.view.adapter.CryptoCurrencyAdapter
import com.rodrigolmti.coinzilla.coinzilla.view.adapter.WhatToMineAdapter
import com.rodrigolmti.coinzilla.coinzilla.view.extensions.gone
import com.rodrigolmti.coinzilla.coinzilla.view.extensions.visible
import com.rodrigolmti.coinzilla.library.controller.activity.BaseActivity
import com.rodrigolmti.coinzilla.library.util.Action
import kotlinx.android.synthetic.main.activity_list.contentError
import kotlinx.android.synthetic.main.activity_list.progressBar
import kotlinx.android.synthetic.main.activity_list.recyclerView
import kotlinx.android.synthetic.main.activity_list.toolbar

class ListActivity : BaseActivity() {

    private lateinit var adapterCryptoCurrency: CryptoCurrencyAdapter
    private lateinit var adapterWhatToMine: WhatToMineAdapter
    private lateinit var coinZillaService: CoinZillaService
    private var resultList: List<Any> = ArrayList()
    private lateinit var action: Action

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        coinZillaService = CoinZillaService(this)
        setSupportActionBar(toolbar)
        enableBackButton()

        if (intent.hasExtra("action.type")) {

            action = Action.valueOf(intent.getStringExtra("action.type"))

            progressBar.visible()
            recyclerView.gone()

            when (action) {
                Action.GPU -> {
                    coinZillaService.getWhatToMineGpu(callBack)
                    title = getString(R.string.activity_list_gpu_title)
                }
                Action.ASIC -> {
                    coinZillaService.getWhatToMineAsic(callBack)
                    title = getString(R.string.activity_list_asic_title)
                }
                Action.WARZ -> {
                    coinZillaService.getWhatToMineWarz(callBack)
                    title = getString(R.string.activity_main_warz_title)
                }
                Action.CRYPTOCURRENCY -> {
                    coinZillaService.getCryptoCurrency(callBack)
                    title = getString(R.string.activity_main_cryptocurrency_title)
                }
                else -> {
                    contentError.visible()
                    recyclerView.gone()
                }
            }
        }
    }

    private val callBack: BaseCallBack = object: BaseCallBack() {
        override fun onSuccess() {

            recyclerView.visible()
            progressBar.gone()

            recyclerView.layoutManager = LinearLayoutManager(this@ListActivity)
            recyclerView.hasFixedSize()

            when (action) {
                Action.GPU -> {
                    resultList = coinZillaService.whatToMineGpuLocal()
                    adapterWhatToMine = WhatToMineAdapter(this@ListActivity, ArrayList(resultList))
                    recyclerView.adapter = adapterWhatToMine
                }
                Action.ASIC -> {
                    resultList = coinZillaService.whatToMineAsicLocal()
                    adapterWhatToMine = WhatToMineAdapter(this@ListActivity, ArrayList(resultList))
                    recyclerView.adapter = adapterWhatToMine
                }
                Action.WARZ -> {
                    resultList = coinZillaService.whatToMineWarzLocal()
                    adapterWhatToMine = WhatToMineAdapter(this@ListActivity, ArrayList(resultList))
                    recyclerView.adapter = adapterWhatToMine
                }
                Action.CRYPTOCURRENCY -> {
                    recyclerView.layoutManager = GridLayoutManager(this@ListActivity, 3)
                    val resultList = coinZillaService.cryptoCurrencyLocal()
                    adapterCryptoCurrency = CryptoCurrencyAdapter(this@ListActivity, ArrayList(resultList), object : CryptoCurrencyAdapter.OnItemClickListener {
                        override fun itemOnClick(item: CryptoCurrency) {
                            val intent = Intent(this@ListActivity, CoinDetailActivity::class.java)
                            intent.putExtra("action.coin.detail", item)
                            startActivity(intent)
                        }
                    })
                    recyclerView.adapter = adapterCryptoCurrency
                }
                else -> {
                    contentError.visible()
                    recyclerView.gone()
                }
            }
        }

        override fun onError() {
            contentError.visible()
            recyclerView.gone()
            progressBar.gone()
        }
    }


    //TODO: Fix search in all cryptocurrency
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        if (action != Action.CRYPTOCURRENCY) {
            menuInflater.inflate(R.menu.menu_list_home, menu)
            val myActionMenuItem = menu!!.findItem(R.id.action_search)
            val searchView = myActionMenuItem.actionView as SearchView
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    return false
                }

                override fun onQueryTextChange(query: String): Boolean {
                    if (adapterWhatToMine != null)
                        adapterWhatToMine.filter(query)
                    else
                        adapterCryptoCurrency.filter(query)
                    return true
                }
            })
        }

        return true
    }
}
