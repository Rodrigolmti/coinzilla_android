package com.rodrigolmti.coinzilla.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rodrigolmti.coinzilla.R
import com.rodrigolmti.coinzilla.data.model.api.ExchangeResponse
import com.rodrigolmti.coinzilla.util.formatCurrencyBRL
import com.rodrigolmti.coinzilla.util.formatCurrencyUSD
import kotlinx.android.synthetic.main.item_exchange.view.*

class ExchangeAdapter(val context: Context, val list: List<ExchangeResponse>?) : androidx.recyclerview.widget.RecyclerView.Adapter<androidx.recyclerview.widget.RecyclerView.ViewHolder>() {

    override fun onBindViewHolder(holder: androidx.recyclerview.widget.RecyclerView.ViewHolder, position: Int) {
        (holder as Item).bindData(list!![position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): androidx.recyclerview.widget.RecyclerView.ViewHolder {
        return Item(LayoutInflater.from(context).inflate(R.layout.item_exchange, parent, false))
    }

    override fun getItemCount(): Int {
        return list!!.size
    }

    class Item(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {
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