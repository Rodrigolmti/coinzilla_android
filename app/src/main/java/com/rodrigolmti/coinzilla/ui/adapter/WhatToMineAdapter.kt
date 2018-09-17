package com.rodrigolmti.coinzilla.ui.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rodrigolmti.coinzilla.R
import com.rodrigolmti.coinzilla.data.model.api.WtmAltcoinResponse
import com.rodrigolmti.coinzilla.data.model.api.WtmAsicResponse
import com.rodrigolmti.coinzilla.data.model.api.WtmGpuResponse
import kotlinx.android.synthetic.main.row_what_to_mine.view.*

open class WhatToMineAdapter(private val context: Context, var list: List<Any>?) : androidx.recyclerview.widget.RecyclerView.Adapter<androidx.recyclerview.widget.RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): androidx.recyclerview.widget.RecyclerView.ViewHolder {
        return Item(LayoutInflater.from(context).inflate(R.layout.row_what_to_mine, parent, false))
    }

    override fun onBindViewHolder(holder: androidx.recyclerview.widget.RecyclerView.ViewHolder, position: Int) {
        (holder as Item).bindData(context, list!![position])
    }

    override fun getItemCount(): Int {
        return list!!.size
    }

    class Item(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {
        fun bindData(context: Context, item: Any) {
            when (item) {
                is WtmAsicResponse -> {

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
                is WtmGpuResponse -> {

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
                is WtmAltcoinResponse -> {

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