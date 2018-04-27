package com.semen.zadorozhnyi.zerna

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.semen.zadorozhnyi.zerna.adapters.MenuItemAdapter
import com.semen.zadorozhnyi.zerna.domain.MenuItem
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : AppCompatActivity() {

    private lateinit var fireStore: FirebaseFirestore

    private var menuItems: MutableList<MenuItem> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        fireStore = FirebaseFirestore.getInstance()
        val menuReference = fireStore.collection("menu")

        val menuLayoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
        val menuAdapter = MenuItemAdapter()

        menu.apply {
            layoutManager = menuLayoutManager
            adapter = menuAdapter
        }

        menuReference.get().addOnCompleteListener {
            if (it.isSuccessful) {
                it.result.documents.forEach {
                    menuItems.add(MenuItem.fromFirebaseMenuItem(it))
                }
                menuAdapter.swapData(menuItems)
            }
        }
    }
}
