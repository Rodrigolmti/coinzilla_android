package com.rodrigolmti.coinzilla.coinzilla.view.activity

import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.rodrigolmti.coinzilla.R
import com.rodrigolmti.coinzilla.coinzilla.model.entity.CryptoCurrency
import com.rodrigolmti.coinzilla.coinzilla.model.presenter.Presenter
import com.rodrigolmti.coinzilla.coinzilla.view.adapter.CryptoCurrencyAdapter
import com.rodrigolmti.coinzilla.coinzilla.view.adapter.WhatToMineAdapter
import com.rodrigolmti.coinzilla.coinzilla.view.extensions.gone
import com.rodrigolmti.coinzilla.coinzilla.view.extensions.visible
import com.rodrigolmti.coinzilla.library.controller.activity.BaseActivity
import com.rodrigolmti.coinzilla.library.controller.mvp.BasePresenter
import com.rodrigolmti.coinzilla.library.controller.mvp.BaseView
import com.rodrigolmti.coinzilla.library.util.Action
import com.rodrigolmti.coinzilla.library.util.Utils
import kotlinx.android.synthetic.main.activity_list.contentError
import kotlinx.android.synthetic.main.activity_list.progressBar
import kotlinx.android.synthetic.main.activity_list.recyclerView
import kotlinx.android.synthetic.main.activity_list.toolbar
import kotlinx.android.synthetic.main.layout_error.imageViewErro
import kotlinx.android.synthetic.main.layout_error.textViewErro

class ListActivity : BaseActivity(), BaseView {

    private val presenter: BasePresenter = Presenter(this, this)
    private lateinit var adapterCryptoCurrency: CryptoCurrencyAdapter
    private lateinit var adapterWhatToMine: WhatToMineAdapter
    private var resultList: List<Any> = ArrayList()
    private lateinit var action: Action

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        setSupportActionBar(toolbar)
        enableBackButton()

        if (intent.hasExtra("action.type")) {
            action = Action.valueOf(intent.getStringExtra("action.type"))
            recyclerView.visibility = View.GONE
            progressBar.visibility = View.VISIBLE
            when (action) {
                Action.GPU -> {
                    presenter.whatToMineGpuWeb()
                    title = getString(R.string.activity_list_gpu_title)
                }
                Action.ASIC -> {
                    presenter.whatToMineAsicWeb()
                    title = getString(R.string.activity_list_asic_title)
                }
                Action.WARZ -> {
                    presenter.whatToMineWarzWeb()
                    title = getString(R.string.activity_main_warz_title)
                }
                Action.CRYPTOCURRENCY -> {
                    presenter.cryptoCurrencyWeb()
                    title = getString(R.string.activity_main_cryptocurrency_title)
                }
                else -> {
                    recyclerView.visibility = View.GONE
                    contentError.visibility = View.VISIBLE
                }
            }
        }

        title = title
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

    override fun success(action: Action) {
        recyclerView.visibility = View.VISIBLE
        progressBar.visibility = View.GONE

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.hasFixedSize()

        when (action) {
            Action.GPU -> {
                resultList = presenter.whatToMineGpuLocal()
                adapterWhatToMine = WhatToMineAdapter(this, ArrayList(resultList))
                recyclerView.adapter = adapterWhatToMine
            }
            Action.ASIC -> {
                resultList = presenter.whatToMineAsicLocal()
                adapterWhatToMine = WhatToMineAdapter(this, ArrayList(resultList))
                recyclerView.adapter = adapterWhatToMine
            }
            Action.WARZ -> {
                resultList = presenter.whatToMineWarzLocal()
                adapterWhatToMine = WhatToMineAdapter(this, ArrayList(resultList))
                recyclerView.adapter = adapterWhatToMine
            }
            Action.CRYPTOCURRENCY -> {
                recyclerView.layoutManager = GridLayoutManager(this, 3)
                val resultList = presenter.cryptoCurrencyLocal()
                adapterCryptoCurrency = CryptoCurrencyAdapter(this, ArrayList(resultList), object : CryptoCurrencyAdapter.onItemClickListener {
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

    override fun success(result: List<Any>) {
    }

    override fun error(action: Action) {
    }

    override fun error(message: String) {
        contentError.visible()
        recyclerView.gone()
        progressBar.gone()

        if (!Utils().isDeviceOnline(this)) {
            textViewErro.text = getString(R.string.general_error_connection)
            imageViewErro.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_offline))
        }
    }

    override fun showProgressBar(visibility: Int) {
        progressBar.visible()
    }
}
