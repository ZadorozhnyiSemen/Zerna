package com.semen.zadorozhnyi.zerna.domain

import com.google.firebase.firestore.QueryDocumentSnapshot

data class Item(val name: String = "none", val price: Long = 0, var size: Boolean = false) {

    companion object {
        fun fromFirebaseItemInfo(document: QueryDocumentSnapshot): Item {
            val data = document.data
            println(data)
            return Item(data["name"] as String,
                    data["price"] as Long,
                    data["size"] as Boolean)
        }
    }
}