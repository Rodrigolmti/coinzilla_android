package com.rodrigolmti.coinzilla.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.rodrigolmti.coinzilla.R
import com.rodrigolmti.coinzilla.data.model.api.WtmAltcoinResponse
import com.rodrigolmti.coinzilla.data.model.api.WtmAsicResponse
import com.rodrigolmti.coinzilla.data.model.api.WtmGpuResponse
import kotlinx.android.synthetic.main.row_what_to_mine.view.*

open class WhatToMineAdapter(private val context: Context, var list: List<Any>?) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {

    private var filteredData: List<Any>?

    init {
        filteredData = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): androidx.recyclerview.widget.RecyclerView.ViewHolder {
        return Item(LayoutInflater.from(context).inflate(R.layout.row_what_to_mine, parent, false))
    }

    override fun onBindViewHolder(holder: androidx.recyclerview.widget.RecyclerView.ViewHolder, position: Int) {
        (holder as Item).bindData(context, filteredData!![position])
    }

    override fun getItemCount(): Int {
        return filteredData!!.size
    }

    override fun getFilter(): Filter {
        return ItemFilter()
    }

    private inner class ItemFilter : Filter() {

        override fun performFiltering(constraint: CharSequence): Filter.FilterResults {

            val query = constraint.toString().toLowerCase()
            val results = Filter.FilterResults()

            list?.let {

                val count = it.size
                val filteredList = ArrayList<Any>(count)

                for (i in 0 until count) {

                    val item = it[i]
                    when (item) {
                        is WtmAsicResponse -> {

                            val tag = item.tag.toLowerCase()

                            if (tag.contains(query)) {
                                filteredList.add(item)
                            }
                        }
                        is WtmGpuResponse -> {

                            val tag = item.tag.toLowerCase()

                            if (tag.contains(query)) {
                                filteredList.add(item)
                            }
                        }
                        is WtmAltcoinResponse -> {

                            val tag = item.tag.toLowerCase()

                            if (tag.contains(query)) {
                                filteredList.add(item)
                            }
                        }
                    }
                }

                results.values = filteredList
                results.count = filteredList.size

            }

            return results
        }

        override fun publishResults(constraint: CharSequence, results: Filter.FilterResults) {
            filteredData = results.values as ArrayList<*>?
            notifyDataSetChanged()
        }
    }

    class Item(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {
        fun bindData(context: Context, item: Any) {
            when (item) {
                is WtmAsicResponse -> handleAsicItem(item, context)
                is WtmGpuResponse -> handleGpuItem(item, context)
                is WtmAltcoinResponse -> handleAltcoinItem(item, context)
            }
        }

        private fun handleAltcoinItem(item: WtmAltcoinResponse, context: Context) {
            try {
                val tag: String = item.tag.split("(")[1].replace(")", "").trim().toLowerCase()
                val res = context.resources.getIdentifier(tag, "drawable", context.packageName)
                if (res == 0)
                    itemView.imageViewCrypto.setImageResource(R.drawable.ic_action_money)
                else
                    itemView.imageViewCrypto.setImageResource(res)
            } catch (error: Exception) {
                itemView.imageViewCrypto.setImageResource(R.drawable.ic_action_money)
            }

            itemView.textViewTag.text = item.tag
            itemView.textViewAlgorithm.text = item.algorithm
            itemView.textViewDifficulty.text = item.difficulty
            itemView.textViewResward.text = item.estReward
            itemView.textViewMarket.text = item.marketCap
            itemView.textViewNetHash.text = item.netHash
        }

        private fun handleGpuItem(item: WtmGpuResponse, context: Context) {
            var netHash = 0
            try {
                netHash = item.netHash.toFloat().toInt()
            } catch (error: Exception) {
            }

            try {
                val res = context.resources.getIdentifier(item.tag.toLowerCase(), "drawable", context.packageName)
                if (res == 0)
                    itemView.imageViewCrypto.setImageResource(R.drawable.ic_action_money)
                else
                    itemView.imageViewCrypto.setImageResource(res)
            } catch (error: Exception) {
                itemView.imageViewCrypto.setImageResource(R.drawable.ic_action_money)
            }

            itemView.textViewTag.text = item.tag
            itemView.textViewAlgorithm.text = item.algorithm
            itemView.textViewDifficulty.text = item.difficulty
            itemView.textViewResward.text = item.estReward
            itemView.textViewMarket.text = item.marketCap
            itemView.textViewNetHash.text = netHash.toString()
        }

        private fun handleAsicItem(item: WtmAsicResponse, context: Context) {
            var netHash = 0
            try {
                netHash = item.netHash.toFloat().toInt()
            } catch (error: Exception) {
            }

            try {
                val res = context.resources.getIdentifier(item.tag.toLowerCase(), "drawable", context.packageName)
                if (res == 0)
                    itemView.imageViewCrypto.setImageResource(R.drawable.ic_action_money)
                else
                    itemView.imageViewCrypto.setImageResource(res)
            } catch (error: Exception) {
                itemView.imageViewCrypto.setImageResource(R.drawable.ic_action_money)
            }

            itemView.textViewTag.text = item.tag
            itemView.textViewAlgorithm.text = item.algorithm
            itemView.textViewDifficulty.text = item.difficulty
            itemView.textViewResward.text = item.estReward
            itemView.textViewMarket.text = item.marketCap
            itemView.textViewNetHash.text = netHash.toString()
        }
    }
}