package com.semen.zadorozhnyi.zerna.domain

import com.google.firebase.firestore.DocumentSnapshot

data class Item(
        val name: String,
        val price: Long,
        val size: Boolean,
        val orderId: String,
        val sugar: Boolean,
        val syrup: String) {

    companion object {
        fun fromFirebaseItem(document: DocumentSnapshot): Item {
            val data = document.data
            return Item(data?.get("beverage") as String,
                    data["cost"] as Long,
                    data["size"] as Boolean,
                    data["orderId"] as String,
                    data["sugar"] as Boolean,
                    data["syrup"] as String)
        }
    }
}