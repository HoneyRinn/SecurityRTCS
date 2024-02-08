package com.example.securityrtcs.net

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.securityrtcs.R
import com.example.securityrtcs.ui.eye.EyeFragment

class HelperAdapter(private val context: EyeFragment, private val moduleList: ArrayList<moduleinfo>) : RecyclerView.Adapter<HelperAdapter.MyVH>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyVH {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.module_content, parent, false)
        return MyVH(itemView)
    }

    override fun onBindViewHolder(holder: MyVH, position: Int) {
       val currentItem = moduleList[position]
       // holder.imageState.setImageDrawable(
       //     //ContextCompat.getDrawable(this, R.drawable.)
       // )
        holder.moduleAlert.text = currentItem.alert
        holder.moduleName.text = currentItem.name
        holder.moduleState.text = currentItem.state
        holder.moduleTime.text = currentItem.timeOfAlert
        holder.moduleLight.text = currentItem.light

        Glide.with(context).load(currentItem.image).into(holder.imageState)
    }

    override fun getItemCount(): Int {
        return moduleList.size
    }

    class MyVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageState: ImageView = itemView.findViewById(R.id.module_icon)
        val moduleName : TextView = itemView.findViewById(R.id.module_name)
        val moduleState : TextView = itemView.findViewById(R.id.text_state)
        val moduleTime : TextView = itemView.findViewById(R.id.text_timeOfAlert)
        val moduleAlert : TextView = itemView.findViewById(R.id.text_moveState)
        val moduleLight : TextView = itemView.findViewById(R.id.text_light)
    }
}