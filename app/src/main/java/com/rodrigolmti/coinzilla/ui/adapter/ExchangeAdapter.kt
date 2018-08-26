package com.rodrigolmti.coinzilla.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rodrigolmti.coinzilla.R
import com.rodrigolmti.coinzilla.data.model.api.ExchangeResponse
import com.rodrigolmti.coinzilla.util.extensions.formatCurrencyBRL
import com.rodrigolmti.coinzilla.util.extensions.formatCurrencyUSD
import kotlinx.android.synthetic.main.row_exchange.view.*

class ExchangeAdapter(val context: Context, val list: List<ExchangeResponse>?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as Item).bindData(list!![position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return Item(LayoutInflater.from(context).inflate(R.layout.row_exchange, parent, false))
    }

    override fun getItemCount(): Int {
        return list!!.size
    }

    class Item(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(item: ExchangeResponse) {
            itemView.textViewExchangeName.text = item.exchange
            if (item.toSymbol == "BRL") {
                itemView.textViewExchangeValue.text = item.volume24h.formatCurrencyBRL()
            } else {
                itemView.textViewExchangeValue.text = item.volume24h.formatCurrencyUSD()
            }
        }
    }
}