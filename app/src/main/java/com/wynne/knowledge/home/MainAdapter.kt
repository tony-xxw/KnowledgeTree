package com.wynne.knowledge.home

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class MainAdapter(val context: Context) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {
    var mList = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_main_layout, parent, false)
        return MainViewHolder(view)
    }

    override fun getItemCount(): Int = mList.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        if (mList.isNotEmpty()) {
            holder.tvModelName.text = mList[position]
        }
    }


    inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvModelName: TextView

        init {
            tvModelName = itemView.findViewById(R.id.tvModelName)
        }
    }
}