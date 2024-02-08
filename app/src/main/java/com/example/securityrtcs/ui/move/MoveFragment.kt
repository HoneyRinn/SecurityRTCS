package com.example.securityrtcs.ui.move

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.securityrtcs.LoginActivity
import com.example.securityrtcs.R
import com.example.securityrtcs.net.NotifyAdapter
import com.example.securityrtcs.net.notifyinfo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class MoveFragment : Fragment() {

    companion object {
        fun newInstance() = MoveFragment()
    }

    private lateinit var dbref: DatabaseReference
    private lateinit var viewModel: MoveViewModel
    lateinit var btn: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var notifyArrayList: ArrayList<notifyinfo>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_move, container, false)

        recyclerView = root.findViewById(R.id.recyclerNotify)
        notifyArrayList = arrayListOf<notifyinfo>()
        getModuleData()

        btn = root.findViewById(R.id.btnClear)
        val button = root.findViewById<Button>(R.id.btnClear)
        button.setOnClickListener {
            dbref = FirebaseDatabase.getInstance().getReference("notify")
            dbref.removeValue()
            getModuleData()
        }
        return root
    }

    override fun onStart() {
        super.onStart()
        getModuleData()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MoveViewModel::class.java)
        // TODO: Use the ViewModel
    }


    private fun getModuleData() {
        dbref = FirebaseDatabase.getInstance().getReference("notify")

        dbref.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                notifyArrayList.clear()
                if(snapshot.exists()){
                    for (moduleSnapshot in snapshot.children)
                    {
                        val module = moduleSnapshot.getValue(notifyinfo::class.java)
                        notifyArrayList.add(module!!)
                    }
                    recyclerView.adapter = NotifyAdapter(this@MoveFragment, notifyArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                throw error.toException();
            }
        })
    }


}