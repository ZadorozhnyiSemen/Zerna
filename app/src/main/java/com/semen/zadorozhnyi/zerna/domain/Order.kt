package com.semen.zadorozhnyi.zerna.domain

import com.google.firebase.firestore.DocumentSnapshot

data class Order(
        val id: String,
        var info: OrderInfo,
        var items: MutableList<Item> = mutableListOf()
) {

    companion object {
        fun fromFirebaseOrder(document: DocumentSnapshot): Order {
            val id = document.id
            val orderInfo = OrderInfo.fromFirebaseOrderInfo(document)
            return Order(
                    id,
                    orderInfo
            )
        }
    }
}
