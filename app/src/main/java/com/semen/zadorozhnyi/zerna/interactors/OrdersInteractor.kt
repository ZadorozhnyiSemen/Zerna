package com.semen.zadorozhnyi.zerna.interactors

import android.util.Log
import com.semen.zadorozhnyi.zerna.domain.Item
import com.semen.zadorozhnyi.zerna.domain.Order
import com.semen.zadorozhnyi.zerna.firestore.OrdersFirestore
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer

class OrdersInteractor {
    private val ordersFirestore = OrdersFirestore()

    val orders: MutableList<Order> = mutableListOf()

    init {

        ordersFirestore.orderIdPublisher.subscribe {
            for (id in it) {
                ordersFirestore.getOrderInfo(id)
                        .doOnEvent { order, e ->
                            if (e != null) {
                                Log.e("OrdersInteractor.class", "Ecxeption occured", e)
                            }
                            if (order.exists()) {
                                val element = Order.fromFirebaseOrder(order)
                                val items = mutableListOf<Item>()
                                ordersFirestore.getOrderItems(element.id).doOnSuccess {
                                    for (queryDocumentSnapshot in it) {
                                        val item = Item.fromFirebaseItem(queryDocumentSnapshot)
                                        items.add(item)
                                    }
                                }.doFinally {
                                    element.items = items
                                    orders.add(element)
                                    println(element)
                                }.subscribe()
                            }
                        }.subscribe()
            }
        }
    }


}