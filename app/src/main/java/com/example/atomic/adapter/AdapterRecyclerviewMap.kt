package com.example.atomic.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.atomic.Levels
import com.example.atomic.R
import com.example.atomic.utils.l
import com.example.atomic.ui.activity.CentralAtcivity

class AdapterRecyclerviewMap : RecyclerView.Adapter<AdapterRecyclerviewMap.MyViewHolder> {
    lateinit var activity:Activity

    constructor(act: Activity) : super() {
        this.activity = act
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view, parent, false)
        return MyViewHolder(v,activity)
    }

    override fun getItemCount(): Int = Levels.level_1.getQuantityLevels()

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        holder.imageLevel.setImageResource(Levels.level_6.getImage())
        holder.nameLevel.text = "   ${Levels.valueOf("level_${position+1}").getNameOfLevel()}   "
        holder.numLevel = position+1
    }

    class MyViewHolder : RecyclerView.ViewHolder, View.OnClickListener {

        lateinit var activity:Activity

        override fun onClick(v: View?) {
            (activity as CentralAtcivity).changeActivity(numLevel)
            l("next Level")
        }

        var layout: RelativeLayout
        var nameLevel: TextView
//        var imageLevel: ImageView
        var numLevel: Int = 1


        constructor(itemView: View,activity:Activity) : super(itemView) {
            this.activity = activity

            layout = itemView.findViewById(R.id.layout)
            nameLevel = itemView.findViewById(R.id.nameLevel)
//            imageLevel = itemView.findViewById(R.id.imageLevel)

            layout.setOnClickListener(this)
        }
    }

}