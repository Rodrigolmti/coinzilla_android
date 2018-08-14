package com.rodrigolmti.coinzilla.ui.list

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.rodrigolmti.coinzilla.R
import com.rodrigolmti.coinzilla.coinzilla.model.api.service.CoinZillaService
import com.rodrigolmti.coinzilla.coinzilla.model.callback.BaseCallBack
import com.rodrigolmti.coinzilla.coinzilla.model.dao.CoinDAO
import com.rodrigolmti.coinzilla.coinzilla.view.adapter.WhatToMineAdapter
import com.rodrigolmti.coinzilla.databinding.ActivityCoinListBinding
import com.rodrigolmti.coinzilla.library.util.Action
import com.rodrigolmti.coinzilla.ui.base.BaseActivity
import com.rodrigolmti.coinzilla.ui.main.MainActivityViewModel

class CoinListActivity : BaseActivity<ActivityCoinListBinding, CoinListViewModel>() {

    private var adapterWhatToMine: WhatToMineAdapter? = null
    private lateinit var coinZillaService: CoinZillaService
    private var resultList: MutableList<Any> = ArrayList()
    private val coinDao: CoinDAO = CoinDAO()
    private lateinit var action: Action

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setAndBindContentView(savedInstanceState, R.layout.activity_main)
        setSupportActionBar(binding.toolbar)
        enableBackButton()

        if (intent.hasExtra("action.type")) {
            viewModel.getDataByAction(Action.valueOf(intent.getStringExtra("action.type")))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.menu_list_home, menu)
//        if (action == CRYPTOCURRENCY) {
//            menu!!.findItem(R.id.action_search).isVisible = false
//            menu.findItem(R.id.action_favorites).isVisible = true
//        }
//        val myActionMenuItem = menu!!.findItem(R.id.action_search)
//        val searchView = myActionMenuItem.actionView as SearchView
//        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String): Boolean {
//                if (query.isEmpty()) {
//                    searchAllCoins()
//                    return false
//                }
//                searchCoinByFilter(query)
//                return false
//            }
//
//            override fun onQueryTextChange(query: String): Boolean {
//                if (query.isEmpty()) {
//                    searchAllCoins()
//                    return false
//                }
//                searchCoinByFilter(query)
//                return true
//            }
//        })

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
//        when (item!!.itemId) {
//            android.R.id.home -> finish()
//            R.id.action_favorites -> {
//
//                if (coinDao.getAllFavorites().isEmpty()) {
//                    Utils().showSnackBar(content, this, getString(R.string.activity_list_favorite_coins))
//                    return true
//                }
//
//                startActivity(Intent(this, FavoriteActivity::class.java))
//            }
//            else -> finish()
//        }

        return true
    }

    private fun searchCoinByFilter(filter: String) {
        when (action) {
            Action.GPU -> {
                resultList.clear()
                resultList.addAll(coinDao.getWhatToMineGpuByFilter(filter))
            }
            Action.ASIC -> {
                resultList.clear()
                resultList.addAll(coinDao.getWhatToMineAsicByFilter(filter))
            }
            Action.WARZ -> {
                resultList.clear()
                resultList.addAll(coinDao.getWhatToMineWarzByFilter(filter))
            }
            else -> {
                resultList.clear()
                resultList.addAll(coinDao.getWhatToMineGpuByFilter(filter))
            }
        }

        adapterWhatToMine?.notifyDataSetChanged()
    }

    private fun searchAllCoins() {
        when (action) {
            Action.GPU -> {
                resultList.clear()
                resultList.addAll(coinDao.getAllWhatToMineGpu())
            }
            Action.ASIC -> {
                resultList.clear()
                resultList.addAll(coinDao.getAllWhatToMineAsic())
            }
            Action.WARZ -> {
                resultList.clear()
                resultList.addAll(coinDao.getAllWhatToMineWarz())
            }
            else -> {
                resultList.clear()
                resultList.addAll(coinDao.getAllWhatToMineGpu())
            }
        }

        adapterWhatToMine?.notifyDataSetChanged()
    }

    private val callBack: BaseCallBack = object : BaseCallBack() {
        override fun onSuccess() {

//            recyclerView.visible()
//            progressBar.gone()
//
//            recyclerView.layoutManager = LinearLayoutManager(this@CoinListActivity)
//            recyclerView.hasFixedSize()
//
//            when (action) {
//                Action.GPU -> {
//                    searchAllCoins()
//                    adapterWhatToMine = WhatToMineAdapter(this@CoinListActivity, resultList)
//                    recyclerView.adapter = adapterWhatToMine
//                }
//                Action.ASIC -> {
//                    searchAllCoins()
//                    adapterWhatToMine = WhatToMineAdapter(this@CoinListActivity, resultList)
//                    recyclerView.adapter = adapterWhatToMine
//                }
//                Action.WARZ -> {
//                    searchAllCoins()
//                    adapterWhatToMine = WhatToMineAdapter(this@CoinListActivity, resultList)
//                    recyclerView.adapter = adapterWhatToMine
//                }
//                Action.CRYPTOCURRENCY -> {
//                    recyclerView.layoutManager = GridLayoutManager(this@CoinListActivity, 3)
//                    val resultList = coinDao.getAllCryptoCurrency()
//                    val adapterCryptoCurrency = CryptoCurrencyAdapter(this@CoinListActivity, ArrayList(resultList), object : CryptoCurrencyAdapter.OnItemClickListener {
//                        override fun itemOnClick(item: CryptoCurrency) {
//                            val intent = Intent(this@CoinListActivity, CoinDetailActivity::class.java)
//                            intent.putExtra("action.coin.detail", item)
//                            startActivity(intent)
//                        }
//                    })
//                    recyclerView.adapter = adapterCryptoCurrency
//                }
//                else -> {
//                    contentError.visible()
//                    recyclerView.gone()
//                }
//            }
        }

        override fun onError() {
//            contentError.visible()
//            recyclerView.gone()
//            progressBar.gone()
        }
    }
}
