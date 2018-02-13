package com.rodrigolmti.coinzilla.coinzilla.view.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.rodrigolmti.coinzilla.R
import com.rodrigolmti.coinzilla.coinzilla.model.dao.CoinDAO
import com.rodrigolmti.coinzilla.coinzilla.model.entity.CryptoCurrency
import com.rodrigolmti.coinzilla.coinzilla.view.adapter.CryptoCurrencyAdapter
import com.rodrigolmti.coinzilla.coinzilla.view.extensions.gone
import com.rodrigolmti.coinzilla.coinzilla.view.extensions.visible
import com.rodrigolmti.coinzilla.library.controller.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_favorite.adView
import kotlinx.android.synthetic.main.activity_favorite.recyclerView

class FavoriteActivity : BaseActivity() {

    private val coinDAO: CoinDAO = CoinDAO()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)
        title = getString(R.string.activity_favorite_title)
        enableBackButton()
        initAds(adView)
    }

    override fun onResume() {
        super.onResume()

        val list = coinDAO.getAllFavorites()
        if (list.isNotEmpty()) {
            recyclerView.layoutManager = GridLayoutManager(this, 3)
            recyclerView.hasFixedSize()
            recyclerView.visible()
            val adapter = CryptoCurrencyAdapter(this, ArrayList(list), object : CryptoCurrencyAdapter.OnItemClickListener {
                override fun itemOnClick(item: CryptoCurrency) {
                    val intent = Intent(this@FavoriteActivity, CoinDetailActivity::class.java)
                    intent.putExtra("action.coin.detail", item)
                    startActivity(intent)
                }
            })
            recyclerView.adapter = adapter
        } else {
            recyclerView.gone()
        }
    }
}