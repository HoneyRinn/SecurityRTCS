package com.example.securityrtcs.net

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.securityrtcs.R
import com.example.securityrtcs.ui.eye.EyeFragment
import com.example.securityrtcs.ui.move.MoveFragment

class NotifyAdapter(private val context: MoveFragment, private val moduleList: ArrayList<notifyinfo>) : RecyclerView.Adapter<NotifyAdapter.MyVH>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyVH {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.motification_item, parent, false)
        return MyVH(itemView)
    }

    override fun onBindViewHolder(holder: NotifyAdapter.MyVH, position: Int) {

        val currentItem = moduleList[position]
        holder.moduleText.text = currentItem.text
        Glide.with(context).load(currentItem.image).into(holder.imageState)
    }

    override fun getItemCount(): Int {
        return moduleList.size
    }

    class MyVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageState: ImageView = itemView.findViewById(R.id.imgCamera)
        val moduleText : TextView = itemView.findViewById(R.id.notify_text)
    }
}