package com.rodrigolmti.coinzilla.ui.adapter

/**
 * Created by rodrigolmti on 12/02/18.
 */
//class PoloniexBalanceAdapter(val context: Context, val list: List<PoloniexCoin>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//        return Item(LayoutInflater.from(context).inflate(R.layout.item_exchange_balance, parent, false))
//    }
//
//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        (holder as Item).bindData(context, list[position])
//    }
//
//    override fun getItemCount(): Int {
//        return list.size
//    }
//
//    class Item(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        fun bindData(context: Context, item: PoloniexCoin) {
//
//            itemView.imageViewCoin
//
//            try {
//                val res = context.resources.getIdentifier(item.tag.toLowerCase(), "drawable", context.packageName)
//                if (res == 0)
//                    itemView.imageViewCoin.setImageResource(R.drawable.ic_action_money)
//                else
//                    itemView.imageViewCoin.setImageResource(res)
//            } catch (error: Exception) {
//            }
//
//            itemView.textViewTag.text = item.tag
//            itemView.textViewAmount.text = item.quant
//        }
//    }
//}