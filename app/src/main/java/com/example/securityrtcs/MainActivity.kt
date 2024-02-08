package com.example.securityrtcs

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.securityrtcs.net.HelperAdapter
import com.example.securityrtcs.net.moduleinfo
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {

    private lateinit var dbref: DatabaseReference
    private lateinit var recyclerView: RecyclerView
    private lateinit var moduleArrayList: ArrayList<moduleinfo>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        moduleArrayList = arrayListOf<moduleinfo>()
    }

    //override fun onStart() {
    //    super.onStart()
    //    getModuleData()
    //}
//
    //private fun getModuleData() {
    //    dbref = FirebaseDatabase.getInstance().getReference("Moduls")
//
    //    dbref.addValueEventListener(object : ValueEventListener{
//
    //        override fun onDataChange(snapshot: DataSnapshot) {
    //            moduleArrayList.clear()
    //            if(snapshot.exists()){
    //                for (moduleSnapshot in snapshot.children)
    //                {
    //                    val module = moduleSnapshot.getValue(moduleinfo::class.java)
    //                    moduleArrayList.add(module!!)
    //                }
    //                recyclerView.adapter = HelperAdapter(this@MainActivity, moduleArrayList)
    //            }
    //        }
//
    //        override fun onCancelled(error: DatabaseError) {
    //            throw error.toException();
    //        }
    //    })
    //}

}