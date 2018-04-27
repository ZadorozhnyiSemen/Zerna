package com.semen.zadorozhnyi.zerna.domain

import android.annotation.SuppressLint
import com.google.firebase.firestore.DocumentSnapshot
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.Date

data class OrderInfo(val date: DateTime, var newOrder: Boolean) {

    companion object {
        fun fromFirebaseOrder(document: DocumentSnapshot): OrderInfo {
            val data = document.data
            val date = data?.get("time") as Date?
            val joda = DateTime(date)
            return OrderInfo(
                    joda,
                    data?.get("new") as Boolean
            )
        }
    }
}