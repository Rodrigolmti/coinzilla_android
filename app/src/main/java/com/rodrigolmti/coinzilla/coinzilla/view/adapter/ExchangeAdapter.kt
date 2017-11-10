package com.rodrigolmti.coinzilla.coinzilla.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rodrigolmti.coinzilla.R
import com.rodrigolmti.coinzilla.coinzilla.model.entity.Exchange
import kotlinx.android.synthetic.main.row_exchange.view.*

class ExchangeAdapter (val context: Context, val list: List<Exchange>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder? {
        return Item(LayoutInflater.from(context).inflate(R.layout.row_exchange, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        (holder as Item).bindData(list[position])
    }

    class Item(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(item: Exchange) {

            itemView.textViewExchangeName.text = item.market
            if (item.type == "BRL") {
                itemView.textViewExchangeValue.text = "R$ ${item.price}"
            } else {
                itemView.textViewExchangeValue.text = "$ ${item.price}"
            }
        }
    }
}