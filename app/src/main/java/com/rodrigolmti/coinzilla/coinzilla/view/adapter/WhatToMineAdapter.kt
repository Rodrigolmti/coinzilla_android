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
import kotlinx.android.synthetic.main.row_what_to_mine.view.imageViewCrypto
import kotlinx.android.synthetic.main.row_what_to_mine.view.textViewAlgorithm
import kotlinx.android.synthetic.main.row_what_to_mine.view.textViewDifficulty
import kotlinx.android.synthetic.main.row_what_to_mine.view.textViewMarket
import kotlinx.android.synthetic.main.row_what_to_mine.view.textViewNetHash
import kotlinx.android.synthetic.main.row_what_to_mine.view.textViewResward
import kotlinx.android.synthetic.main.row_what_to_mine.view.textViewTag
import java.util.regex.Pattern

open class WhatToMineAdapter(private val context: Context, var list: MutableList<Any>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder? {
        return Item(LayoutInflater.from(context).inflate(R.layout.row_what_to_mine, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        (holder as Item).bindData(context, list[position])
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
                        val regex = Pattern.compile("\\((.*?)\\)")
                        val regexMatcher = regex.matcher(item.tag)
                        val res = context.resources.getIdentifier(regexMatcher.group(0).toLowerCase(), "drawable", context.packageName)
                        if (res == 0)
                            itemView.imageViewCrypto.setImageResource(R.drawable.ic_action_money)
                        else
                            itemView.imageViewCrypto.setImageResource(res)
                    } catch (error: Exception) {
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