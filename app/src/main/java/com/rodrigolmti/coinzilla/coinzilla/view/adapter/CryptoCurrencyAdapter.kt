package com.rodrigolmti.coinzilla.coinzilla.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rodrigolmti.coinzilla.R
import com.rodrigolmti.coinzilla.data.model.api.CryptoCurrencyResponse
import kotlinx.android.synthetic.main.row_crypto_currency.view.*

open class CryptoCurrencyAdapter(val context: Context, val list: List<CryptoCurrencyResponse>?,
                                 private val listener: OnItemClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return Item(LayoutInflater.from(context).inflate(R.layout.row_crypto_currency, parent, false))
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

    class Item(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(item: CryptoCurrencyResponse, listener: OnItemClickListener) {
            itemView.setOnClickListener { listener.itemOnClick(item) }
            itemView.textViewSymbol.text = item.symbol
            itemView.textViewName.text = item.name
        }
    }

    interface OnItemClickListener {
        fun itemOnClick(item: CryptoCurrencyResponse)
    }
}