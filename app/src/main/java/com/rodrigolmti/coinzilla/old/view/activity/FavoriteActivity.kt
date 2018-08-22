package com.rodrigolmti.coinzilla.old.view.activity

import android.os.Bundle
import com.rodrigolmti.coinzilla.R
import com.rodrigolmti.coinzilla.old.controller.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_favorite.*

class FavoriteActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)
        title = getString(R.string.activity_favorite_title)
        enableBackButton()
        initAds(adView)
    }

    override fun onResume() {
        super.onResume()

//        val list = coinDAO.getAllFavorites()
//        if (list.isNotEmpty()) {
//            recyclerView.layoutManager = GridLayoutManager(this, 3)
//            recyclerView.hasFixedSize()
//            recyclerView.visible()
//            val adapter = CryptoCurrencyAdapter(this, ArrayList(list), object : CryptoCurrencyAdapter.OnItemClickListener {
//                override fun itemOnClick(item: CryptoCurrencyResponse) {
//                    val intent = Intent(this@FavoriteActivity, CoinDetailActivity::class.java)
//                    intent.putExtra("action.coin.detail", item)
//                    startActivity(intent)
//                }
//            })
//            recyclerView.adapter = adapter
//        } else {
//            recyclerView.gone()
//        }
    }
}