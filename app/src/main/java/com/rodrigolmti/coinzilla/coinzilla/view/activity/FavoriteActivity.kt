package com.rodrigolmti.coinzilla.coinzilla.view.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.MenuItem
import android.view.View
import com.google.android.gms.ads.AdRequest
import com.rodrigolmti.coinzilla.R
import com.rodrigolmti.coinzilla.coinzilla.model.entity.CryptoCurrency
import com.rodrigolmti.coinzilla.coinzilla.model.presenter.Presenter
import com.rodrigolmti.coinzilla.coinzilla.view.adapter.CryptoCurrencyAdapter
import com.rodrigolmti.coinzilla.library.controller.mvp.BasePresenter
import com.rodrigolmti.coinzilla.library.controller.mvp.BaseView
import com.rodrigolmti.coinzilla.library.util.Action
import kotlinx.android.synthetic.main.activity_favorite.*

class FavoriteActivity : AppCompatActivity(), BaseView {

    private val presenter: BasePresenter = Presenter(this, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        initAds()

        val actionBar: ActionBar = supportActionBar as ActionBar
        actionBar.title = getString(R.string.activity_favorite_title)
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setHomeButtonEnabled(true)
    }

    override fun onResume() {
        super.onResume()

        val list = presenter.getAllFavorites()
        if (list.isNotEmpty()) {
            recyclerView.layoutManager = GridLayoutManager(this, 3)
            recyclerView.hasFixedSize()
            val adapter = CryptoCurrencyAdapter(this, ArrayList(presenter.getAllFavorites()), object : CryptoCurrencyAdapter.onItemClickListener {
                override fun itemOnClick(item: CryptoCurrency) {
                    val intent = Intent(this@FavoriteActivity, DetailActivity::class.java)
                    intent.putExtra("action.coin", item)
                    startActivity(intent)
                }
            })
            recyclerView.adapter = adapter
        } else {
            layoutEmpty.visibility = View.VISIBLE
            recyclerView.visibility = View.GONE
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }

    override fun showProgressBar(visibility: Int) {
    }

    override fun success(result: List<Any>) {
    }

    override fun success(action: Action) {
    }

    override fun error(action: Action) {
    }

    override fun error(message: String) {
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