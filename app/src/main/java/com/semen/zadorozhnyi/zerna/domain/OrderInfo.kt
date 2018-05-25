package com.semen.zadorozhnyi.zerna.domain

import com.google.firebase.firestore.DocumentSnapshot
import org.joda.time.DateTime


data class OrderInfo(val date: DateTime, var newOrder: Boolean, val userInfo: UserInfo) {

    companion object {
        fun fromFirebaseOrderInfo(document: DocumentSnapshot): OrderInfo {
            val timestamp = document.getTimestamp("time")
            val data = document.data
            val joda = DateTime(timestamp?.toDate())
            val userInfo = UserInfo(data?.get("name") as String)
            return OrderInfo(
                    joda,
                    data["new"] as Boolean,
                    userInfo
            )
        }
    }
}