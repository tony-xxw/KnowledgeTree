package com.wynne.advanced

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.wynne.advanced.jetpack.JetPackActivity
import com.wynne.knowledge.base.base.BaseActivity
import com.wynne.knowledge.base.constant.ARouterPath.BASE_HIGH
import kotlinx.android.synthetic.main.activity_base_advanced_layout.*

@Route(path = BASE_HIGH)
class BaseAdvancedActivity : BaseActivity() {

    var list = mutableListOf<String>()

    override fun initView() {
        tlBar.title = "JetPack"

        list.add("JetPack")
        rvList.layoutManager = LinearLayoutManager(this)
        val adapter = adapter(list)
        adapter.listener = {
            when (it) {
                0 -> {
                    startActivity(Intent(this, JetPackActivity::class.java))
                }
            }

        }
        rvList.adapter = adapter
    }

    override fun getLayoutId(): Int = R.layout.activity_base_advanced_layout


    class adapter(list: List<String>) : RecyclerView.Adapter<viewHolder>() {
        var count = 0
        var list: List<String>
        lateinit var listener: (Int) -> Unit


        init {
            count = list.size
            this.list = list
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
            return viewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false))
        }

        override fun getItemCount(): Int = count

        override fun onBindViewHolder(holder: viewHolder, position: Int) {
            holder.textView.text = list[position]
            holder.textView.setOnClickListener {
                listener(position)
            }
        }


    }

    class viewHolder(item: View) : RecyclerView.ViewHolder(item) {

        var textView = item.findViewById<TextView>(R.id.tvText)
    }


}