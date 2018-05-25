package com.semen.zadorozhnyi.zerna.firestore

import android.util.Log
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.semen.zadorozhnyi.zerna.domain.Order
import io.ashdavies.rx.rxtasks.RxTasks
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.subjects.PublishSubject

class OrdersFirestore {

    val orderIdPublisher: PublishSubject<List<String>> = PublishSubject.create()
    val oredersPublisher: PublishSubject<List<Order>> = PublishSubject.create()

    val orders: MutableList<Order> = mutableListOf()

    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    private lateinit var ordersCollection: CollectionReference
    private lateinit var ordersListener: EventListener<QuerySnapshot>

    init {
        ordersCollection = firestore.collection(ORDERS_DATA)

        ordersListener = EventListener { snapshot, exception ->
            if (exception != null) {
                Log.e("OrdersFirestore.class", "Something wrong", exception)
                return@EventListener
            }

            val ids: MutableList<String> = mutableListOf()

            for (document in snapshot!!) {
                ids.add(document.id)
            }
            orderIdPublisher.onNext(ids)

            Log.d("OrdersFirestore.class", "Collection: $ids")
        }

        ordersCollection.addSnapshotListener(ordersListener)
    }

    fun combineOrderInfo() {
        Completable.mergeArray()
    }

    fun getOrderItems(orderId: String): Single<QuerySnapshot> =
            RxTasks.single(FirebaseFirestore.getInstance().collection("items").whereEqualTo("orderId", orderId).get())

    fun getOrderInfo(id: String): Single<DocumentSnapshot> =
            RxTasks.single(FirebaseFirestore.getInstance().collection(ORDERS_DATA).document(id).get())

    companion object {
        private const val ORDERS_DATA = "orders"
    }
}