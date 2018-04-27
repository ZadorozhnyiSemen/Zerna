package com.semen.zadorozhnyi.zerna.adapters

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.semen.zadorozhnyi.zerna.R
import com.semen.zadorozhnyi.zerna.domain.MenuItem
import java.util.ArrayList


class MenuItemAdapter : RecyclerView.Adapter<MenuItemAdapter.ViewHolder>() {

    private var data: List<MenuItem> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewItem = LayoutInflater.from(parent.context)
                .inflate(R.layout.menu_item, parent, false)
        return ViewHolder(viewItem)
    }

    override fun getItemCount() = data.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.menuItemName.text = item.name
        holder.menuItemPrice.text = item.price.toString() + "р."
    }

    fun swapData(data: List<MenuItem>) {
        this.data = data
        notifyDataSetChanged()
    }

    private fun getItem(position: Int) = data[position]

    class ViewHolder(
            itemView: View,
            val menuItemName: TextView = itemView.findViewById(R.id.menu_item_name),
            val menuItemPrice: TextView = itemView.findViewById(R.id.menu_item_price)
    ) : RecyclerView.ViewHolder(itemView)
}