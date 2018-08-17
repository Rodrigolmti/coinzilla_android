package com.rodrigolmti.coinzilla.ui.list

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import com.rodrigolmti.coinzilla.R
import com.rodrigolmti.coinzilla.coinzilla.model.callback.BaseCallBack
import com.rodrigolmti.coinzilla.coinzilla.view.activity.FavoriteActivity
import com.rodrigolmti.coinzilla.coinzilla.view.adapter.WhatToMineAdapter
import com.rodrigolmti.coinzilla.databinding.ActivityCoinListBinding
import com.rodrigolmti.coinzilla.library.util.Action
import com.rodrigolmti.coinzilla.ui.base.BaseActivity

class CoinListActivity : BaseActivity<ActivityCoinListBinding, CoinListViewModel>() {

    private var adapterWhatToMine: WhatToMineAdapter? = null
    private lateinit var action: Action

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setAndBindContentView(savedInstanceState, R.layout.activity_coin_list)
        setSupportActionBar(binding.toolbar)
        enableBackButton()

        if (intent.hasExtra("action.type")) {
            action = Action.valueOf(intent.getStringExtra("action.type"))
            viewModel.getDataByAction(action)
        }

        setupRecycler()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_list_home, menu)
        if (action == Action.CRYPTOCURRENCY) {
            menu!!.findItem(R.id.action_search).isVisible = false
            menu.findItem(R.id.action_favorites).isVisible = true
        }
        val myActionMenuItem = menu!!.findItem(R.id.action_search)
        setupSearchView(myActionMenuItem)
        return true
    }

    private fun setupRecycler() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this@CoinListActivity)
        binding.recyclerView.hasFixedSize()

        viewModel.mutableGpuLiveData.observe(this, Observer {
            adapterWhatToMine = WhatToMineAdapter(this@CoinListActivity, it)
            binding.recyclerView.adapter = adapterWhatToMine
        })
        viewModel.mutableAsicLiveData.observe(this, Observer {
            adapterWhatToMine = WhatToMineAdapter(this@CoinListActivity, it)
            binding.recyclerView.adapter = adapterWhatToMine
        })
        viewModel.mutableAltcoinLiveData.observe(this, Observer {
            adapterWhatToMine = WhatToMineAdapter(this@CoinListActivity, it)
            binding.recyclerView.adapter = adapterWhatToMine
        })
    }

    private fun setupSearchView(myActionMenuItem: MenuItem) {
        val searchView = myActionMenuItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
    //                if (query.isEmpty()) {
    //                    searchAllCoins()
    //                    return false
    //                }
    //                searchCoinByFilter(query)
                return false
            }

            override fun onQueryTextChange(query: String): Boolean {
    //                if (query.isEmpty()) {
    //                    searchAllCoins()
    //                    return false
    //                }
    //                searchCoinByFilter(query)
                return true
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.action_favorites -> {

//                if (coinDao.getAllFavorites().isEmpty()) {
//                    Utils().showSnackBar(binding.content, this, getString(R.string.activity_list_favorite_coins))
//                    return true
//                }

                startActivity(Intent(this, FavoriteActivity::class.java))
            }
            else -> finish()
        }

        return true
    }

    private val callBack: BaseCallBack = object : BaseCallBack() {
        override fun onSuccess() {

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
    }
}
