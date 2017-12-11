package com.rodrigolmti.coinzilla.coinzilla.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rodrigolmti.coinzilla.R
import com.rodrigolmti.coinzilla.coinzilla.model.entity.CryptoCurrency
import kotlinx.android.synthetic.main.row_crypto_currency.view.textViewName
import kotlinx.android.synthetic.main.row_crypto_currency.view.textViewSymbol

open class CryptoCurrencyAdapter(val context: Context, val list: ArrayList<CryptoCurrency>, val listener: onItemClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val filteredList: ArrayList<CryptoCurrency> = ArrayList()

    init {
        filteredList.addAll(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder? {
        return Item(LayoutInflater.from(context).inflate(R.layout.row_crypto_currency, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        (holder as Item).bindData(list[position], listener)
    }

    open fun filter(text: String) {
        list.clear()
        if (text.isEmpty()) {
            list.addAll(filteredList)
        } else {
            filteredList.filterTo(list) {
                it.name!!.contains(text, true) ||
                        it.symbol!!.contains(text, true)
            }
        }
        notifyDataSetChanged()
    }


    class Item(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(item: CryptoCurrency, listener: onItemClickListener) {
            itemView.setOnClickListener { listener.itemOnClick(item) }
            itemView.textViewSymbol.text = item.symbol
            itemView.textViewName.text = item.name
        }
    }

    interface onItemClickListener {
        fun itemOnClick(item: CryptoCurrency)
    }
}