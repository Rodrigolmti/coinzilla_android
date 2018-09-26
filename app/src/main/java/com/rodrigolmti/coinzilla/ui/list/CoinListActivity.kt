package com.rodrigolmti.coinzilla.ui.list

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import com.rodrigolmti.coinzilla.R
import com.rodrigolmti.coinzilla.data.model.api.CryptoCurrencyResponse
import com.rodrigolmti.coinzilla.databinding.ActivityCoinListBinding
import com.rodrigolmti.coinzilla.ui.adapter.CryptoCurrencyAdapter
import com.rodrigolmti.coinzilla.ui.adapter.WhatToMineAdapter
import com.rodrigolmti.coinzilla.ui.base.BaseActivity
import com.rodrigolmti.coinzilla.ui.coinDetail.CoinDetailActivity
import com.rodrigolmti.coinzilla.util.MenuActionEnum

class CoinListActivity : BaseActivity<ActivityCoinListBinding, CoinListViewModel>() {

    private lateinit var cryptoCurrencyAdapter: CryptoCurrencyAdapter
    private lateinit var whatToMineAdapter: WhatToMineAdapter
    private lateinit var action: MenuActionEnum

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setAndBindContentView(savedInstanceState, R.layout.activity_coin_list)
        setSupportActionBar(binding.toolbar)
        enableBackButton()
        if (intent.hasExtra("action.type")) {
            action = MenuActionEnum.valueOf(intent.getStringExtra("action.type"))
            viewModel.getDataByAction(action)
        }
        setupRecycler()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_list_home, menu)
        if (action == MenuActionEnum.CRYPTOCURRENCY) {
            menu!!.findItem(R.id.action_search).isVisible = false
            menu.findItem(R.id.action_favorites).isVisible = true
        }
        val myActionMenuItem = menu!!.findItem(R.id.action_search)
        setupSearchView(myActionMenuItem)
        return true
    }

    private fun setupRecycler() {
        when (action) {
            MenuActionEnum.GPU, MenuActionEnum.ASIC, MenuActionEnum.ALTCOIN -> setupLinearListAdapter()
            else -> setupGridListAdapter()
        }
        viewModel.mutableGpuLiveData.observe(this, Observer {
            whatToMineAdapter = WhatToMineAdapter(this@CoinListActivity, it)
            binding.recyclerView.adapter = whatToMineAdapter
        })
        viewModel.mutableAsicLiveData.observe(this, Observer {
            whatToMineAdapter = WhatToMineAdapter(this@CoinListActivity, it)
            binding.recyclerView.adapter = whatToMineAdapter
        })
        viewModel.mutableAltcoinLiveData.observe(this, Observer {
            whatToMineAdapter = WhatToMineAdapter(this@CoinListActivity, it)
            binding.recyclerView.adapter = whatToMineAdapter
        })
        viewModel.mutableCryptoCurrencyLiveData.observe(this, Observer {
            cryptoCurrencyAdapter = CryptoCurrencyAdapter(this@CoinListActivity, it, object : CryptoCurrencyAdapter.OnItemClickListener {
                override fun itemOnClick(item: CryptoCurrencyResponse) {
                    val intent = Intent(this@CoinListActivity, CoinDetailActivity::class.java)
                    intent.putExtra("action.coin.detail", item.id)
                    activityNavigator.startActivity(intent)
                }
            })
            binding.recyclerView.adapter = cryptoCurrencyAdapter
        })
    }

    private fun setupLinearListAdapter() {
        binding.recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this@CoinListActivity)
        binding.recyclerView.hasFixedSize()
    }

    private fun setupGridListAdapter() {
        binding.recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this@CoinListActivity)
    }

    private fun setupSearchView(myActionMenuItem: MenuItem) {
        val searchView = myActionMenuItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                (binding.recyclerView.adapter as WhatToMineAdapter).filter.filter(query)
                return false
            }

            override fun onQueryTextChange(query: String): Boolean {
                (binding.recyclerView.adapter as WhatToMineAdapter).filter.filter(query)
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

            }
            else -> finish()
        }

        return true
    }
}
