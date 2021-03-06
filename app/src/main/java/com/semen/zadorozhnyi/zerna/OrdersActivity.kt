package com.semen.zadorozhnyi.zerna

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.semen.zadorozhnyi.zerna.adapters.OrdersAdapter
import com.semen.zadorozhnyi.zerna.domain.Item
import com.semen.zadorozhnyi.zerna.domain.Order
import com.semen.zadorozhnyi.zerna.domain.OrderInfo
import com.semen.zadorozhnyi.zerna.domain.UserInfo
import com.semen.zadorozhnyi.zerna.firestore.OrdersFirestore
import com.semen.zadorozhnyi.zerna.interactors.OrdersInteractor
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.activity_orders.*

class OrdersActivity : AppCompatActivity(), View.OnClickListener{

    private lateinit var recyclerView: RecyclerView
    private lateinit var ordersAdapter: OrdersAdapter
    private lateinit var ordersLayoutManager: RecyclerView.LayoutManager

    private lateinit var fireStore: FirebaseFirestore

    private val orderList = mutableListOf<Order>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_orders)

        fireStore = FirebaseFirestore.getInstance()

        menu_grid.setOnClickListener(this)

        ordersLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        ordersAdapter = OrdersAdapter()
        val divider = DividerItemDecoration(orders.context, LinearLayoutManager.VERTICAL)

        orders.apply {
            setHasFixedSize(true)
            addItemDecoration(divider)
            layoutManager = ordersLayoutManager
            adapter = ordersAdapter
        }

        val ordersFirestore = OrdersInteractor()
    }

    override fun onClick(v: View?) {
        when(v) {
            menu_grid -> {
                val intent = Intent(this@OrdersActivity, MenuActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun getSpecificOrderForOrder(parentName: String) = fireStore.collection("orders").document(parentName).collection("order")

    private fun getSpecificUserForOrder(parentName: String) = fireStore.collection("orders").document(parentName).collection("user")

    private fun updateData() {
        println(orderList)
        ordersAdapter.swapData(orderList)
        progressBar.visibility = View.INVISIBLE
        orders.visibility = View.VISIBLE
        orderList.clear()
    }
}
