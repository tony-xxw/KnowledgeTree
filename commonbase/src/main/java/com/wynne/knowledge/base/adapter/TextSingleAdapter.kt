package com.wynne.knowledge.base.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wynne.knowledge.base.R

class TextSingleAdapter : RecyclerView.Adapter<TextSingleAdapter.TextSingleViewHolder>() {
    var context: Context? = null

    override fun getItemCount(): Int {
        return 2
    }

    override fun onBindViewHolder(holder: TextSingleViewHolder, position: Int) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextSingleViewHolder {
        return TextSingleViewHolder(LayoutInflater.from(context).inflate(R.layout.item_text_single, parent, false))
    }


    class TextSingleViewHolder(val convertView: View) : RecyclerView.ViewHolder(convertView) {
        fun <T : View> findView(viewId: Int): T {
            return convertView.findViewById(viewId)
        }

    }
}