package com.wynne.knowledge.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MainAdapter(val context: Context) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {
    var mList = mutableListOf<String>()
    lateinit var listener: (position: Int) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_main_layout, parent, false)
        return MainViewHolder(view)
    }

    override fun getItemCount(): Int = mList.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        if (mList.isNotEmpty()) {
            holder.tvModelName.text = mList[position]
        }

        holder.itemView.setOnClickListener {
            listener(position)
        }
    }


    inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvModelName: TextView

        init {
            tvModelName = itemView.findViewById(R.id.tvModelName)
        }
    }
}