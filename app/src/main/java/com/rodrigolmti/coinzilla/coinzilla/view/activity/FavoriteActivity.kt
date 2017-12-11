package com.rodrigolmti.coinzilla.coinzilla.view.activity

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.rodrigolmti.coinzilla.R
import com.rodrigolmti.coinzilla.coinzilla.model.entity.CryptoCurrency
import com.rodrigolmti.coinzilla.coinzilla.model.presenter.Presenter
import com.rodrigolmti.coinzilla.coinzilla.view.adapter.CryptoCurrencyAdapter
import com.rodrigolmti.coinzilla.library.controller.activity.BaseActivity
import com.rodrigolmti.coinzilla.library.controller.mvp.BasePresenter
import com.rodrigolmti.coinzilla.library.controller.mvp.BaseView
import kotlinx.android.synthetic.main.activity_favorite.adView
import kotlinx.android.synthetic.main.activity_favorite.layoutEmpty
import kotlinx.android.synthetic.main.activity_favorite.recyclerView

class FavoriteActivity : BaseActivity(), BaseView {

    private val presenter: BasePresenter = Presenter(this, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)
        actionBar.title = getString(R.string.activity_favorite_title)
        initAds(adView)
    }

    override fun onResume() {
        super.onResume()

        val list = presenter.getAllFavorites()
        if (list.isNotEmpty()) {
            recyclerView.layoutManager = GridLayoutManager(this, 3)
            recyclerView.hasFixedSize()
            val adapter = CryptoCurrencyAdapter(this, ArrayList(presenter.getAllFavorites()), object : CryptoCurrencyAdapter.onItemClickListener {
                override fun itemOnClick(item: CryptoCurrency) {
//                    val intent = Intent(this@FavoriteActivity, DetailActivity::class.java)
//                    intent.putExtra("action.coin", item)
//                    startActivity(intent)
                }
            })
            recyclerView.adapter = adapter
        } else {
            layoutEmpty.visibility = View.VISIBLE
            recyclerView.visibility = View.GONE
        }
    }
}