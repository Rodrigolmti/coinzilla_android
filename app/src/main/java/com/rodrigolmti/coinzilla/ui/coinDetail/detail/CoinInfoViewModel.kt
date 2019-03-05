package com.rodrigolmti.coinzilla.ui.coinDetail.detail

import android.content.Context
import android.content.res.Resources
import androidx.databinding.ObservableField
import com.rodrigolmti.coinzilla.R
import com.rodrigolmti.coinzilla.data.model.api.CryptoCurrencyResponse
import com.rodrigolmti.coinzilla.di.qualifier.AppContext
import com.rodrigolmti.coinzilla.di.scopes.PerFragment
import com.rodrigolmti.coinzilla.ui.base.viewModel.BaseViewModel
import com.rodrigolmti.coinzilla.util.formatCurrencyBRL
import com.rodrigolmti.coinzilla.util.formatCurrencyUSD
import javax.inject.Inject

@PerFragment
class CoinInfoViewModel
@Inject constructor(
        @AppContext val context: Context,
        private val resources: Resources
) : BaseViewModel() {

    val name: ObservableField<String> = ObservableField()
    val symbol: ObservableField<String> = ObservableField()
    val coinCurrency: ObservableField<String> = ObservableField()
    val volume: ObservableField<String> = ObservableField()
    val marketCap: ObservableField<String> = ObservableField()
    val maxSupply: ObservableField<String> = ObservableField()
    val totalSupply: ObservableField<String> = ObservableField()
    val percentChange1H: ObservableField<String> = ObservableField()
    val percentChange24H: ObservableField<String> = ObservableField()
    val percentChange7D: ObservableField<String> = ObservableField()

    fun setupView(response: CryptoCurrencyResponse, currency: String) {

        coinCurrency.set(currency)
        name.set(response.name)
        symbol.set(response.tag)

        val usd = resources.getString(R.string.activity_detail_usd)
        val brl = resources.getString(R.string.activity_detail_brl)

        response.maxSupply?.let { maxSupply.set(it.toString()) }
        response.totalSupply?.let { totalSupply.set(it.toString()) }

        if (currency == usd) {

            response.quoteUsd?.let {

                volume.set(it.volume24.formatCurrencyUSD())
                marketCap.set(it.marketCap.formatCurrencyUSD())
                percentChange1H.set(it.percentChange1H.formatCurrencyUSD())
                percentChange24H.set(it.percentChange24H.formatCurrencyUSD())
                percentChange7D.set(it.percentChange7D.formatCurrencyUSD())

            }

            return
        }

        if (currency == brl) {

            response.quoteUsd?.let {

                volume.set(it.volume24.formatCurrencyBRL())
                marketCap.set(it.marketCap.formatCurrencyBRL())
                percentChange1H.set(it.percentChange1H.formatCurrencyBRL())
                percentChange24H.set(it.percentChange24H.formatCurrencyBRL())
                percentChange7D.set(it.percentChange7D.formatCurrencyBRL())

            }
        }
    }
}
