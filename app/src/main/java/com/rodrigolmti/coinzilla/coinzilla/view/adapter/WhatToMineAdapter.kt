package com.rodrigolmti.coinzilla.coinzilla.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rodrigolmti.coinzilla.R
import com.rodrigolmti.coinzilla.coinzilla.model.entity.coin.WhatToMineAsic
import com.rodrigolmti.coinzilla.coinzilla.model.entity.coin.WhatToMineGpu
import com.rodrigolmti.coinzilla.coinzilla.model.entity.coin.WhatToMineWarz
import kotlinx.android.synthetic.main.row_what_to_mine.view.*

open class WhatToMineAdapter(private val context: Context, var list: MutableList<Any>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return Item(LayoutInflater.from(context).inflate(R.layout.row_what_to_mine, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as Item).bindData(context, list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class Item(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(context: Context, item: Any) {
            when (item) {
                is WhatToMineAsic -> {

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
                is WhatToMineGpu -> {

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
                is WhatToMineWarz -> {

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
            }
        }
    }
}