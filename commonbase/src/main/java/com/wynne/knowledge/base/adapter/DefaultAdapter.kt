package com.wynne.knowledge.base.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wynne.knowledge.base.R

class DefaultAdapter(val context: Context, val list: List<MainData> = mutableListOf()) :
    RecyclerView.Adapter<DefaultAdapter.MainViewHolder>() {
    lateinit var listener: (position: Int) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_main_layout, parent, false)
        return MainViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        if (list.isNotEmpty()) {
            holder.tvModelName.text = list[position].name
            holder.ivIcon.setImageResource(list[position].iconPath)
        }

        holder.itemView.setOnClickListener {
            listener(position)
        }
    }


    inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvModelName: TextView
        var ivIcon :ImageView

        init {
            tvModelName = itemView.findViewById(R.id.tvModelName)
            ivIcon = itemView.findViewById(R.id.ivAndroid)
        }
    }
}