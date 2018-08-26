package com.rodrigolmti.coinzilla.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rodrigolmti.coinzilla.R
import com.rodrigolmti.coinzilla.data.model.api.CryptoCurrencyResponse
import com.rodrigolmti.coinzilla.util.extensions.formatCurrencyBRL
import com.rodrigolmti.coinzilla.util.extensions.formatCurrencyUSD
import kotlinx.android.synthetic.main.row_crypto_currency.view.*

open class CryptoCurrencyAdapter(val context: Context, val list: List<CryptoCurrencyResponse>?,
                                 private val listener: OnItemClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return Item(context, LayoutInflater.from(context).inflate(R.layout.row_crypto_currency, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as Item).bindData(list!![position], listener)
    }

    private val filteredList: ArrayList<CryptoCurrencyResponse> = ArrayList()

    init {
        filteredList.addAll(list!!)
    }

    override fun getItemCount(): Int {
        return list!!.size
    }

    class Item(val context: Context, itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(item: CryptoCurrencyResponse, listener: OnItemClickListener) {
            itemView.setOnClickListener { listener.itemOnClick(item) }
            itemView.textViewSymbol.text = item.tag
            itemView.textViewName.text = item.name
            item.quoteUsd?.let {
                itemView.textViewPriceUsd.text = "$${it.price.formatCurrencyUSD()}"
            }
            item.quoteBrl?.let {
                itemView.textViewPriceBrl.text = "R$${it.price.formatCurrencyBRL()}"
            }
            try {
                val res = context.resources.getIdentifier(item.tag!!.toLowerCase(), "drawable", context.packageName)
                if (res == 0)
                    itemView.imageViewCrypto.setImageResource(R.drawable.ic_action_money)
                else
                    itemView.imageViewCrypto.setImageResource(res)
            } catch (error: Exception) {
                itemView.imageViewCrypto.setImageResource(R.drawable.ic_action_money)
            }
        }
    }

    interface OnItemClickListener {
        fun itemOnClick(item: CryptoCurrencyResponse)
    }
}