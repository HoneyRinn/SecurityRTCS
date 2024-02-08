package com.example.securityrtcs.ui.eye

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.securityrtcs.R
import com.example.securityrtcs.net.HelperAdapter
import com.example.securityrtcs.net.MoveAdapter
import com.example.securityrtcs.net.moduleinfo
import com.example.securityrtcs.net.moveinfo
import com.google.firebase.database.*

class EyeFragment : Fragment() {

    private lateinit var dbref: DatabaseReference

    private lateinit var recyclerCamera: RecyclerView
    private lateinit var cameraArrayList: ArrayList<moduleinfo>

    private lateinit var recyclerMove: RecyclerView
    private lateinit var moveArrayList: ArrayList<moveinfo>


    companion object {
        fun newInstance() = EyeFragment()
    }

    private lateinit var viewModel: EyeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_eye, container, false)

        recyclerCamera = root.findViewById(R.id.recyclerView)
        recyclerMove = root.findViewById(R.id.recyclerView2)
        cameraArrayList = arrayListOf<moduleinfo>()
        moveArrayList = arrayListOf<moveinfo>()
        getModuleData()
        getMoveData()
        return root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        getModuleData()
        getMoveData()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(EyeViewModel::class.java)
        // TODO: Use the ViewModel
    }

    private fun getModuleData() {
        dbref = FirebaseDatabase.getInstance().getReference("Moduls")

        dbref.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                cameraArrayList.clear()
                if(snapshot.exists()){
                    for (moduleSnapshot in snapshot.children)
                    {
                        val module = moduleSnapshot.getValue(moduleinfo::class.java)
                        cameraArrayList.add(module!!)
                    }
                    recyclerCamera.adapter = HelperAdapter(this@EyeFragment, cameraArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                throw error.toException();
            }
        })
    }
    private fun getMoveData() {
        dbref = FirebaseDatabase.getInstance().getReference("Move")

        dbref.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                moveArrayList.clear()
                if(snapshot.exists()){
                    for (moduleSnapshot in snapshot.children)
                    {
                        val move = moduleSnapshot.getValue(moveinfo::class.java)
                        moveArrayList.add(move!!)
                    }
                    recyclerMove.adapter = MoveAdapter(this@EyeFragment, moveArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                throw error.toException();
            }
        })
    }

}