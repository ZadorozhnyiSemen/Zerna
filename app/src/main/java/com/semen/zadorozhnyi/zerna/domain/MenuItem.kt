package com.semen.zadorozhnyi.zerna.domain

import com.google.firebase.firestore.DocumentSnapshot


data class MenuItem(val name: String = "", val price: Int = 0, val size: Boolean = false) {

    companion object {
        fun fromFirebaseMenuItem(item: DocumentSnapshot): MenuItem {
            val data = item.data
            val name = data?.get("name") as String
            val price = data["price"] as Long
            val size = data["size"] as Boolean
            return MenuItem(name, price.toInt(), size)
        }
    }
}