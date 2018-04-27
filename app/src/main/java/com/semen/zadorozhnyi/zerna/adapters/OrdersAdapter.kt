package com.semen.zadorozhnyi.zerna.adapters

import android.content.res.Resources
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.semen.zadorozhnyi.zerna.R
import com.semen.zadorozhnyi.zerna.domain.Order
import com.semen.zadorozhnyi.zerna.domain.OrderInfo
import com.semen.zadorozhnyi.zerna.extentions.toDateTimeString
import java.util.*
import kotlin.collections.ArrayList

class OrdersAdapter : RecyclerView.Adapter<OrdersAdapter.ViewHolder>() {

    private var order: List<Order> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewItem = LayoutInflater.from(parent.context)
                .inflate(R.layout.order_item, parent, false)
        return ViewHolder(viewItem)
    }

    override fun getItemCount() = order.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val order = getItem(position)

        holder.userName.text = order.userInfo.name
        holder.arrivalTime.text = order.info.date.toDateTimeString()
        val newOrder = order.info.newOrder
        if (newOrder) {
            holder.newFlag.setTextColor(ContextCompat.getColor(holder.newFlag.context, R.color.colorNewOrder))
            holder.newFlag.text = "НОВЫЙ"
        } else {
            holder.newFlag.setTextColor(ContextCompat.getColor(holder.newFlag.context, R.color.orderDone))
            holder.newFlag.text = "ГОТОВО"
        }

        val adapter = ItemOrderAdapter()
        holder.orders.adapter = adapter
        adapter.setItems(order.items)
    }

    private fun getItem(position: Int) = order[position]

    fun swapData(data: List<Order>) {
        this.order = ArrayList(data)
        notifyDataSetChanged()
    }

    class ViewHolder(
            itemView: View,
            val userName: TextView = itemView.findViewById(R.id.user_name),
            val arrivalTime: TextView = itemView.findViewById(R.id.arrival_time),
            val orders: RecyclerView = itemView.findViewById(R.id.order_items),
            val newFlag: TextView = itemView.findViewById(R.id.new_order)
    ) : RecyclerView.ViewHolder(itemView) {
        init {
            orders.apply {
                layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            }
        }
    }

}