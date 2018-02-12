package com.rodrigolmti.coinzilla.coinzilla.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rodrigolmti.coinzilla.R
import com.rodrigolmti.coinzilla.coinzilla.model.entity.WhatToMineAsic
import com.rodrigolmti.coinzilla.coinzilla.model.entity.WhatToMineGpu
import com.rodrigolmti.coinzilla.coinzilla.model.entity.WhatToMineWarz
import kotlinx.android.synthetic.main.row_what_to_mine.view.textViewAlgorithm
import kotlinx.android.synthetic.main.row_what_to_mine.view.textViewDifficulty
import kotlinx.android.synthetic.main.row_what_to_mine.view.textViewMarket
import kotlinx.android.synthetic.main.row_what_to_mine.view.textViewNetHash
import kotlinx.android.synthetic.main.row_what_to_mine.view.textViewResward
import kotlinx.android.synthetic.main.row_what_to_mine.view.textViewTag

open class WhatToMineAdapter(val context: Context, var list: ArrayList<Any>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val filteredList: ArrayList<Any> = ArrayList()

    init {
        filteredList.addAll(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder? {
        return Item(LayoutInflater.from(context).inflate(R.layout.row_what_to_mine, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        (holder as Item).bindData(list[position])
    }

    open fun filter(text: String) {
        list.clear()
        if (text.isEmpty()) {
            list.addAll(filteredList)
        } else {
            for (item in filteredList) {
                when (item) {
                    is WhatToMineAsic -> {
                        if (item.tag.contains(text, true)) {
                            list.add(item)
                        }
                    }
                    is WhatToMineGpu -> {
                        if (item.tag.contains(text, true)) {
                            list.add(item)
                        }
                    }
                    is WhatToMineWarz -> {
                        if (item.tag.contains(text, true)) {
                            list.add(item)
                        }
                    }
                }
            }
        }
        notifyDataSetChanged()
    }

    class Item(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(item: Any) {
            when (item) {
                is WhatToMineAsic -> {

                    var netHash = 0
                    try {
                        netHash = item.netHash.toFloat().toInt()
                    } catch (error: Exception) {}

                    itemView.textViewTag.text = item.tag
                    itemView.textViewAlgorithm.text = item.algorithm
                    itemView.textViewDifficulty.text = item.difficulty
                    itemView.textViewResward.text = item.estReward
                    itemView.textViewMarket.text = item.marketCap
                    itemView.textViewNetHash.text = netHash.toString()
                }
                is WhatToMineGpu -> {

                    var netHash = 0
                    try {
                        netHash = item.netHash.toFloat().toInt()
                    } catch (error: Exception) {}

                    itemView.textViewTag.text = item.tag
                    itemView.textViewAlgorithm.text = item.algorithm
                    itemView.textViewDifficulty.text = item.difficulty
                    itemView.textViewResward.text = item.estReward
                    itemView.textViewMarket.text = item.marketCap
                    itemView.textViewNetHash.text = netHash.toString()
                }
                is WhatToMineWarz -> {
                    itemView.textViewTag.text = item.tag
                    itemView.textViewAlgorithm.text = item.algorithm
                    itemView.textViewDifficulty.text = item.difficulty
                    itemView.textViewResward.text = item.estReward
                    itemView.textViewMarket.text = item.marketCap
                    itemView.textViewNetHash.text = item.netHash
                }
            }
        }
    }
}