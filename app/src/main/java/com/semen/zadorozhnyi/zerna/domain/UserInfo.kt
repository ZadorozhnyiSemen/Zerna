package com.semen.zadorozhnyi.zerna.domain

import com.google.firebase.firestore.QueryDocumentSnapshot

class UserInfo(val name: String = "unknown") {

    companion object {
        fun fromFirebaseUserInfo(document: QueryDocumentSnapshot): UserInfo {
            val data = document.data
            return UserInfo(data["name"] as String)
        }
    }
}