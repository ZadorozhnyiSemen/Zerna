package com.semen.zadorozhnyi.zerna.adapters

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.semen.zadorozhnyi.zerna.R
import com.semen.zadorozhnyi.zerna.domain.Item

class ItemOrderAdapter : RecyclerView.Adapter<ItemOrderAdapter.ViewHolder>() {

    private var items: List<Item> = ArrayList()

    fun setItems(items: List<Item>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewItem = LayoutInflater.from(parent.context)
                .inflate(R.layout.order_detail_item, parent, false)
        return ViewHolder(viewItem)
    }

    override fun getItemCount(): Int = items.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.itemName.text = item.name
        holder.itemPrice.text = item.price.toInt().toString() + " р."
        holder.itemSize.text = if (item.size == true) "Большой" else "Маленький"
    }

    private fun getItem(position: Int) = items[position]

    class ViewHolder(
            itemView: View,
            val itemName: TextView = itemView.findViewById(R.id.order_item_name),
            val itemPrice: TextView = itemView.findViewById(R.id.order_item_price),
            val itemSize: TextView = itemView.findViewById(R.id.order_size)
    ) : RecyclerView.ViewHolder(itemView)
}
