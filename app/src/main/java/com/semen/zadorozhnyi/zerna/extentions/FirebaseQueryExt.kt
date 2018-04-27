package com.semen.zadorozhnyi.zerna.extentions

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.QuerySnapshot
import com.semen.zadorozhnyi.zerna.domain.Order
import java.util.Date


/*
fun QuerySnapshot.toOrders(): List<Order> {
    val orders = mutableListOf<Order>()
    for (item in this) {
        val data = item.data
        val new: Boolean = data["new"] as Boolean
        val userReference = data["userId"] as DocumentReference
        val userName = "some name"
        val time = data["time"] as Date
        orders.add(Order(listOf(), time = time.time, user = userName))
    }
    return orders
}*/
